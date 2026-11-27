public class SortingSearching {

    // Sort Expenses by Date (Ascending)
    public static void sortByDate() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        for (int i = 0; i < Main.expenses.size() - 1; i++) {

            for (int j = i + 1; j < Main.expenses.size(); j++) {

                if (Main.expenses.get(i).date.isAfter(Main.expenses.get(j).date)) {

                    Expense temp = Main.expenses.get(i);

                    Main.expenses.set(i, Main.expenses.get(j));

                    Main.expenses.set(j, temp);
                }
            }
        }

        System.out.println("\nExpenses sorted by date:");

        for (int i = 0; i < Main.expenses.size(); i++) {
            Main.expenses.get(i).displayExpense();
        }
    }


    // Sort Expenses by Amount (Ascending)
    public static void sortByAmount() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        for (int i = 0; i < Main.expenses.size() - 1; i++) {

            for (int j = i + 1; j < Main.expenses.size(); j++) {

                if (Main.expenses.get(i).amount > Main.expenses.get(j).amount) {

                    Expense temp = Main.expenses.get(i);

                    Main.expenses.set(i, Main.expenses.get(j));

                    Main.expenses.set(j, temp);
                }
            }
        }

        System.out.println("\nExpenses sorted by amount:");

        for (int i = 0; i < Main.expenses.size(); i++) {
            Main.expenses.get(i).displayExpense();
        }
    }


    // Search Expense by ID
    public static void searchById(int searchId) {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        boolean found = false;

        for (int i = 0; i < Main.expenses.size(); i++) {

            if (Main.expenses.get(i).id == searchId) {

                System.out.println("Expense Found!");

                Main.expenses.get(i).displayExpense();

                found = true;

                break;
            }
        }

        if (!found) {
            System.out.println("Expense not found!");
        }
    }


    // Search Expenses by Category
    public static void searchByCategory(String searchCategory) {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        if (searchCategory == null || searchCategory.trim().isEmpty()) {
            System.out.println("Category cannot be empty!");
            return;
        }

        boolean found = false;

        for (int i = 0; i < Main.expenses.size(); i++) {

            if (Main.expenses.get(i).category != null &&
                    Main.expenses.get(i).category.equalsIgnoreCase(searchCategory.trim())) {

                Main.expenses.get(i).displayExpense();

                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category!");
        }
    }
}