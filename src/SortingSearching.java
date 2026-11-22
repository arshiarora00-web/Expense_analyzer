import java.util.Collections;
import java.util.Comparator;

public class SortingSearching {
    public static void sortByDate() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        Collections.sort(Main.expenses,
                Comparator.comparing(e -> e.date));

        System.out.println("Expenses sorted by date:");

        for (Expense e : Main.expenses) {
            e.displayExpense();
        }
    }

    public static void sortByAmount() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        Collections.sort(Main.expenses,
                Comparator.comparingDouble(e -> e.amount));

        System.out.println("Expenses sorted by amount:");

        for (Expense e : Main.expenses) {
            e.displayExpense();
        }
    }

    public static void searchById(int searchId) {

        boolean found = false;

        for (Expense e : Main.expenses) {

            if (e.id == searchId) {

                System.out.println("Expense Found!");
                e.displayExpense();

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Expense not found!");
        }
    }

    public static void searchByCategory(String searchCategory) {

        boolean found = false;

        for (Expense e : Main.expenses) {

            if (e.category.equalsIgnoreCase(searchCategory)) {

                e.displayExpense();

                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category!");
        }
    }
}
