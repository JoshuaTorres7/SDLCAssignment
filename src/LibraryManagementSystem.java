import java.util.Scanner;

/**
 * LibraryManagementSystem.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This is the main class for the Library Management System. It allows the
 * librarian to manage the library's collection of books by adding, removing,
 * and listing books.
 */
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Books from File");
            System.out.println("2. Remove Book by ID");
            System.out.println("3. List All Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();
                    library.addBooksFromFile(filePath);
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    int id = scanner.nextInt();
                    library.removeBookById(id);
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
