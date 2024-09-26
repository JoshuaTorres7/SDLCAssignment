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
 * checking out, and checking in books.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library(); // Initialize the Library
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        while (true) {
            // Display the menu options
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
                int choice = scanner.nextInt(); // Read user choice
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine(); // Get file path from user
                        library.addBooksFromFile(filePath); // Add books from file
                        break;
                    case 2:
                        System.out.print("Enter book ID to remove: ");
                        int id = scanner.nextInt(); // Get book ID from user
                        library.removeBookById(id); // Remove the book by ID
                        break;
                    case 3:
                        System.out.print("Enter book title to remove: ");
                        String titleToRemove = scanner.nextLine(); // Get title from user
                        library.removeBookByTitle(titleToRemove); // Remove the book by title
                        break;
                    case 4:
                        System.out.print("Enter book title to check out: ");
                        String titleToCheckOut = scanner.nextLine(); // Get title from user
                        library.checkOutBook(titleToCheckOut); // Check out the book
                        break;
                    case 5:
                        System.out.print("Enter book title to check in: ");
                        String titleToCheckIn = scanner.nextLine(); // Get title from user
                        library.checkInBook(titleToCheckIn); // Check in the book
                        break;
                    case 6:
                        library.listBooks(); // List all books
                        break;
                    case 7:
                        System.out.println("Exiting..."); // Exit message
                        return; // Exit the program
                    default:
                        System.out.println("Invalid option. Please try again."); // Handle invalid input
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number."); // Handle invalid input
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
