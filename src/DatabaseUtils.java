import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseUtils {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/movei_ticket";
    private static final String DB_USER = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "Shabi6264@"; // Replace with your MySQL password

    public static void saveUser(String username, String email, String password) {
        String insertQuery = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            // Set values for the query
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // Ideally, hash the password here

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User saved successfully!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




}

