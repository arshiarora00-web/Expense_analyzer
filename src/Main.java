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

        int choice = 0;

        do {

            System.out.println("\n========================================");
            System.out.println("       SMART EXPENSE PATTERN ANALYZER");
            System.out.println("========================================");

            System.out.println("1. Add New Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. Update Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Category-wise Spending");
            System.out.println("6. Highest Expense");
            System.out.println("7. Lowest Expense");
            System.out.println("8. Sort Expenses by Date");
            System.out.println("9. Sort Expenses by Amount");
            System.out.println("10. Monthly Spending Summary");
            System.out.println("11. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                choice = Integer.parseInt(sc.nextLine());

            } catch (Exception e) {

                System.out.println("Invalid input! Enter a number.");
                continue;
            }

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
                    System.out.println("Category-wise Spending Module");
                    break;

                case 6:
                    System.out.println("Highest Expense Module");
                    break;

                case 7:
                    System.out.println("Lowest Expense Module");
                    break;

                case 8:
                    System.out.println("Sort Expenses by Date Module");
                    break;

                case 9:
                    System.out.println("Sort Expenses by Amount Module");
                    break;

                case 10:
                    System.out.println("Monthly Spending Summary Module");
                    break;

                case 11:
                    System.out.println("\nThank you for using Smart Expense Pattern Analyzer!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 11);

    }


    // ADD NEW EXPENSE

    public static void addExpense() {

        System.out.print("\nEnter expense description: ");

        String description = sc.nextLine();

        if (description.trim().isEmpty()) {

            System.out.println("Description cannot be empty!");
            return;
        }

        System.out.print("Enter amount: ");

        double amount;

        try {

            amount = Double.parseDouble(sc.nextLine());

            if (amount <= 0 || Double.isInfinite(amount) || Double.isNaN(amount)) {

                System.out.println("Amount must be greater than zero!");
                return;
            }

        } catch (Exception e) {

            System.out.println("Invalid amount!");
            return;
        }

        System.out.print("Enter date (yyyy-mm-dd): ");

        LocalDate date;

        try {

            date = LocalDate.parse(sc.nextLine());

        } catch (Exception e) {

            System.out.println("Invalid date! Use yyyy-mm-dd format.");
            return;
        }

        // AUTOMATIC CATEGORIZATION

        String category = CategoryManager.getCategory(description);

        Expense e = new Expense(id, description, amount, date, category);

        expenses.add(e);

        id++;

        saveExpenses();

        System.out.println("\nExpense added successfully!");
        System.out.println("Assigned Category: " + category);
    }


    // VIEW ALL EXPENSES

    public static void viewExpenses() {

        if (expenses.isEmpty()) {

            System.out.println("\nNo expenses found!");

        } else {

            System.out.println("\n========== ALL EXPENSES ==========");

            for (Expense expens : expenses) {

                expens.displayExpense();

            }

            System.out.println("-----------------------------------");
        }
    }


    // UPDATE EXPENSE

    public static void updateExpense() {

        if (expenses.isEmpty()) {

            System.out.println("\nNo expenses available to update!");
            return;
        }

        System.out.print("\nEnter Expense ID to update: ");

        int searchId;

        try {

            searchId = Integer.parseInt(sc.nextLine());

        } catch (Exception e) {

            System.out.println("Invalid ID!");
            return;
        }

        for (Expense e : expenses) {

            if (e.id == searchId) {

                System.out.println("\nExpense Found!");

                e.displayExpense();

                System.out.print("\nEnter new description: ");

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

                } catch (Exception ex) {

                    System.out.println("Invalid amount!");
                    return;
                }

                System.out.print("Enter new date (yyyy-mm-dd): ");

                LocalDate date;

                try {

                    date = LocalDate.parse(sc.nextLine());

                } catch (Exception ex) {

                    System.out.println("Invalid date!");
                    return;
                }

                e.description = description;
                e.amount = amount;
                e.date = date;

                // UPDATE CATEGORY AUTOMATICALLY

                e.category = CategoryManager.getCategory(description);

                saveExpenses();

                System.out.println("\nExpense updated successfully!");
                System.out.println("New Category: " + e.category);

                return;
            }
        }

        System.out.println("\nExpense ID not found!");
    }


    // DELETE EXPENSE

    public static void deleteExpense() {

        if (expenses.isEmpty()) {

            System.out.println("\nNo expenses available to delete!");
            return;
        }

        System.out.print("\nEnter Expense ID to delete: ");

        int searchId;

        try {

            searchId = Integer.parseInt(sc.nextLine());

        } catch (Exception e) {

            System.out.println("Invalid ID!");
            return;
        }

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == searchId) {

                expenses.remove(i);

                saveExpenses();

                System.out.println("\nExpense deleted successfully!");

                return;
            }
        }

        System.out.println("\nExpense ID not found!");
    }


    // SAVE EXPENSES TO FILE

    public static void saveExpenses() {

        try {

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME)
            );

            out.writeObject(expenses);

            out.close();

        } catch (IOException e) {

            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }


    // LOAD EXPENSES FROM FILE

    @SuppressWarnings("unchecked")
    public static void loadExpenses() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {

            System.out.println("No previous expense records found.");

            return;
        }

        try {

            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(FILE_NAME)
            );

            expenses = (ArrayList<Expense>) in.readObject();

            in.close();

            for (Expense expens : expenses) {

                if (expens.id >= id) {

                    id = expens.id + 1;
                }
            }

            System.out.println("Previous expenses loaded successfully!");

        } catch (Exception e) {

            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

}