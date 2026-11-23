import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date range filter: selected date range ke expenses aur unka total.
 *
 * ASSUMPTION: model.Expense has getAmount() -> double and getDate() -> java.time.LocalDate.
 */
public class DateFilterService {

    // Expenses between from and to (both inclusive)
    public List<Expense> filterByDateRange(List<Expense> expenses, LocalDate from, LocalDate to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
        return expenses.stream()
                .filter(e -> !e.date.isBefore(from) && !e.date.isAfter(to))
                .collect(Collectors.toList());
    }

    // Total of expenses in the selected range
    public double getTotalInRange(List<Expense> expenses, LocalDate from, LocalDate to) {
        return filterByDateRange(expenses, from, to).stream()
                .mapToDouble(e -> e.amount)
                .sum();
    }

    public void printRangeSummary(List<Expense> expenses, LocalDate from, LocalDate to) {
        List<Expense> filtered = filterByDateRange(expenses, from, to);
        System.out.println("===== Expenses from " + from + " to " + to + " =====");
        if (filtered.isEmpty()) {
            System.out.println("No expenses found in this range.");
            return;
        }
        filtered.forEach(Expense::displayExpense);
        System.out.printf("Total: %.2f (%d expenses)%n", getTotalInRange(expenses, from, to), filtered.size());
    }
}