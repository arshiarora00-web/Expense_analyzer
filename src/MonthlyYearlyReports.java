
import java.util.HashMap;
import java.util.Map;

public class MonthlyYearlyReports {

    // Monthly Report
    public static void showMonthlyReport() {

        try {
            System.out.print("Enter year (e.g., 2026): ");
            int year = Integer.parseInt(Main.sc.nextLine());

            System.out.print("Enter month (1-12): ");
            int month = Integer.parseInt(Main.sc.nextLine());

            if (month < 1 || month > 12) {
                System.out.println("Invalid month!");
                return;
            }

            double total = 0;
            int count = 0;

            Map<String, Double> categoryTotals = new HashMap<>();

            for (Expense e : Main.expenses) {

                if (e.date.getYear() == year &&
                        e.date.getMonthValue() == month) {

                    total += e.amount;
                    count++;

                    categoryTotals.put(
                            e.category,
                            categoryTotals.getOrDefault(e.category, 0.0)
                                    + e.amount
                    );
                }
            }

            System.out.println("\n========== MONTHLY REPORT ==========");
            System.out.println("Month: " + month + "/" + year);
            System.out.printf("Monthly Total Spending: Rs. %.2f%n", total);
            System.out.println("Expense Count: " + count);

            double average = count > 0 ? total / count : 0;

            System.out.printf("Average Expense: Rs. %.2f%n", average);

            System.out.println("\n--- Category-wise Breakdown ---");

            if (categoryTotals.isEmpty()) {
                System.out.println("No expenses found for this month.");
            } else {
                for (Map.Entry<String, Double> entry
                        : categoryTotals.entrySet()) {

                    System.out.printf(
                            "%s : Rs. %.2f%n",
                            entry.getKey(),
                            entry.getValue()
                    );
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter valid numeric values!");
        }
    }

    // Yearly Report
    public static void showYearlyReport() {

        try {
            System.out.print("Enter year (e.g., 2026): ");
            int year = Integer.parseInt(Main.sc.nextLine());

            double total = 0;
            int count = 0;

            for (Expense e : Main.expenses) {

                if (e.date.getYear() == year) {
                    total += e.amount;
                    count++;
                }
            }

            System.out.println("\n========== YEARLY REPORT ==========");
            System.out.println("Year: " + year);
            System.out.printf("Yearly Total Spending: Rs. %.2f%n", total);
            System.out.println("Total Expense Count: " + count);

            if (count == 0) {
                System.out.println("No expenses found for this year.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid year!");
        }
    }
}
