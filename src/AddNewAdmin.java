import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class AddNewAdmin extends JPanel {
    private Connection connection;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public AddNewAdmin() {
        // Set up the layout to null (custom positioning)
        setLayout(null);
        setSize(550, 500);

        // Icon and header
        ImageIcon icon = new ImageIcon(getClass().getResource("/user.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(225, 20, 100, 100);
        add(iconLabel);

        // Username row
        JLabel usernameLabel = createLabel("Enter New Admin Username");
        usernameLabel.setBounds(50, 130, 200, 25);
        add(usernameLabel);

        usernameField = createTextField("");
        usernameField.setBounds(250, 130, 200, 25);
        add(usernameField);

        // Password row
        JLabel passwordLabel = createLabel("Enter New Admin Password");
        passwordLabel.setBounds(50, 180, 200, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 180, 200, 25);
        add(passwordField);

        // Email row
        JLabel emailLabel = createLabel("Enter New Admin Email");
        emailLabel.setBounds(50, 230, 200, 25);
        add(emailLabel);

        emailField = createTextField("");
        emailField.setBounds(250, 230, 200, 25);
        add(emailField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(200, 300, 120, 30);
        saveButton.addActionListener(e -> handleSave());
        add(saveButton);

        // Establish a database connection
        setupDatabaseConnection();

    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField(String text) {
        return new JTextField(text, 15);
    }

    private void handleSave() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String email = emailField.getText().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save data to the database
        try {
            String insertQuery = "INSERT INTO admins (username, password, email, created_at) VALUES (?, ?, ?, NOW())";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Admin added successfully.");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add admin.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
    }

    private void setupDatabaseConnection() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/moviebeats";
            String dbUsername = "root";
            String dbPassword = "sharafat@321";
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
