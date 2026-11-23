# Smart Expense Pattern Analyzer

A console-based Java application for tracking daily expenses and understanding where the money goes. Expenses are saved to disk between runs, grouped into categories automatically, and summarised by month and year. You can also set a monthly budget and get warned when you go over it.

Built as a group project for Programming in Java (25CSE0202), B.E. CSE, 3rd semester, Chitkara University.

## What it does

- Add, view, update and delete expenses
- Assigns a category automatically from the description
- Category-wise spending breakdown
- Highest and lowest expense
- Sort by date or amount
- Search by expense ID or category
- Monthly and yearly reports
- Monthly budgets, with a report showing spent, remaining and percentage used
- Warning when a month goes over budget, including right after adding an expense
- Total spending between any two dates

## Running it

You need JDK 17 or newer.

```bash
git clone https://github.com/arshiarora00-web/Expense_analyzer.git
cd Expense_analyzer

javac -d out src/*.java
java -cp out Main
```

Expenses are stored in `expenses.dat` and budgets in `budgets.dat`, both created in the folder you run the program from.

## Menu

```
1.  Add New Expense                  10. Monthly Report
2.  View All Expenses                11. Search Expense by ID
3.  Update Expense                   12. Search Expenses by Category
4.  Delete Expense                   13. Yearly Report
5.  Category-wise Spending Analysis  14. Set Monthly Budget
6.  Highest Expense                  15. Budget Report
7.  Lowest Expense                   16. Total Expenses for Date Range
8.  Sort Expenses by Date            17. Exit
9.  Sort Expenses by Amount
```

Dates are entered as `YYYY-MM-DD`, and months as `YYYY-MM`.

### Budget example

```
Enter your choice: 15
Enter month (YYYY-MM): 2026-11
===== Budget Report: 2026-11 =====
Budget    : 5000.00
Spent     : 6000.00 (120.0%)
Remaining : -1000.00
WARNING: Budget exceeded! Budget: 5000.00, Spent: 6000.00, Over by: 1000.00
```

Below the limit but past 80%, you get a softer heads-up instead of the over-budget warning.

## Project layout

```
src/
  Main.java                    menu and input handling
  Expense.java                 the expense record
  CategoryManager.java         picks a category from the description
  CategoryAnalysis.java        category-wise breakdown
  ExpenseAnalyzer.java         highest / lowest expense
  SortingSearching.java        sorting and search
  MonthlyYearlyReports.java    monthly and yearly reports
  BudgetService.java           budgets, comparison, remaining amount, warnings
  DateFilterService.java       date range filter and total
  BudgetExceededException.java
```

## Java concepts used

OOP, collections (`ArrayList`, streams), custom exceptions, exception handling, file handling with object serialization, and the `java.time` API.

## Team

| Name | Roll no. |
|---|---|
| Arshia Arora | 2510990441 |
| Sanchi | 2510990450 |
| Sakshi | 2510990133 |
| Vrinda | 2510990187 |

## Possible next steps

A GUI (JavaFX or Swing), spending charts, simple prediction from past months, and multi-user login.

For academic use, Chitkara University, CSE Department.
