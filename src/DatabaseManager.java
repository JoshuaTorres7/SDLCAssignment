import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * DatabaseManager.java
 * Name: [Joshua Torre]
 * Course: [CEN-3024C]
 * Date: [11/11/24]
 *
 * This class manages the interaction with the database for the Library Management System.
 * It connects to the MySQL database and provides methods to list, add, and remove books
 * in the library database.
 */
public class DatabaseManager {
    // Database connection properties
    private static final String URL = "jdbc:mysql://localhost:3306/?user=root"; // Database URL
    private static final String USER = "root"; // Database username
    private static final String PASSWORD = "JMTF"; // Database password (empty for local MySQL setup)
    private Connection connection; // Connection object to interact with the database

    /**
     * Connects to the database.
     * This method establishes a connection to the MySQL database using JDBC.
     *
     * @throws SQLException if a database access error occurs.
     */
    public void connect() throws SQLException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Handle errors if connection fails
            System.out.println("Database connection failed!");
            throw new SQLException("Failed to connect to database", e);
        }
    }

    /**
     * Lists all books from the library database.
     * This method retrieves all records from the books table and displays them.
     */
    public void listBooks() {
        // SQL query to select all books from the database
        String query = "SELECT * FROM books";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Iterate through the result set and print the books
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") + ", Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing books: " + e.getMessage());
        }
    }

    /**
     * Adds books from a file to the database.
     * This method adds books to the database by reading the provided file path.
     * (This method is currently a placeholder for adding file-reading functionality.)
     *
     * @param filePath The path to the file that contains book details.
     */
    public void addBooksFromFile(String filePath) {
        // Placeholder for file-reading and inserting book records
        // Actual implementation would read file content and insert records into the database.
        System.out.println("Adding books from file: " + filePath);
    }

    /**
     * Removes a book from the database by its ID.
     * This method removes a book based on the provided book ID.
     *
     * @param id The ID of the book to remove.
     */
    public void removeBookById(int id) {
        String query = "DELETE FROM books WHERE id = " + id;

        try (Statement stmt = connection.createStatement()) {
            // Execute the delete query
            stmt.executeUpdate(query);
            System.out.println("Book with ID " + id + " removed.");
        } catch (SQLException e) {
            System.out.println("Error removing book by ID: " + e.getMessage());
        }
    }

    /**
     * Removes a book from the database by its title.
     * This method removes a book based on the provided book title.
     *
     * @param title The title of the book to remove.
     */
    public void removeBookByTitle(String title) {
        String query = "DELETE FROM books WHERE title = '" + title + "'";

        try (Statement stmt = connection.createStatement()) {
            // Execute the delete query
            stmt.executeUpdate(query);
            System.out.println("Book with title '" + title + "' removed.");
        } catch (SQLException e) {
            System.out.println("Error removing book by title: " + e.getMessage());
        }
    }

    /**
     * Checks out a book by its title.
     * This method marks a book as checked out in the database.
     *
     * @param title The title of the book to check out.
     */
    public void checkOutBook(String title) {
        String query = "UPDATE books SET status = 'Checked Out' WHERE title = '" + title + "'";

        try (Statement stmt = connection.createStatement()) {
            // Execute the update query
            stmt.executeUpdate(query);
            System.out.println("Book with title '" + title + "' checked out.");
        } catch (SQLException e) {
            System.out.println("Error checking out book: " + e.getMessage());
        }
    }

    /**
     * Checks in a book by its title.
     * This method marks a book as checked in in the database.
     *
     * @param title The title of the book to check in.
     */
    public void checkInBook(String title) {
        String query = "UPDATE books SET status = 'Available' WHERE title = '" + title + "'";

        try (Statement stmt = connection.createStatement()) {
            // Execute the update query
            stmt.executeUpdate(query);
            System.out.println("Book with title '" + title + "' checked in.");
        } catch (SQLException e) {
            System.out.println("Error checking in book: " + e.getMessage());
        }
    }
}
