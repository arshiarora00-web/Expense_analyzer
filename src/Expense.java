import java.time.LocalDate;
import java.io.Serializable;
import java.io.Serial;

public class Expense implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public int id;
    public String description;
    public double amount;
    public LocalDate date;
    public String category;

    public Expense(int id, String description, double amount,
                   LocalDate date, String category) {

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public void displayExpense() {

        System.out.println("-----------------------------------");
        System.out.println("Expense ID: " + id);
        System.out.println("Description: " + description);
        System.out.println("Amount: Rs. " + amount);
        System.out.println("Date: " + date);
        System.out.println("Category: " + category);
    }
}