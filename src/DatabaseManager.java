import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;

    // Connect to the database
    public Connection connect() {
        if (connection != null) {
            return connection;  // Return the existing connection if already established
        }
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "JMTF");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;  // Return null if the connection cannot be established
        }
        return connection;
    }

    // List all books from the database
    public void listBooks() {
        Connection connection = connect();  // Ensure connection is established first
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;  // Exit the method if connection is not established
        }
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a book to the database
    public void addBook(String title, String author) {
        Connection connection = connect();  // Ensure connection is established
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO books (title, author) VALUES ('" + title + "', '" + author + "')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a book by ID
    public void removeBookById(int id) {
        Connection connection = connect();  // Ensure connection is established
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM books WHERE id = " + id;
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a book by title
    public void removeBookByTitle(String title) {
        Connection connection = connect();  // Ensure connection is established
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM books WHERE title = '" + title + "'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check out a book
    public void checkOutBook(String title) {
        Connection connection = connect();  // Ensure connection is established
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            String query = "UPDATE books SET status = 'checked out' WHERE title = '" + title + "'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check in a book
    public void checkInBook(String title) {
        Connection connection = connect();  // Ensure connection is established
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try {
            Statement stmt = connection.createStatement();
            String query = "UPDATE books SET status = 'available' WHERE title = '" + title + "'";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
