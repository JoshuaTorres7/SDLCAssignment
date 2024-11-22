import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * DatabaseManager.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [11/11/24]
 *
 * This class manages the interaction with the database for the Library Management System.
 * It connects to the MySQL database and provides methods to list, add, and remove books
 * in the library database.
 */
public class DatabaseManager {

    private Connection connection;

    // Establish connection to the database
    public Connection connect() {
        if (connection != null) {
            return connection; // Return existing connection if already established
        }
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver"); // Loads the MySQL driver class
            // Connect to the database using the DriverManager
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "JMTF");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null; // Return null if the connection fails
        }
        return connection; // Return the established connection
    }

    // List all books in the database
    public void listBooks() {
        Connection connection = connect(); // Call the connect method to establish a database connection
        if (connection == null) {
            System.err.println("Database connection failed!"); // Error handling if connection is null
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Execute a query to fetch all books
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                // Iterate through the results and print each book's details
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String status = resultSet.getString("status");
                String dueDate = resultSet.getString("due_date");
                System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author +
                        ", Status: " + status + ", Due Date: " + dueDate); // Displaying book information
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential SQL errors
        }
    }

    // Add a new book
    public void addBook(String title, String author) {
        Connection connection = connect(); // Ensure connection to the database
        if (connection == null) {
            System.err.println("Database connection failed!");
            return; // Stop execution if connection fails
        }
        try (Statement stmt = connection.createStatement()) {
            // Insert the new book into the database with default status "available" and no due date
            String query = "INSERT INTO books (title, author, status, due_date) VALUES ('" +
                    title + "', '" + author + "', 'available', NULL)";
            stmt.executeUpdate(query); // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace(); // Catch any SQL errors during the insertion process
        }
    }

    // Remove a book by ID
    public void removeBookById(int id) {
        Connection connection = connect(); // Establish connection
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Delete the book with the given ID from the database
            String query = "DELETE FROM books WHERE id = " + id;
            stmt.executeUpdate(query); // Execute the delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Handle any SQL-related errors during deletion
        }
    }

    // Remove a book by title
    public void removeBookByTitle(String title) {
        Connection connection = connect(); // Ensure connection
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Delete the book with the given title from the database
            String query = "DELETE FROM books WHERE title = '" + title + "'";
            stmt.executeUpdate(query); // Execute the delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL errors
        }
    }

    // Check out a book by title (set status to 'checked out' and due date to 4 weeks from now)
    public void checkOutBook(String title) {
        Connection connection = connect(); // Establish connection
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Check if the book is already checked out
            String checkQuery = "SELECT status FROM books WHERE title = '" + title + "'";
            ResultSet resultSet = stmt.executeQuery(checkQuery);
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                if ("checked out".equals(status)) {
                    System.out.println("Error: This book is already checked out.");
                    return; // Stop further execution if the book is already checked out
                }
            }

            // Update the book's status to 'checked out' and set due date to 4 weeks from today
            String query = "UPDATE books SET status = 'checked out', due_date = DATE_ADD(CURDATE(), INTERVAL 4 WEEK) WHERE title = '" + title + "'";
            stmt.executeUpdate(query); // Execute the update query
            System.out.println("The book '" + title + "' has been checked out successfully.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL errors during checkout
        }
    }

    // Check in a book by title (set status to 'available' and due date to NULL)
    public void checkInBook(String title) {
        Connection connection = connect(); // Ensure connection
        if (connection == null) {
            System.err.println("Database connection failed!");
            return;
        }
        try (Statement stmt = connection.createStatement()) {
            // Check if the book exists in the database
            String checkQuery = "SELECT status FROM books WHERE title = '" + title + "'";
            ResultSet resultSet = stmt.executeQuery(checkQuery);
            if (resultSet.next()) {
                String status = resultSet.getString("status");
                if ("available".equals(status)) {
                    System.out.println("Error: This book is already checked in.");
                    return; // Stop execution if the book is already checked in
                }
            } else {
                System.out.println("Error: The book with the given title does not exist.");
                return; // Handle the case where the book doesn't exist
            }

            // Update the book's status to 'available' and set due date to NULL
            String query = "UPDATE books SET status = 'available', due_date = NULL WHERE title = '" + title + "'";
            stmt.executeUpdate(query); // Execute the update query
            System.out.println("The book '" + title + "' has been checked in successfully.");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle errors during the check-in process
        }
    }
}
