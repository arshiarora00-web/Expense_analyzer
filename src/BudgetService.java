import java.io.*;
import java.time.YearMonth;
import java.util.*;

/**
 * Monthly budget feature:
 *  1. set monthly budget
 *  2. compare spending with budget
 *  3. calculate remaining budget
 *  4. warn when budget is exceeded
 *
 * ASSUMPTION: model.Expense has getAmount() -> double and getDate() -> java.time.LocalDate.
 * If your getters are named differently, change them in getMonthlySpending() only.
 */
public class BudgetService {

    private static final String BUDGET_FILE = "budgets.dat";
    private static final double WARNING_THRESHOLD = 0.80; // warn at 80% usage

    private final Map<YearMonth, Double> budgets = new HashMap<>();

    public BudgetService() {
        load();
    }

    // 1. Set monthly budget
    public void setMonthlyBudget(YearMonth month, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Budget must be greater than 0.");
        }
        budgets.put(month, amount);
        save();
    }

    public Optional<Double> getBudget(YearMonth month) {
        return Optional.ofNullable(budgets.get(month));
    }

    // total spent in a given month
    public double getMonthlySpending(List<Expense> expenses, YearMonth month) {
        return expenses.stream()
                .filter(e -> YearMonth.from(e.date).equals(month))
                .mapToDouble(e -> e.amount)
                .sum();
    }

    // 3. Remaining budget (negative means overspent)
    public double getRemainingBudget(List<Expense> expenses, YearMonth month) {
        double budget = budgets.getOrDefault(month, 0.0);
        return budget - getMonthlySpending(expenses, month);
    }

    // 4. Throws if spending is over budget
    public void checkBudget(List<Expense> expenses, YearMonth month) throws BudgetExceededException {
        Double budget = budgets.get(month);
        if (budget == null) return;
        double spent = getMonthlySpending(expenses, month);
        if (spent > budget) {
            throw new BudgetExceededException(budget, spent);
        }
    }

    // 2. Compare spending with budget and print a report (with warnings)
    public void printBudgetReport(List<Expense> expenses, YearMonth month) {
        Double budget = budgets.get(month);
        if (budget == null) {
            System.out.println("No budget set for " + month + ". Please set a budget first.");
            return;
        }

        double spent = getMonthlySpending(expenses, month);
        double remaining = budget - spent;
        double percent = (spent / budget) * 100;

        System.out.println("===== Budget Report: " + month + " =====");
        System.out.printf("Budget    : %.2f%n", budget);
        System.out.printf("Spent     : %.2f (%.1f%%)%n", spent, percent);
        System.out.printf("Remaining : %.2f%n", remaining);

        try {
            checkBudget(expenses, month);
            if (spent >= budget * WARNING_THRESHOLD) {
                System.out.println("WARNING: You have used more than 80% of your budget!");
            } else {
                System.out.println("Status: Within budget.");
            }
        } catch (BudgetExceededException ex) {
            System.out.println("WARNING: " + ex.getMessage());
        }
    }

    // ---- simple persistence (budgets.dat, format: yyyy-MM=amount) ----
    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BUDGET_FILE))) {
            for (Map.Entry<YearMonth, Double> e : budgets.entrySet()) {
                pw.println(e.getKey() + "=" + e.getValue());
            }
        } catch (IOException ex) {
            System.out.println("Could not save budgets: " + ex.getMessage());
        }
    }

    private void load() {
        File f = new File(BUDGET_FILE);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    budgets.put(YearMonth.parse(parts[0]), Double.parseDouble(parts[1]));
                }
            }
        } catch (IOException | RuntimeException ex) {
            System.out.println("Could not load budgets: " + ex.getMessage());
        }
    }
}