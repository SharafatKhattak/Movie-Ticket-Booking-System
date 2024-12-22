import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {

    // Method to establish connection to the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/movei_ticket";
        String username = "root";
        String password = "Shabi6264@";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to save user data into the database
    public static void saveUser(String username, String email, String passwordHash) {
        String insertSQL = "INSERT INTO users (Username, email, Password) VALUES (?, ?, ?)";

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


    public static List<String> getMovies() {
        List<String> movies = new ArrayList<>();
        String query = "SELECT name, genre FROM movies";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String movie = rs.getString("name") + " (" + rs.getString("genre") + ")";
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }



}