import javax.swing.*;
import java.awt.*;
import java.sql.*;

public abstract class ManageAccount extends JPanel {
    protected Connection connection;
    protected JTextField usernameField;
    protected JTextField emailField;
    protected JTextField passwordField;
    protected int id;
    protected boolean isAdmin;

    public ManageAccount(int id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;

        // Set up the layout to null (custom positioning)
        setLayout(null);
        setPreferredSize(new Dimension(550, 500));

        // Icon and header
        ImageIcon icon = new ImageIcon(getClass().getResource("/user.png"));
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImg);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setBounds(225, 20, 100, 100);
        add(iconLabel);

        // Username row
        JLabel usernameLabel = createLabel("User Name");
        usernameLabel.setBounds(50, 130, 100, 25);
        add(usernameLabel);

        usernameField = createTextField("");
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

        emailField = createTextField("");
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

        // Establish a database connection
        setupDatabaseConnection();

        // Retrieve account data from the database
        fetchAccountDataFromDB();
    }

    protected JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    protected JTextField createTextField(String text) {
        return new JTextField(text, 15);
    }

    protected JButton createChangeButton(String tooltip) {
        JButton button = new JButton("Change");
        button.setToolTipText(tooltip);
        button.addActionListener(e -> handleChange(tooltip));
        return button;
    }

    protected void handleChange(String field) {
        String newValue = JOptionPane.showInputDialog(this, "Enter new value for " + field, "Update " + field,
                JOptionPane.PLAIN_MESSAGE);
        if (newValue != null && !newValue.trim().isEmpty()) {
            try {
                String updateQuery = getUpdateQuery(field);
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, newValue);
                preparedStatement.setInt(2, id);
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(this, field + " updated successfully.");
                    fetchAccountDataFromDB(); // Refresh data
                } else {
                    JOptionPane.showMessageDialog(this, "No rows updated. Please check input.", "Update Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error updating " + field + ": " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected abstract void setupDatabaseConnection();

    protected abstract void fetchAccountDataFromDB();

    protected abstract String getUpdateQuery(String field);

    protected void updateUIFields(String username, String email, String password) {
        usernameField.setText(username);
        emailField.setText(email);
        passwordField.setText("********");
    }

    private void closeWindow() {
        SwingUtilities.getWindowAncestor(this).dispose();
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