import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LibraryGUIForm.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [11/3/24]
 *
 * This class represents the GUI for the Library Management System, allowing users to interact with the
 * library functionalities such as adding books, listing books, checking books in/out, and removing books by ID or title.
 */
public class LibraryGUIForm extends JFrame {

    private DatabaseManager databaseManager; // Instance of DatabaseManager to handle database operations

    /**
     * Constructor for the LibraryGUIForm class. It initializes the database manager and sets up the GUI components.
     */
    public LibraryGUIForm() {
        databaseManager = new DatabaseManager(); // Initialize the database manager
        initComponents(); // Call method to initialize GUI components
    }

    /**
     * Initializes the GUI components such as buttons and their event listeners.
     */
    private void initComponents() {
        // Setting up the window (JFrame) for the Library Management System
        setTitle("Library Management System"); // Set the window title
        setSize(400, 300); // Set the window size
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Define the close operation
        setLayout(null); // Use null layout for absolute positioning of components

        // Button to list all books in the database
        JButton listBooksButton = new JButton("List Books");
        listBooksButton.setBounds(10, 10, 150, 25); // Set position and size of the button
        add(listBooksButton); // Add the button to the frame
        listBooksButton.addActionListener(e -> databaseManager.listBooks()); // Action listener to list books

        // Button to add a new book to the database
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(10, 50, 150, 25); // Set position and size of the button
        add(addBookButton); // Add the button to the frame
        addBookButton.addActionListener(e -> {
            // Prompt user to enter book title and author
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Book Author:");
            databaseManager.addBook(title, author); // Call method to add the book to the database
        });

        // Button to remove a book from the database by its ID
        JButton removeByIdButton = new JButton("Remove by ID");
        removeByIdButton.setBounds(10, 90, 150, 25); // Set position and size of the button
        add(removeByIdButton); // Add the button to the frame
        removeByIdButton.addActionListener(e -> {
            // Prompt user to enter book ID to remove
            String idStr = JOptionPane.showInputDialog("Enter Book ID:");
            try {
                int bookId = Integer.parseInt(idStr); // Parse the ID to an integer
                databaseManager.removeBookById(bookId); // Call method to remove the book by ID
            } catch (NumberFormatException ex) {
                // Handle invalid ID format
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.");
            }
        });

        // Button to remove a book from the database by its title
        JButton removeByTitleButton = new JButton("Remove by Title");
        removeByTitleButton.setBounds(10, 130, 150, 25); // Set position and size of the button
        add(removeByTitleButton); // Add the button to the frame
        removeByTitleButton.addActionListener(e -> {
            // Prompt user to enter book title to remove
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            databaseManager.removeBookByTitle(title); // Call method to remove the book by title
        });

        // Button to check out a book from the database
        JButton checkOutButton = new JButton("Check Out Book");
        checkOutButton.setBounds(200, 10, 150, 25); // Set position and size of the button
        add(checkOutButton); // Add the button to the frame
        checkOutButton.addActionListener(e -> {
            // Prompt user to enter book title to check out
            String title = JOptionPane.showInputDialog("Enter Book Title to Check Out:");
            databaseManager.checkOutBook(title); // Call method to check out the book
        });

        // Button to check in a book to the database
        JButton checkInButton = new JButton("Check In Book");
        checkInButton.setBounds(200, 50, 150, 25); // Set position and size of the button
        add(checkInButton); // Add the button to the frame
        checkInButton.addActionListener(e -> {
            // Prompt user to enter book title to check in
            String title = JOptionPane.showInputDialog("Enter Book Title to Check In:");
            databaseManager.checkInBook(title); // Call method to check in the book
        });

        // Button to exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(200, 90, 150, 25); // Set position and size of the button
        add(exitButton); // Add the button to the frame
        exitButton.addActionListener(e -> System.exit(0)); // Close the application when clicked
    }

    /**
     * The main method to start the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and display the LibraryGUIForm
            LibraryGUIForm form = new LibraryGUIForm();
            form.setVisible(true);
        });
    }
}