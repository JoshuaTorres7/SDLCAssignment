import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * LibraryManagementSystem.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This is the main class for the Library Management System. It allows the
 * librarian to manage the library's collection of books by adding, removing,
 * checking out, and checking in books through a simple text-based menu system.
 */
public class LibraryManagementSystem {
    /**
     * Main method that runs the Library Management System. It presents a menu to
     * the librarian to perform various operations like adding, removing, and
     * managing books in the system.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Library library = new Library(); // Initialize the Library object
        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input

        while (true) {
            // Display the menu options to the librarian
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Books from File");
            System.out.println("2. Remove Book by ID");
            System.out.println("3. Remove Book by Title");
            System.out.println("4. Check Out Book by Title");
            System.out.println("5. Check In Book by Title");
            System.out.println("6. List All Books");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt(); // Get the user's choice
                scanner.nextLine();  // Consume the newline character after the integer input

                switch (choice) {
                    case 1:
                        // Add books from a file specified by the librarian
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine();
                        library.addBooksFromFile(filePath); // Add books to the library from the file
                        break;
                    case 2:
                        // Remove a book by its unique ID
                        System.out.print("Enter book ID to remove: ");
                        int id = scanner.nextInt();
                        library.removeBookById(id); // Remove book by ID
                        break;
                    case 3:
                        // Remove a book by its title
                        System.out.print("Enter book title to remove: ");
                        String titleToRemove = scanner.nextLine();
                        library.removeBookByTitle(titleToRemove); // Remove book by title
                        break;
                    case 4:
                        // Check out a book by its title
                        System.out.print("Enter book title to check out: ");
                        String titleToCheckOut = scanner.nextLine();
                        library.checkOutBook(titleToCheckOut); // Check out the book
                        break;
                    case 5:
                        // Check in a book by its title
                        System.out.print("Enter book title to check in: ");
                        String titleToCheckIn = scanner.nextLine();
                        library.checkInBook(titleToCheckIn); // Check in the book
                        break;
                    case 6:
                        // List all books currently in the library
                        library.listBooks(); // Display all books
                        break;
                    case 7:
                        // Exit the program
                        System.out.println("Exiting... Thank you for using the Library Management System.");
                        return; // Exit the program
                    default:
                        // Handle invalid option input
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle invalid input types (e.g., user enters a letter instead of a number)
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the scanner buffer to prevent infinite loop
            }
        }
    }
}
