import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Library.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This class manages the collection of books in the Library Management System.
 * It provides methods to add books from a file, remove a book by its ID or title,
 * check out and check in books, and list all books.
 */
public class Library {
    private ArrayList<Book> books = new ArrayList<>(); // Stores the collection of books

    /**
     * Adds books to the library collection from a specified file.
     * @param filePath The path to the text file containing book details.
     */
    public void addBooksFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // Split line into parts by comma
                int id = Integer.parseInt(parts[0].trim()); // Parse book ID
                String title = parts[1].trim(); // Extract book title
                String author = parts[2].trim(); // Extract book author
                books.add(new Book(id, title, author)); // Add book to collection
            }
            System.out.println("Books added successfully.");
        } catch (IOException e) {
            // Handle file reading errors
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Removes a book from the collection by its ID.
     * @param id The ID of the book to remove.
     */
    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id); // Remove book with matching ID
        System.out.println("Book removed successfully.");
    }

    /**
     * Removes a book from the collection by its title.
     * @param title The title of the book to remove.
     */
    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title)); // Remove book with matching title
        System.out.println("Book removed successfully.");
    }

    /**
     * Checks out a book by its title. Updates its status and sets the due date.
     * @param title The title of the book to check out.
     */
    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) { // Find book with matching title
                if ("checked out".equals(book.getStatus())) { // Check if already checked out
                    System.out.println("Error: Book is already checked out.");
                    return;
                }
                book.setStatus("checked out"); // Update status to "checked out"
                book.setDueDate(LocalDate.now().plusWeeks(4)); // Set due date to 4 weeks from today
                System.out.println("Book checked out successfully. Due date is " + book.getDueDate());
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found."); // Book not found in collection
    }

    /**
     * Checks in a book by its title. Updates its status and clears the due date.
     * @param title The title of the book to check in.
     */
    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) { // Find book with matching title
                book.setStatus("checked in"); // Update status to "checked in"
                book.setDueDate(null); // Clear due date
                System.out.println("Book checked in successfully. Status is now 'checked in'. Due date is now null.");
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found."); // Book not found in collection
    }

    /**
     * Lists all books currently in the collection.
     */
    public void listBooks() {
        if (books.isEmpty()) { // Check if collection is empty
            System.out.println("No books in the collection.");
        } else {
            books.forEach(System.out::println); // Print details of each book
        }
    }

    /**
     * Returns the list of books in the library.
     * @return A list of all books.
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
}
