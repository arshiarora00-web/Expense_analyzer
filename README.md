# 💸 Smart Expense Pattern Analyzer

A console-based Java application that records daily expenses, stores them in a database, and analyzes spending patterns (category-wise totals, monthly trends, top spends, and overspending alerts).

> **Course:** Programming in Java (25CSE0202) | **B.E. CSE, 3rd Sem** | Chitkara University
> **Evaluation:** Continuous Evaluation – Project Milestone 3
> **Type:** Group Project

## 👥 Team

| Member | Roll No |
|---|---|
| `Arshia Arora` | `2510990441` |
| `Sanchi` | `2510990450` |
| `Sakshi` | `2510990133` |
| `Vrinda` | `2510990187` |

---

## ✨ Features

- Add, view, update and delete expenses (full CRUD)
- Category-wise and month-wise spending summary
- Top-N highest expenses and average daily spend
- Budget limit per category with overspending alerts
- Sort and filter by date, amount or category
- Import / export expenses from CSV files
- Background auto-save and report generation using threads

---

## 🧩 Syllabus Concepts Used

| Syllabus Topic | Where It Is Used |
|---|---|
| **OOP** (classes, objects, constructors, inheritance, polymorphism, abstraction/interfaces) | `Expense`, `RecurringExpense extends Expense`, `Analyzer` interface, `Category` |
| **Strings & StringBuilder** | Formatting reports, parsing CSV lines, input validation |
| **Wrapper classes** | Parsing amounts/dates (`Double.parseDouble`, `Integer.valueOf`) |
| **Exception Handling** (try/catch/finally, throw/throws, custom exceptions) | `InvalidExpenseException`, `BudgetExceededException` |
| **Collections Framework** (ArrayList, LinkedList, HashMap, LinkedHashMap, TreeMap, HashSet, TreeSet, PriorityQueue) | Expense list, category totals (`HashMap`), sorted monthly report (`TreeMap`), top-N spends (`PriorityQueue`), unique categories (`TreeSet`) |
| **Iterator / ListIterator, Comparable / Comparator, Collections utility** | Sorting by amount/date, safe removal while iterating |
| **Multithreading** (Thread, Runnable, sleep, join, synchronization, wait/notify) | Auto-save thread, report-generator thread, producer-consumer for import |
| **JDBC** (DriverManager, Connection, PreparedStatement, ResultSet) | `ExpenseDAO` with CRUD on SQLite/MySQL |
| **Lambda & Stream API** (filter, map, sorted, distinct, limit, skip) | Filtering, grouping and summarizing expenses |
| **Generics** (generic classes/methods, bounded types) | `Repository<T>`, generic `Utils.printList()` |
| **File Handling** (FileReader/Writer, streams, CSV read-write) | CSV import/export, report files |

---

## 🗂️ Project Structure

```
SmartExpenseAnalyzer/
├── src/
│   ├── model/        # Expense, RecurringExpense, Category
│   ├── dao/          # ExpenseDAO (JDBC), DBConnection
│   ├── service/      # AnalyzerService (streams, collections)
│   ├── thread/       # AutoSaveTask, ReportTask
│   ├── exception/    # InvalidExpenseException, BudgetExceededException
│   ├── util/         # CsvHandler, Repository<T>, Validator
│   └── Main.java     # Menu-driven entry point
├── data/             # sample CSV files
├── lib/              # JDBC driver (.jar)
└── README.md
```

---

## ⚙️ Setup & Run

**Requirements:** JDK 17+, SQLite/MySQL JDBC driver (placed in `lib/`)

```bash
git clone https://github.com/<your-username>/smart-expense-pattern-analyzer.git
cd smart-expense-pattern-analyzer

# compile
javac -cp "lib/*" -d out $(find src -name "*.java")

# run
java -cp "out:lib/*" Main        # use ";" instead of ":" on Windows
```

---

## 🖥️ Sample Menu

```
===== SMART EXPENSE PATTERN ANALYZER =====
1. Add Expense          5. Category Summary
2. View All             6. Monthly Trend
3. Update Expense       7. Top 5 Expenses
4. Delete Expense       8. Import / Export CSV
9. Set Budget           0. Exit
```

---

## 📊 Sample Output

```
Category Summary
Food ........ ₹4,250
Travel ...... ₹2,100
Shopping .... ₹3,800
⚠ Alert: Shopping budget exceeded by ₹800
```

---

## 🔮 Future Scope

- GUI using JavaFX / Swing
- Charts for spending trends
- Spending prediction using moving averages
- Multi-user login

---

## 📄 License

For academic use – Chitkara University, CSE Department.
