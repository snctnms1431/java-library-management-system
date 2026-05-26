/**
 * Main.java
 * Entry point and menu-driven interface for the Library Management System.
 * Handles all user interactions via a console menu and delegates
 * operations to the Library class.
 *
 * Author  : Internship Submission
 * Project : Library Management System
 *
 * HOW TO COMPILE AND RUN:
 *   javac *.java
 *   java Main
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // -------------------------------------------------------
    // Constants
    // -------------------------------------------------------
    private static final String LINE =
            "  ================================================================";
    private static final String THIN_LINE =
            "  ----------------------------------------------------------------";

    // -------------------------------------------------------
    // Main Method
    // -------------------------------------------------------

    public static void main(String[] args) {

        // Create the library instance
        Library library = new Library("Java Public Library");
        Scanner scanner  = new Scanner(System.in);

        // Display the application header
        displayHeader(library.getLibraryName());

        // Main application loop
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntInput(scanner, "  Enter your choice: ");

            System.out.println();

            switch (choice) {
                case 1:
                    handleAddBook(scanner, library);
                    break;
                case 2:
                    handleDisplayBooks(library);
                    break;
                case 3:
                    handleSearchBook(scanner, library);
                    break;
                case 4:
                    handleRemoveBook(scanner, library);
                    break;
                case 5:
                    running = false;
                    displayExitMessage();
                    break;
                default:
                    System.out.println("  [!] Invalid choice. Please select a number from 1 to 5.");
                    System.out.println();
            }
        }

        scanner.close();
    }

    // -------------------------------------------------------
    // Menu Display
    // -------------------------------------------------------

    /**
     * Prints the application header banner on startup.
     *
     * @param libraryName The name of the library to show in the banner
     */
    private static void displayHeader(String libraryName) {
        System.out.println();
        System.out.println(LINE);
        System.out.println("  ||                                                            ||");
        System.out.println("  ||          LIBRARY MANAGEMENT SYSTEM  v1.0                  ||");
        System.out.printf( "  ||          %-50s||%n", libraryName);
        System.out.println("  ||                                                            ||");
        System.out.println(LINE);
        System.out.println("  System initialized. Sample books have been pre-loaded.");
        System.out.println();
    }

    /**
     * Prints the main menu options.
     */
    private static void displayMenu() {
        System.out.println(LINE);
        System.out.println("  ||                      MAIN MENU                            ||");
        System.out.println(LINE);
        System.out.println("  ||  [1]  Add New Book                                        ||");
        System.out.println("  ||  [2]  Display All Books                                   ||");
        System.out.println("  ||  [3]  Search Book by Title                                ||");
        System.out.println("  ||  [4]  Remove Book                                         ||");
        System.out.println("  ||  [5]  Exit                                                ||");
        System.out.println(LINE);
        System.out.print("  Enter your choice: ");
    }

    /**
     * Displays a goodbye banner when the user exits.
     */
    private static void displayExitMessage() {
        System.out.println(LINE);
        System.out.println("  ||   Thank you for using the Library Management System.     ||");
        System.out.println("  ||   Goodbye!                                               ||");
        System.out.println(LINE);
        System.out.println();
    }

    // -------------------------------------------------------
    // HANDLER: Add Book
    // -------------------------------------------------------

    /**
     * Collects book details from the user and adds the book to the library.
     * Validates inputs and shows a confirmation message on success.
     *
     * @param scanner The Scanner for user input
     * @param library The Library instance to add the book to
     */
    private static void handleAddBook(Scanner scanner, Library library) {
        System.out.println(LINE);
        System.out.println("  ADD NEW BOOK");
        System.out.println(LINE);

        System.out.print("  Enter Book Title  : ");
        String title = scanner.nextLine().trim();

        System.out.print("  Enter Author Name : ");
        String author = scanner.nextLine().trim();

        System.out.print("  Enter Genre       : ");
        String genre = scanner.nextLine().trim();

        int year = readIntInput(scanner, "  Enter Pub. Year  : ");

        System.out.println();

        // Attempt to add — Library.addBook() handles validation internally
        boolean added = library.addBook(title, author, genre, year);

        if (added) {
            System.out.println(THIN_LINE);
            System.out.println("  [SUCCESS] Book \"" + title + "\" has been added successfully!");
            System.out.printf("  Library now contains %d book(s).%n", library.getTotalBooks());
            System.out.println(THIN_LINE);
        }

        System.out.println();
        pause(scanner);
    }

    // -------------------------------------------------------
    // HANDLER: Display All Books
    // -------------------------------------------------------

    /**
     * Triggers the display of all books in a formatted table.
     *
     * @param library The Library instance to fetch books from
     */
    private static void handleDisplayBooks(Library library) {
        System.out.println(LINE);
        System.out.println("  ALL BOOKS IN LIBRARY");
        System.out.println(LINE);
        library.displayAllBooks();
    }

    // -------------------------------------------------------
    // HANDLER: Search Book
    // -------------------------------------------------------

    /**
     * Reads a search keyword from the user and displays matching books.
     *
     * @param scanner The Scanner for user input
     * @param library The Library instance to search in
     */
    private static void handleSearchBook(Scanner scanner, Library library) {
        System.out.println(LINE);
        System.out.println("  SEARCH BOOK BY TITLE");
        System.out.println(LINE);
        System.out.print("  Enter title keyword to search: ");
        String keyword = scanner.nextLine().trim();

        library.searchByTitle(keyword);

        System.out.println();
        pause(scanner);
    }

    // -------------------------------------------------------
    // HANDLER: Remove Book
    // -------------------------------------------------------

    /**
     * Reads a Book ID from the user, confirms the removal,
     * and removes the book if it exists.
     *
     * @param scanner The Scanner for user input
     * @param library The Library instance to remove from
     */
    private static void handleRemoveBook(Scanner scanner, Library library) {
        System.out.println(LINE);
        System.out.println("  REMOVE BOOK");
        System.out.println(LINE);

        // Show the list so the user knows which ID to enter
        System.out.println("  Current books in library:");
        library.displayAllBooks();

        int bookId = readIntInput(scanner, "  Enter Book ID to remove: ");

        // Check if the ID actually exists before asking for confirmation
        if (!library.bookExists(bookId)) {
            System.out.println("\n  [ERROR] No book found with ID: " + bookId);
            System.out.println("  Please check the Book ID and try again.\n");
            pause(scanner);
            return;
        }

        // Ask for confirmation before deleting
        System.out.print("\n  Are you sure you want to remove Book ID " + bookId + "? (yes/no): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            Book removed = library.removeBook(bookId);
            if (removed != null) {
                System.out.println();
                System.out.println(THIN_LINE);
                System.out.println("  [SUCCESS] Book \"" + removed.getTitle() + "\" has been removed.");
                System.out.printf("  Library now contains %d book(s).%n", library.getTotalBooks());
                System.out.println(THIN_LINE);
            }
        } else {
            System.out.println("\n  [INFO] Remove operation cancelled. No changes were made.");
        }

        System.out.println();
        pause(scanner);
    }

    // -------------------------------------------------------
    // Input Helper Methods
    // -------------------------------------------------------

    /**
     * Reads a valid integer from the console.
     * Handles non-numeric input gracefully by returning -1.
     *
     * @param scanner The Scanner to read from
     * @param prompt  The message to display before reading input
     * @return Integer entered, or -1 on invalid input
     */
    private static int readIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        try {
            int value = scanner.nextInt();
            scanner.nextLine(); // Flush the trailing newline
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear the invalid token
            System.out.println("  [!] Invalid input. Please enter a number.");
            return -1;
        }
    }

    /**
     * Waits for the user to press Enter before returning to the main menu.
     * Keeps the console easy to read by pausing between operations.
     *
     * @param scanner The Scanner to read the Enter key from
     */
    private static void pause(Scanner scanner) {
        System.out.print("  Press Enter to return to the main menu...");
        scanner.nextLine();
        System.out.println();
    }
}