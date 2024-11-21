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
    // Instance of the DatabaseManager to manage the collection of books
    private DatabaseManager databaseManager;

    // GUI components
    private JTextField filePathTextField;
    private JButton addBooksButton;
    private JButton listBooksButton;
    private JButton removeByIdButton;
    private JButton removeByTitleButton;
    private JButton checkOutButton;
    private JButton checkInButton;
    private JButton exitButton;

    /**
     * Constructor for LibraryGUIForm
     * Initializes the databaseManager instance and calls initComponents to set up the GUI.
     */
    public LibraryGUIForm() {
        databaseManager = new DatabaseManager();  // Database manager to handle book operations
        initComponents();  // Initialize the GUI components
    }

    /**
     * initComponents
     * Sets up and configures all GUI components including labels, buttons, and text fields.
     */
    private void initComponents() {
        // Frame properties
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Label for file path input
        JLabel filePathLabel = new JLabel("File Path:");
        filePathLabel.setBounds(10, 10, 100, 25);
        add(filePathLabel);

        // Text field for entering file path to load books from
        filePathTextField = new JTextField();
        filePathTextField.setBounds(120, 10, 250, 25);
        add(filePathTextField);

        // Button to add books from the file specified in the text field
        addBooksButton = new JButton("Add Books");
        addBooksButton.setBounds(10, 50, 120, 25);
        add(addBooksButton);
        addBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseManager.addBooksFromFile(filePathTextField.getText());  // Load books from the file
            }
        });

        // Button to list all books in the library
        listBooksButton = new JButton("List Books");
        listBooksButton.setBounds(150, 50, 120, 25);
        add(listBooksButton);
        listBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                databaseManager.listBooks();  // Display list of all books
            }
        });

        // Button to remove a book by ID
        removeByIdButton = new JButton("Remove by ID");
        removeByIdButton.setBounds(10, 90, 120, 25);
        add(removeByIdButton);
        removeByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Enter Book ID to Remove:");
                int id = Integer.parseInt(idStr);
                databaseManager.removeBookById(id);  // Remove book with the specified ID
            }
        });

        // Button to remove a book by title
        removeByTitleButton = new JButton("Remove by Title");
        removeByTitleButton.setBounds(150, 90, 120, 25);
        add(removeByTitleButton);
        removeByTitleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Book Title to Remove:");
                databaseManager.removeBookByTitle(title);  // Remove book with the specified title
            }
        });

        // Button to check out a book by title
        checkOutButton = new JButton("Check Out");
        checkOutButton.setBounds(10, 130, 120, 25);
        add(checkOutButton);
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Book Title to Check Out:");
                databaseManager.checkOutBook(title);  // Mark the book as checked out
            }
        });

        // Button to check in a book by title
        checkInButton = new JButton("Check In");
        checkInButton.setBounds(150, 130, 120, 25);
        add(checkInButton);
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Book Title to Check In:");
                databaseManager.checkInBook(title);  // Mark the book as checked in
            }
        });

        // Exit button to close the application
        exitButton = new JButton("Exit");
        exitButton.setBounds(10, 170, 120, 25);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        });
    }

    /**
     * Main method to launch the Library Management System GUI.
     * This method starts the program and creates the main GUI window.
     * The Library Management System allows users to perform actions such as
     * adding books, checking books in/out, and removing books by ID or title.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryGUIForm form = new LibraryGUIForm();
            form.setVisible(true);  // Display the GUI window
        });
    }
}
