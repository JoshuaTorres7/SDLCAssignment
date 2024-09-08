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
    private ArrayList<Book> books = new ArrayList<>();

    /**
     * Adds books to the library collection from a specified file.
     * @param filePath The path to the text file containing book details.
     */
    public void addBooksFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                books.add(new Book(id, title, author));
            }
            System.out.println("Books added successfully.");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Removes a book from the collection by its ID.
     * @param id The ID of the book to remove.
     */
    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed successfully.");
    }

    /**
     * Removes a book from the collection by its title.
     * @param title The title of the book to remove.
     */
    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed successfully.");
    }

    /**
     * Checks out a book by its title. Changes the book's status and sets the due date.
     * @param title The title of the book to check out.
     */
    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if ("checked out".equals(book.getStatus())) {
                    System.out.println("Error: Book is already checked out.");
                    return;
                }
                book.setStatus("checked out");
                book.setDueDate(LocalDate.now().plusWeeks(4)); // Set due date to 4 weeks from now
                System.out.println("Book checked out successfully. Due date is " + book.getDueDate());
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    /**
     * Checks in a book by its title. Changes the book's status to "checked in" and clears the due date.
     * @param title The title of the book to check in.
     */
    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setStatus("checked in"); // Change status to "checked in"
                book.setDueDate(null); // Clear due date
                System.out.println("Book checked in successfully. Status is now 'checked in'. Due date is now null.");
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    /**
     * Lists all books currently in the collection.
     */
    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the collection.");
        } else {
            books.forEach(book -> System.out.println(book));
        }
    }
}
