import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserAccount extends ManageAccount {

    public UserAccount(int id) {
        super(id, false);
    }

    @Override
    protected void setupDatabaseConnection() {
        try {
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
    protected void fetchAccountDataFromDB() {
        try {
            String query = "SELECT username, email, password_hash FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password_hash");

                updateUIFields(username, email, password);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching account data: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected String getUpdateQuery(String field) {
        switch (field) {
            case "Change Username":
                return "UPDATE users SET username = ? WHERE user_id = ?";
            case "Change Password":
                return "UPDATE users SET password_hash = ? WHERE user_id = ?";
            case "Change Email":
                return "UPDATE users SET email = ? WHERE user_id = ?";
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