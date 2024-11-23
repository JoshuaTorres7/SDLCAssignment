import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LibraryGUIForm.java
 * Name: [Joshua Torres]
 * Course: [CEN-3024C]
 * Date: [11/3/24]
 *
 * This class represents the GUI for the Library Management System. It allows users to interact with the
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
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Button to list all books
        JButton listBooksButton = new JButton("List Books");
        listBooksButton.setBounds(10, 10, 150, 25);
        add(listBooksButton);
        listBooksButton.addActionListener(e -> databaseManager.listBooks());

        // Button to add a new book
        JButton addBookButton = new JButton("Add Book");
        addBookButton.setBounds(10, 50, 150, 25);
        add(addBookButton);
        addBookButton.addActionListener(e -> {
            String title = getUserInput("Enter Book Title:");
            String author = getUserInput("Enter Book Author:");
            databaseManager.addBook(title, author);
        });

        // Button to remove a book by ID
        JButton removeByIdButton = new JButton("Remove by ID");
        removeByIdButton.setBounds(10, 90, 150, 25);
        add(removeByIdButton);
        removeByIdButton.addActionListener(e -> {
            String idStr = getUserInput("Enter Book ID:");
            try {
                int bookId = Integer.parseInt(idStr);
                databaseManager.removeBookById(bookId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.");
            }
        });

        // Button to remove a book by title
        JButton removeByTitleButton = new JButton("Remove by Title");
        removeByTitleButton.setBounds(10, 130, 150, 25);
        add(removeByTitleButton);
        removeByTitleButton.addActionListener(e -> {
            String title = getUserInput("Enter Book Title:");
            databaseManager.removeBookByTitle(title);
        });

        // Button to check out a book
        JButton checkOutButton = new JButton("Check Out Book");
        checkOutButton.setBounds(200, 10, 150, 25);
        add(checkOutButton);
        checkOutButton.addActionListener(e -> {
            String title = getUserInput("Enter Book Title to Check Out:");
            databaseManager.checkOutBook(title);
        });

        // Button to check in a book
        JButton checkInButton = new JButton("Check In Book");
        checkInButton.setBounds(200, 50, 150, 25);
        add(checkInButton);
        checkInButton.addActionListener(e -> {
            String title = getUserInput("Enter Book Title to Check In:");
            databaseManager.checkInBook(title);
        });

        // Button to exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(200, 90, 150, 25);
        add(exitButton);
        exitButton.addActionListener(e -> {
            int confirmed = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    /**
     * Helper method to get user input from a dialog box.
     * @param message The message to prompt the user with.
     * @return The user's input as a String.
     */
    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    /**
     * The main method to start the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUIForm form = new LibraryGUIForm();
            form.setVisible(true);
        });
    }
}
