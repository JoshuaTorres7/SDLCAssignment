import java.sql.*;

/**
 * DatabaseManager.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [11/11/24]
 *
 * This class manages database operations for the Library Management System.
 */
public class DatabaseManager {

    private Connection connection;

    public DatabaseManager() {
        connect(); // Initialize database connection
    }

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return The connection object if successful, or null if the connection fails.
     */
    public Connection connect() {
        try {
            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database (Update with your database URL and password)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC", "root", "your_password");

            return connection; // Successful connection
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Connection failed
        }
    }

    /**
     * Lists all books in the database.
     */
    public void listBooks() {
        try {
            String query = "SELECT * FROM books";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("title") + " by " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds books from a file to the database (implementation depends on file structure).
     *
     * @param filePath The file path containing book information.
     */
    public void addBooksFromFile(String filePath) {
        // Implement the logic to add books from the file
        // For now, it's a placeholder method.
    }

    /**
     * Removes a book from the database by its ID.
     *
     * @param id The ID of the book to remove.
     */
    public void removeBookById(int id) {
        try {
            String query = "DELETE FROM books WHERE barcode = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Book with ID " + id + " removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a book from the database by its title.
     *
     * @param title The title of the book to remove.
     */
    public void removeBookByTitle(String title) {
        try {
            String query = "DELETE FROM books WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Book with title " + title + " removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a book as checked out by updating its status in the database.
     *
     * @param title The title of the book to check out.
     */
    public void checkOutBook(String title) {
        try {
            String query = "UPDATE books SET status = 'checked out', due_date = CURDATE() WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Book " + title + " checked out.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks a book as checked in by updating its status in the database.
     *
     * @param title The title of the book to check in.
     */
    public void checkInBook(String title) {
        try {
            String query = "UPDATE books SET status = 'checked in', due_date = NULL WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Book " + title + " checked in.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
