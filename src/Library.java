import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Library.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [8/30/24]
 *
 * This class manages the collection of books in the Library Management System.
 * It provides methods to add books from a file, remove a book by its ID, and list all books.
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
