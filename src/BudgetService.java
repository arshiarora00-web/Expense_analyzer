import java.io.*;
import java.time.YearMonth;
import java.util.*;

public class BudgetService {

    private static final String BUDGET_FILE = "budgets.dat";
    private static final double WARNING_THRESHOLD = 0.80;

    private final Map<YearMonth, Double> budgets = new HashMap<>();

    public BudgetService() {
        load();
    }

    // Set Monthly Budget
    public void setMonthlyBudget(YearMonth month, double amount) {

        if (month == null) {
            throw new IllegalArgumentException("Month cannot be empty.");
        }

        if (amount <= 0 || !Double.isFinite(amount)) {
            throw new IllegalArgumentException(
                    "Budget must be a valid positive amount."
            );
        }

        budgets.put(month, amount);
        save();
    }

    // Get Budget
    public Optional<Double> getBudget(YearMonth month) {
        return Optional.ofNullable(budgets.get(month));
    }

    // Calculate Monthly Spending
    public double getMonthlySpending(List<Expense> expenses, YearMonth month) {

        if (expenses == null || month == null) {
            return 0;
        }

        double total = 0;

        for (Expense e : expenses) {

            if (e != null && e.date != null) {

                if (YearMonth.from(e.date).equals(month)) {
                    total += e.amount;
                }
            }
        }

        return total;
    }

    // Calculate Remaining Budget
    public double getRemainingBudget(List<Expense> expenses, YearMonth month) {

        double budget = budgets.getOrDefault(month, 0.0);

        return budget - getMonthlySpending(expenses, month);
    }

    // Check Budget
    public void checkBudget(List<Expense> expenses, YearMonth month)
            throws BudgetExceededException {

        Double budget = budgets.get(month);

        if (budget == null) {
            return;
        }

        double spent = getMonthlySpending(expenses, month);

        if (spent > budget) {
            throw new BudgetExceededException(budget, spent);
        }
    }

    // Print Budget Report
    public void printBudgetReport(List<Expense> expenses, YearMonth month) {

        if (month == null) {
            System.out.println("Invalid month.");
            return;
        }

        Double budget = budgets.get(month);

        if (budget == null) {
            System.out.println("No budget set for " + month);
            System.out.println("Please set a budget first.");
            return;
        }

        double spent = getMonthlySpending(expenses, month);
        double remaining = budget - spent;
        double percent = (spent / budget) * 100;

        System.out.println("\n===== Budget Report: " + month + " =====");

        System.out.printf("Budget    : Rs. %.2f%n", budget);
        System.out.printf("Spent     : Rs. %.2f (%.1f%%)%n", spent, percent);
        System.out.printf("Remaining : Rs. %.2f%n", remaining);

        try {

            checkBudget(expenses, month);

            if (spent >= budget * WARNING_THRESHOLD) {
                System.out.println(
                        "WARNING: You have used more than 80% of your budget!"
                );
            } else {
                System.out.println("Status: Within budget.");
            }

        } catch (BudgetExceededException ex) {

            System.out.println("WARNING: " + ex.getMessage());
        }
    }

    // Save Budget Data
    private void save() {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(BUDGET_FILE))) {

            for (Map.Entry<YearMonth, Double> e : budgets.entrySet()) {

                pw.println(e.getKey() + "=" + e.getValue());
            }

        } catch (IOException ex) {

            System.out.println("Could not save budgets: " + ex.getMessage());
        }
    }

    // Load Budget Data
    private void load() {

        File f = new File(BUDGET_FILE);

        if (!f.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {

            String line;

            while ((line = br.readLine()) != null) {

                try {

                    String[] parts = line.split("=");

                    if (parts.length == 2) {

                        YearMonth month =
                                YearMonth.parse(parts[0].trim());

                        double amount =
                                Double.parseDouble(parts[1].trim());

                        if (amount > 0 && Double.isFinite(amount)) {

                            budgets.put(month, amount);

                        } else {

                            System.out.println(
                                    "Invalid budget skipped: " + line
                            );
                        }
                    }

                } catch (RuntimeException ex) {

                    System.out.println(
                            "Invalid budget record skipped: " + line
                    );
                }
            }

        } catch (IOException ex) {

            System.out.println(
                    "Could not load budgets: " + ex.getMessage()
            );
        }
    }
}