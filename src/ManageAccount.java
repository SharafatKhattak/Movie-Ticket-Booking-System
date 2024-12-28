import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ManageAccount extends JPanel {
    private Connection connection;
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField passwordField;
    private int id;

    public ManageAccount(int id) {
        this.id = id; // Store the user ID

        // Set up the frame properties
        JFrame frame = new JFrame("Manage Account");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setResizable(false); // Disable resizing
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Establish a database connection
        setupDatabaseConnection();

        // Retrieve user data from the database
        String username = "", email = "", passwordHash = "";
        try {
            String query = "SELECT username, email, password_hash FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id); // Use the id parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                username = resultSet.getString("username");
                email = resultSet.getString("email");
                passwordHash = resultSet.getString("password_hash");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching user data: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Set up the layout to null (custom positioning)
        setLayout(null);
        setPreferredSize(new Dimension(550, 500));

        // Icon and header
        // Load and scale the image
        ImageIcon icon = new ImageIcon(getClass().getResource("/user.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Resize the image to 100x100 pixels
        icon = new ImageIcon(scaledImg); // Create a new ImageIcon with the scaled image

        // Set up the JLabel with the scaled image
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(225, 20, 100, 100); // Manually set bounds (x, y, width, height)
        add(iconLabel);

        // Username row
        JLabel usernameLabel = createLabel("User Name");
        usernameLabel.setBounds(50, 130, 100, 25); // x, y, width, height
        add(usernameLabel);

        usernameField = createTextField(username);
        usernameField.setBounds(150, 130, 200, 25);
        usernameField.setEditable(false);
        add(usernameField);

        JButton changeUsernameButton = createChangeButton("Change Username");
        changeUsernameButton.setBounds(360, 130, 120, 25);
        add(changeUsernameButton);

        // Password row
        JLabel passwordLabel = createLabel("Password");
        passwordLabel.setBounds(50, 180, 100, 25);
        add(passwordLabel);

        passwordField = createTextField("********");
        passwordField.setBounds(150, 180, 200, 25);
        passwordField.setEditable(false);
        add(passwordField);

        JButton changePasswordButton = createChangeButton("Change Password");
        changePasswordButton.setBounds(360, 180, 120, 25);
        add(changePasswordButton);

        // Email row
        JLabel emailLabel = createLabel("Email");
        emailLabel.setBounds(50, 230, 100, 25);
        add(emailLabel);

        emailField = createTextField(email);
        emailField.setBounds(150, 230, 200, 25);
        emailField.setEditable(false);
        add(emailField);

        JButton changeEmailButton = createChangeButton("Change Email");
        changeEmailButton.setBounds(360, 230, 120, 25);
        add(changeEmailButton);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(200, 300, 120, 30);
        closeButton.addActionListener(e -> closeWindow());
        add(closeButton);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createTextField(String text) {
        return new JTextField(text, 15);
    }

    private JButton createChangeButton(String tooltip) {
        JButton button = new JButton("Change");
        button.setToolTipText(tooltip);
        button.addActionListener(e -> handleChange(tooltip));
        return button;
    }

    private void handleChange(String field) {
        String newValue = JOptionPane.showInputDialog(this, "Enter new value for " + field, "Update " + field,
                JOptionPane.PLAIN_MESSAGE);
        if (newValue != null && !newValue.trim().isEmpty()) {
            try {
                String updateQuery = "";
                switch (field) {
                    case "Change Username":
                        updateQuery = "UPDATE users SET username = ? WHERE user_id = ?";
                        break;
                    case "Change Password":
                        String hashedPassword = hashPassword(newValue); // Hash the password
                        updateQuery = "UPDATE users SET password_hash = ? WHERE user_id = ?";
                        break;
                    case "Change Email":
                        updateQuery = "UPDATE users SET email = ? WHERE user_id = ?";
                        break;
                }

                if (!updateQuery.isEmpty()) {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setInt(2, id); // Use the user ID
                    int rowsUpdated = preparedStatement.executeUpdate();

                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, field + " updated successfully.");
                        fetchUserDataFromDB(); // Refresh data after update
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update " + field, "Database Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating " + field + ": " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String hashPassword(String password) {
        // Simple password hashing (use a secure method like bcrypt in production)
        return Integer.toHexString(password.hashCode());
    }

    private void fetchUserDataFromDB() {
        // Refresh user data from the database after update
        try {
            String query = "SELECT username, email, password_hash FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String passwordHash = resultSet.getString("password_hash");

                // Update the UI components with the new data
                updateUIFields(username, email, passwordHash);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching updated user data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateUIFields(String username, String email, String passwordHash) {
        // Update the JTextField values with the new data from the database
        usernameField.setText(username);
        emailField.setText(email);
        passwordField.setText("********"); // Hide password value
    }

    private void closeWindow() {
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    private void setupDatabaseConnection() {
        try {
            // Modify the database URL, username, and password as needed
            String dbUrl = "jdbc:mysql://localhost:3306/moviebeats";
            String dbUsername = "root";
            String dbPassword = "sharafat@321";
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
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
