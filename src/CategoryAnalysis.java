public class CategoryAnalysis {

    public static void showCategoryAnalysis() {

        if (Main.expenses.isEmpty()) {
            System.out.println("No expenses available for analysis.");
            return;
        }

        double food = 0;
        double transportation = 0;
        double shopping = 0;
        double education = 0;
        double entertainment = 0;
        double healthcare = 0;
        double other = 0;

        double total = 0;

        for (int i = 0; i < Main.expenses.size(); i++) {

            Expense e = Main.expenses.get(i);

            total = total + e.amount;

            if (e.category.equals("Food")) {
                food = food + e.amount;
            }
            else if (e.category.equals("Transportation")) {
                transportation = transportation + e.amount;
            }
            else if (e.category.equals("Shopping")) {
                shopping = shopping + e.amount;
            }
            else if (e.category.equals("Education")) {
                education = education + e.amount;
            }
            else if (e.category.equals("Entertainment")) {
                entertainment = entertainment + e.amount;
            }
            else if (e.category.equals("Healthcare")) {
                healthcare = healthcare + e.amount;
            }
            else {
                other = other + e.amount;
            }
        }

        System.out.println("\n========== CATEGORY ANALYSIS ==========");

        System.out.println("Food: Rs. " + food);
        System.out.println("Transportation: Rs. " + transportation);
        System.out.println("Shopping: Rs. " + shopping);
        System.out.println("Education: Rs. " + education);
        System.out.println("Entertainment: Rs. " + entertainment);
        System.out.println("Healthcare: Rs. " + healthcare);
        System.out.println("Other: Rs. " + other);

        System.out.println("---------------------------------------");
        System.out.println("Total Spending: Rs. " + total);

        double highest = food;
        String highestCategory = "Food";

        if (transportation > highest) {
            highest = transportation;
            highestCategory = "Transportation";
        }

        if (shopping > highest) {
            highest = shopping;
            highestCategory = "Shopping";
        }

        if (education > highest) {
            highest = education;
            highestCategory = "Education";
        }

        if (entertainment > highest) {
            highest = entertainment;
            highestCategory = "Entertainment";
        }

        if (healthcare > highest) {
            highest = healthcare;
            highestCategory = "Healthcare";
        }

        if (other > highest) {
            highest = other;
            highestCategory = "Other";
        }

        System.out.println("---------------------------------------");
        System.out.println("Highest Spending Category: " + highestCategory);
        System.out.println("Highest Category Spending: Rs. " + highest);

        System.out.println("=======================================");
    }
}