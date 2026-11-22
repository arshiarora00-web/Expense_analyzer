public class ExpenseAnalyzer {

    // Find Highest Expense
    public static void findHighestExpense() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        Expense highest = Main.expenses.get(0);

        for (int i = 1; i < Main.expenses.size(); i++) {

            Expense e = Main.expenses.get(i);

            if (e.amount > highest.amount) {
                highest = e;
            }
        }

        System.out.println("\n========== HIGHEST EXPENSE ==========");
        highest.displayExpense();
    }

    // Find Lowest Expense
    public static void findLowestExpense() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        Expense lowest = Main.expenses.get(0);

        for (int i = 1; i < Main.expenses.size(); i++) {

            Expense e = Main.expenses.get(i);

            if (e.amount < lowest.amount) {
                lowest = e;
            }
        }

        System.out.println("\n========== LOWEST EXPENSE ==========");
        lowest.displayExpense();
    }
}