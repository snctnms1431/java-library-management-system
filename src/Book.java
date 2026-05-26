/**
 * Book.java
 * Represents a single book in the Library Management System.
 * Demonstrates OOP principles: encapsulation, constructor usage,
 * and meaningful toString() formatting.
 *
 * Author  : Internship Submission
 * Project : Library Management System
 */

public class Book {

    // -------------------------------------------------------
    // Instance Variables (Private — Encapsulation)
    // -------------------------------------------------------
    private int    bookId;      // Unique auto-generated ID
    private String title;       // Title of the book
    private String author;      // Author's full name
    private String genre;       // Genre/category (e.g., Fiction, Science)
    private int    year;        // Year of publication
    private boolean available;  // Availability status (true = in shelf)

    // -------------------------------------------------------
    // Constructor
    // -------------------------------------------------------

    /**
     * Creates a new Book with all required details.
     * Availability is set to true by default (book is in the library).
     *
     * @param bookId  Auto-generated unique ID
     * @param title   Book title
     * @param author  Author name
     * @param genre   Book genre or category
     * @param year    Publication year
     */
    public Book(int bookId, String title, String author, String genre, int year) {
        this.bookId    = bookId;
        this.title     = title;
        this.author    = author;
        this.genre     = genre;
        this.year      = year;
        this.available = true; // Every newly added book is available
    }

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // -------------------------------------------------------
    // Display Utility
    // -------------------------------------------------------

    /**
     * Returns a cleanly formatted single-line representation of the book.
     * Used when displaying books in a table-style list in the console.
     */
    @Override
    public String toString() {
        String status = available ? "Available" : "Checked Out";
        return String.format(
                "  | %-4d | %-30s | %-20s | %-15s | %-4d | %-12s |",
                bookId, title, author, genre, year, status
        );
    }

    /**
     * Prints a detailed view of this book's information.
     * Used in search results for a richer display.
     */
    public void printDetails() {
        String divider = "  +----------------------------------------------------------+";
        System.out.println(divider);
        System.out.println("  |  BOOK DETAILS                                            |");
        System.out.println(divider);
        System.out.printf("  |  Book ID    : %-42d|%n", bookId);
        System.out.printf("  |  Title      : %-42s|%n", title);
        System.out.printf("  |  Author     : %-42s|%n", author);
        System.out.printf("  |  Genre      : %-42s|%n", genre);
        System.out.printf("  |  Published  : %-42d|%n", year);
        System.out.printf("  |  Status     : %-42s|%n", (available ? "Available" : "Checked Out"));
        System.out.println(divider);
    }
}