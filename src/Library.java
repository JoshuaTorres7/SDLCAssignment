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

    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed successfully.");
    }

    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed successfully.");
    }

    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if ("checked out".equals(book.getStatus())) {
                    System.out.println("Error: Book is already checked out.");
                    return;
                }
                book.setStatus("checked out");
                book.setDueDate(LocalDate.now().plusWeeks(4));
                System.out.println("Book checked out successfully. Due date is " + book.getDueDate());
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.setStatus("checked in");
                book.setDueDate(null);
                System.out.println("Book checked in successfully. Status is now 'checked in'. Due date is now null.");
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the collection.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}
