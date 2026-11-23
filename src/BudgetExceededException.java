/**
 * Thrown when spending for a month goes beyond the budget that was set.
 */
public class BudgetExceededException extends Exception {
    private final double budget;
    private final double spent;

    public BudgetExceededException(double budget, double spent) {
        super(String.format("Budget exceeded! Budget: %.2f, Spent: %.2f, Over by: %.2f",
                budget, spent, spent - budget));
        this.budget = budget;
        this.spent = spent;
    }

    public double getBudget() { return budget; }
    public double getSpent()  { return spent; }
    public double getExcess() { return spent - budget; }
}