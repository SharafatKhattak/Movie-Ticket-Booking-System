import javax.swing.*;
import java.sql.*;

public class AdminAccount extends ManageAccount {

    public AdminAccount(int id) {
        super(id, true);
    }

    @Override
    protected void setupDatabaseConnection() {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/moviebeats";
            String dbUsername = "root";
            String dbPassword = "sharafat@321";
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void fetchAccountDataFromDB() {
        try {
            String query = "SELECT username, email, password FROM admins WHERE id = ?";
            System.out.println("Executing query: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Data retrieved successfully!");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                System.out.println("Username: " + username + ", Email: " + email + ", Password: " + password);

                updateUIFields(username, email, password);
            } else {
                System.out.println("No data found for ID: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching account data: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void updateUIFields(String username, String email, String password) {
        SwingUtilities.invokeLater(() -> {
            usernameField.setText(username);
            emailField.setText(email);
            passwordField.setText(password);
        });
    }

    @Override
    protected String getUpdateQuery(String field) {
        switch (field) {
            case "Change Username":
                return "UPDATE admins SET username = ? WHERE id = ?";
            case "Change Password":
                return "UPDATE admins SET password = ? WHERE id = ?";
            case "Change Email":
                return "UPDATE admins SET email = ? WHERE id = ?";
            default:
                return "";
        }
    }


    @Override
    public void setVisible(boolean visible) {
        JFrame frame = new JFrame("Manage Account");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setResizable(false); // Disable resizing
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(visible);
    }
}