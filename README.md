# 📚 Library Management System

A console-based **Library Management System** built with **Core Java** as part of a Java Programming Internship project.
Demonstrates OOP concepts, ArrayList-based CRUD operations, input validation, and clean console UI design.

---

## 🖥️ Sample Output

```
  ================================================================
  ||                                                            ||
  ||          LIBRARY MANAGEMENT SYSTEM  v1.0                  ||
  ||          Java Public Library                              ||
  ||                                                            ||
  ================================================================
  System initialized. Sample books have been pre-loaded.

  ================================================================
  ||                      MAIN MENU                            ||
  ================================================================
  ||  [1]  Add New Book                                        ||
  ||  [2]  Display All Books                                   ||
  ||  [3]  Search Book by Title                               ||
  ||  [4]  Remove Book                                         ||
  ||  [5]  Exit                                                ||
  ================================================================

  +------+--------------------------------+----------------------+-----------------+------+--------------+
  | ID   | Title                          | Author               | Genre           | Year | Status       |
  +------+--------------------------------+----------------------+-----------------+------+--------------+
  | 1001 | Clean Code                     | Robert C. Martin     | Programming     | 2008 | Available    |
  | 1002 | The Pragmatic Programmer       | Andrew Hunt          | Programming     | 1999 | Available    |
  | 1003 | Effective Java                 | Joshua Bloch         | Programming     | 2018 | Available    |
  | 1004 | The Alchemist                  | Paulo Coelho         | Fiction         | 1988 | Available    |
  | 1005 | Sapiens                        | Yuval Noah Harari    | Non-Fiction     | 2011 | Available    |
  +------+--------------------------------+----------------------+-----------------+------+--------------+
  Total Books in Library: 5
```

---

## ✨ Features

- ✅ Add new books with auto-generated IDs
- ✅ Display all books in a formatted table
- ✅ Search books by title (partial, case-insensitive)
- ✅ Remove books with confirmation prompt
- ✅ Duplicate book prevention
- ✅ Input validation for all fields
- ✅ Pre-loaded sample books on startup
- ✅ Live book count after each operation

---

## 🗂️ Project Structure

```
LibraryManagement/
├── Book.java       → Book entity class (encapsulation, toString, printDetails)
├── Library.java    → Core logic: add, display, search, remove using ArrayList
└── Main.java       → Menu-driven UI, user input handling, switch-case
```

---

## ⚙️ Class & Method Overview

### `Book.java`
| Method | Description |
|---|---|
| `Book(id, title, author, genre, year)` | Constructor — initializes a book |
| `getTitle()`, `getAuthor()`, etc. | Getters for encapsulated fields |
| `toString()` | Returns one-line table row format |
| `printDetails()` | Prints detailed box-style view |

### `Library.java`
| Method | Description |
|---|---|
| `addBook(title, author, genre, year)` | Validates and adds a book |
| `displayAllBooks()` | Prints all books in a table |
| `searchByTitle(keyword)` | Partial-match, case-insensitive search |
| `removeBook(bookId)` | Removes a book by ID |
| `isDuplicateTitle(title)` | Prevents adding the same book twice |

### `Main.java`
| Method | Description |
|---|---|
| `main()` | Entry point — runs the menu loop |
| `handleAddBook()` | Collects input and calls library.addBook() |
| `handleDisplayBooks()` | Calls library.displayAllBooks() |
| `handleSearchBook()` | Collects keyword and calls library.searchByTitle() |
| `handleRemoveBook()` | Gets ID, confirms, calls library.removeBook() |
| `readIntInput()` | Safe integer input with error handling |
| `pause()` | Waits for Enter key before returning to menu |

---

## 🚀 How to Compile and Run

### Prerequisites
- Java JDK 8 or higher installed
- A terminal / command prompt

### Steps

```bash
# 1. Navigate to the project folder
cd LibraryManagement

# 2. Compile all Java files
javac *.java

# 3. Run the application
java Main
```

---

## 🛠️ Technologies Used

| Technology | Usage |
|---|---|
| Core Java | Language |
| `ArrayList` | Dynamic book storage |
| `Scanner` | Console user input |
| OOP (Encapsulation) | Book and Library class design |
| `switch-case` | Menu navigation |
| String methods | Search and duplicate detection |

---

## 📸 Suggested Screenshots for GitHub/LinkedIn

1. **Main Menu** — the formatted menu display on startup
2. **Display All Books** — the full table view
3. **Search Result** — detailed box view of a found book
4. **Add Book Success** — confirmation message after adding
5. **Remove Confirmation** — the yes/no prompt before removal

---

## 📄 License

This project is open-source and free to use for learning purposes.

---

> 💡 Built as part of a **Java Programming Internship** — demonstrates OOP, CRUD, and console UI design.
