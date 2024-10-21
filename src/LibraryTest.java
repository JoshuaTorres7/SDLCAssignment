import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.addBooksFromFile("testsrc/test_books.txt"); // Ensure this file exists for your tests
    }

    @Test
    void testAddBooks() {
        // Check the number of books after adding
        assertEquals(3, library.getBooks().size()); // Adjust based on your test setup
    }

    @Test
    void testRemoveBookById() {
        library.removeBookById(1); // Assuming a book with ID 1 exists
        assertEquals(2, library.getBooks().size()); // Adjust based on your test setup
    }

    @Test
    void testRemoveBookByTitle() {
        library.removeBookByTitle("1984"); // Use an existing title from your setup
        assertEquals(2, library.getBooks().size()); // Adjust based on your test setup
    }

    @Test
    void testCheckOutBook() {
        library.checkOutBook("1984"); // Use an existing title from your setup
        Book book = library.getBooks().get(0); // Get the first book (or adjust to get the specific one)
        assertEquals("checked out", book.getStatus());
        assertNotNull(book.getDueDate());
    }

    @Test
    void testCheckInBook() {
        library.checkInBook("1984"); // Use an existing title from your setup
        Book book = library.getBooks().get(0); // Get the first book (or adjust to get the specific one)
        assertEquals("checked in", book.getStatus());
        assertNull(book.getDueDate());
    }
}
