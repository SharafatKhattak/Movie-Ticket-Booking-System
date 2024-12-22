import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtils {

    // Method to establish connection to the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String username = "root";
        String password = "sharafat@321";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to save user data into the database
    public static void saveUser(String username, String email, String passwordHash) {
        String insertSQL = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, username);       // Set username
            pstmt.setString(2, email);          // Set email
            pstmt.setString(3, passwordHash);   // Set hashed password

            pstmt.executeUpdate();             // Execute the insert query

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception (better to use a logger in production)
        }
    }
}
