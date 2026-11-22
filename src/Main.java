import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.*;

public class Main {

    public static ArrayList<Expense> expenses = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static int id = 1;

    public static final String FILE_NAME = "expenses.dat";

    public static void main(String[] args) {

        loadExpenses();

        int choice;

        do {
            System.out.println("\n========== SMART EXPENSE PATTERN ANALYZER ==========");
            System.out.println("1. Add New Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Category-wise Spending Analysis");
            System.out.println("6. Highest Expense");
            System.out.println("7. Lowest Expense");
            System.out.println("8. Sort Expenses by Date");
            System.out.println("9. Sort Expenses by Amount");
            System.out.println("10. Monthly Spending Summary");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addExpense();
                        break;

                    case 2:
                        viewExpenses();
                        break;

                    case 3:
                        updateExpense();
                        break;

                    case 4:
                        deleteExpense();
                        break;

                    case 5:
                        CategoryAnalysis.showCategoryAnalysis();
                        break;

                    case 6:
                        ExpenseAnalyzer.findHighestExpense();
                        break;

                    case 7:
                        ExpenseAnalyzer.findLowestExpense();
                        break;

                    case 8:
                        System.out.println("Sort by Date module is not connected yet.");
                        break;

                    case 9:
                        System.out.println("Sort by Amount module is not connected yet.");
                        break;

                    case 10:
                        System.out.println("Monthly Report module is not connected yet.");
                        break;

                    case 11:
                        saveExpenses();
                        System.out.println("Thank you for using Smart Expense Pattern Analyzer!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                choice = 0;
            }

        } while (choice != 11);
    }

    // Add New Expense
    public static void addExpense() {

        System.out.print("Enter expense description: ");
        String description = sc.nextLine();

        if (description.trim().isEmpty()) {
            System.out.println("Description cannot be empty!");
            return;
        }

        double amount;

        try {
            System.out.print("Enter amount: ");
            amount = Double.parseDouble(sc.nextLine());

            if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {
                System.out.println("Please enter a valid positive amount!");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount!");
            return;
        }

        LocalDate date;

        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            date = LocalDate.parse(sc.nextLine());

        } catch (Exception e) {
            System.out.println("Invalid date format!");
            return;
        }

        String category = CategoryManager.getCategory(description);

        Expense expense = new Expense(id, description, amount, date, category);

        expenses.add(expense);

        id++;

        saveExpenses();

        System.out.println("Expense added successfully!");
        System.out.println("Automatically Assigned Category: " + category);
    }

    // View All Expenses
    public static void viewExpenses() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available!");
            return;
        }

        System.out.println("\n========== ALL EXPENSES ==========");

        for (Expense expens : expenses) {
            expens.displayExpense();
        }
    }

    // Update Expense
    public static void updateExpense() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available to update!");
            return;
        }

        System.out.print("Enter Expense ID to update: ");

        int searchId;

        try {
            searchId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        for (Expense e : expenses) {

            if (e.id == searchId) {

                System.out.print("Enter new description: ");
                String description = sc.nextLine();

                if (description.trim().isEmpty()) {
                    System.out.println("Description cannot be empty!");
                    return;
                }

                System.out.print("Enter new amount: ");

                double amount;

                try {
                    amount = Double.parseDouble(sc.nextLine());

                    if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {
                        System.out.println("Invalid amount!");
                        return;
                    }

                } catch (NumberFormatException ex) {
                    System.out.println("Invalid amount!");
                    return;
                }

                System.out.print("Enter new date (YYYY-MM-DD): ");

                LocalDate date;

                try {
                    date = LocalDate.parse(sc.nextLine());
                } catch (Exception ex) {
                    System.out.println("Invalid date format!");
                    return;
                }

                e.description = description;
                e.amount = amount;
                e.date = date;

                e.category = CategoryManager.getCategory(description);

                saveExpenses();

                System.out.println("Expense updated successfully!");
                System.out.println("New Category: " + e.category);

                return;
            }
        }

        System.out.println("Expense ID not found!");
    }

    // Delete Expense
    public static void deleteExpense() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available to delete!");
            return;
        }

        System.out.print("Enter Expense ID to delete: ");

        int searchId;

        try {
            searchId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!");
            return;
        }

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == searchId) {

                expenses.remove(i);

                saveExpenses();

                System.out.println("Expense deleted successfully!");

                return;
            }
        }

        System.out.println("Expense ID not found!");
    }

    // Save Expenses in File
    public static void saveExpenses() {

        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            out.writeObject(expenses);

            out.close();

        } catch (IOException e) {
            System.out.println("Error while saving expenses!");
        }
    }

    // Load Expenses from File
    @SuppressWarnings("unchecked")
    public static void loadExpenses() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            expenses = (ArrayList<Expense>) in.readObject();

            in.close();

            for (Expense expens : expenses) {

                if (expens.id >= id) {
                    id = expens.id + 1;
                }
            }

            System.out.println("Previous expenses loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error while loading expenses!");
        }
    }
}