/**
 * Library.java
 * Core class that manages the collection of books.
 * Handles all CRUD operations: Add, Display, Search, and Remove.
 * Uses an ArrayList to store Book objects dynamically.
 *
 * Author  : Internship Submission
 * Project : Library Management System
 */

import java.util.ArrayList;
import java.util.List;

public class Library {

    // -------------------------------------------------------
    // Constants
    // -------------------------------------------------------
    private static final String TABLE_HEADER =
            "  +------+--------------------------------+----------------------+-----------------+------+--------------+";
    private static final String TABLE_TITLE =
            "  | ID   | Title                          | Author               | Genre           | Year | Status       |";

    // -------------------------------------------------------
    // Instance Variables
    // -------------------------------------------------------
    private String      libraryName;    // Name of this library
    private List<Book>  bookList;       // Dynamic list of all books
    private int         idCounter;      // Auto-increments to generate unique IDs

    // -------------------------------------------------------
    // Constructor
    // -------------------------------------------------------

    /**
     * Initializes the Library with a name and a few pre-loaded sample books.
     * The sample books give the system realistic data to work with on first run.
     *
     * @param libraryName The display name of this library
     */
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.bookList    = new ArrayList<>();
        this.idCounter   = 1001; // IDs will start at 1001

        // Pre-load some sample books so the library isn't empty on first run
        loadSampleBooks();
    }

    /**
     * Adds a small set of demo books to the library on startup.
     */
    private void loadSampleBooks() {
        bookList.add(new Book(idCounter++, "Clean Code",              "Robert C. Martin",  "Programming",  2008));
        bookList.add(new Book(idCounter++, "The Pragmatic Programmer", "Andrew Hunt",       "Programming",  1999));
        bookList.add(new Book(idCounter++, "Effective Java",           "Joshua Bloch",      "Programming",  2018));
        bookList.add(new Book(idCounter++, "The Alchemist",            "Paulo Coelho",      "Fiction",      1988));
        bookList.add(new Book(idCounter++, "Sapiens",                  "Yuval Noah Harari", "Non-Fiction",  2011));
    }

    // -------------------------------------------------------
    // Getters
    // -------------------------------------------------------

    public String getLibraryName() {
        return libraryName;
    }

    public int getTotalBooks() {
        return bookList.size();
    }

    // -------------------------------------------------------
    // CRUD OPERATION 1: Add Book
    // -------------------------------------------------------

    /**
     * Adds a new book to the library's collection.
     * Validates that the title is not blank and is not a duplicate.
     *
     * @param title  Book title
     * @param author Author name
     * @param genre  Book genre
     * @param year   Publication year
     * @return true if the book was added, false if it was rejected
     */
    public boolean addBook(String title, String author, String genre, int year) {

        // Validation 1: Fields must not be empty
        if (title == null || title.trim().isEmpty() ||
                author == null || author.trim().isEmpty() ||
                genre == null  || genre.trim().isEmpty()) {
            System.out.println("\n  [ERROR] Book details cannot be empty. Please try again.");
            return false;
        }

        // Validation 2: Year must be realistic
        if (year < 1000 || year > 2100) {
            System.out.println("\n  [ERROR] Please enter a valid publication year (1000 - 2100).");
            return false;
        }

        // Validation 3: Prevent duplicate titles (case-insensitive comparison)
        if (isDuplicateTitle(title.trim())) {
            System.out.println("\n  [ERROR] A book with the title \"" + title.trim()
                    + "\" already exists in the library.");
            return false;
        }

        // All checks passed — create and add the book
        Book newBook = new Book(idCounter++, title.trim(), author.trim(), genre.trim(), year);
        bookList.add(newBook);
        return true;
    }

    /**
     * Checks whether a book with the given title already exists.
     * Comparison is case-insensitive to catch variations like
     * "Clean Code" vs "clean code".
     *
     * @param title The title to check
     * @return true if a duplicate exists, false otherwise
     */
    private boolean isDuplicateTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------------------
    // CRUD OPERATION 2: Display All Books
    // -------------------------------------------------------

    /**
     * Prints all books in the library in a formatted table.
     * Shows a message if the library is currently empty.
     */
    public void displayAllBooks() {
        System.out.println();

        if (bookList.isEmpty()) {
            System.out.println("  The library has no books yet. Add some books to get started!");
            return;
        }

        // Print table header
        System.out.println(TABLE_HEADER);
        System.out.println(TABLE_TITLE);
        System.out.println(TABLE_HEADER);

        // Print each book row
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        // Print table footer with total count
        System.out.println(TABLE_HEADER);
        System.out.printf("  Total Books in Library: %d%n%n", bookList.size());
    }

    // -------------------------------------------------------
    // CRUD OPERATION 3: Search Book by Title
    // -------------------------------------------------------

    /**
     * Searches for books whose titles contain the given keyword.
     * The search is case-insensitive and supports partial matches
     * (e.g., searching "code" will find "Clean Code").
     *
     * @param keyword The search term to look for in book titles
     * @return Count of matching books found
     */
    public int searchByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("\n  [ERROR] Search keyword cannot be empty.");
            return 0;
        }

        System.out.println("\n  Searching for: \"" + keyword + "\"...\n");

        List<Book> results = new ArrayList<>();

        // Collect all books where the title contains the keyword
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(keyword.trim().toLowerCase())) {
                results.add(book);
            }
        }

        if (results.isEmpty()) {
            System.out.println("  No books found matching \"" + keyword + "\".");
            System.out.println("  Try a different keyword or check the spelling.");
        } else {
            System.out.println("  Found " + results.size() + " result(s):\n");
            for (Book book : results) {
                book.printDetails(); // Show detailed view for each match
            }
        }

        return results.size();
    }

    // -------------------------------------------------------
    // CRUD OPERATION 4: Remove Book
    // -------------------------------------------------------

    /**
     * Removes a book from the library by its unique Book ID.
     * If no book with that ID exists, an error is shown.
     *
     * @param bookId The ID of the book to remove
     * @return The removed Book object if found, or null if not found
     */
    public Book removeBook(int bookId) {
        Book bookToRemove = findById(bookId);

        if (bookToRemove == null) {
            System.out.println("\n  [ERROR] No book found with ID: " + bookId);
            System.out.println("  Use 'Display All Books' to view valid Book IDs.");
            return null;
        }

        bookList.remove(bookToRemove);
        return bookToRemove;
    }

    /**
     * Finds a book in the list by its unique ID.
     *
     * @param bookId The ID to look for
     * @return The matching Book object, or null if not found
     */
    private Book findById(int bookId) {
        for (Book book : bookList) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    /**
     * Checks if a given Book ID exists in the library.
     * Useful for validating remove operations before proceeding.
     *
     * @param bookId The ID to validate
     * @return true if the book exists, false otherwise
     */
    public boolean bookExists(int bookId) {
        return findById(bookId) != null;
    }
}