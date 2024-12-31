import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Enterpoll extends JFrame {

    private int userId;  // Change the userId to an integer

    public Enterpoll(boolean isAdmin, int userId) {
        this.userId = userId; // Set the user ID from the constructor
        setTitle(isAdmin ? "Admin: Movie Suggestions" : "User: Enter Your Suggestion");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        // Gradient Background Panel
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(null);
        add(gradientPanel);

        // Header Label
        JLabel header = new JLabel(isAdmin ? "Admin: Movie Suggestions" : "Enter Your Suggestion");
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setForeground(Color.WHITE);
        header.setBounds(150, 10, 300, 30);
        gradientPanel.add(header);

        if (isAdmin) {
            loadAdminView(gradientPanel);
        } else {
            loadUserView(gradientPanel);
        }

        setVisible(true);
    }

    private void loadUserView(JPanel panel) {
        // Movie Name Label and TextField
        JLabel movieLabel = new JLabel("Movie Name:");
        movieLabel.setBounds(30, 60, 100, 25);
        movieLabel.setForeground(Color.WHITE);
        panel.add(movieLabel);

        JTextField movieField = new JTextField();
        movieField.setBounds(160, 60, 300, 25);
        panel.add(movieField);

        // Description Label and TextField
        JLabel descLabel = new JLabel("Description (Optional):");
        descLabel.setBounds(30, 100, 150, 25);
        descLabel.setForeground(Color.WHITE);
        panel.add(descLabel);

        JTextArea descField = new JTextArea();
        descField.setBounds(160, 100, 300, 200);
        descField.setLineWrap(true); // Enable line wrapping
        descField.setWrapStyleWord(true); // Wrap at word boundaries
        descField.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Set font size
        descField.setMargin(new Insets(5, 5, 5, 5)); // Add padding for better readability
        panel.add(descField);

        // Save Button
        JButton saveButton = new JButton("Submit Suggestion");
        saveButton.setBounds(180, 360, 200, 30);
        saveButton.setBackground(Color.WHITE);
        saveButton.setFocusPainted(false);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = movieField.getText().trim();
                String description = descField.getText().trim();

                if (movieName.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Movie name is required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    saveMovieSuggestion(movieName, description);
                }
            }
        });
    }

    private void loadAdminView(JPanel panel) {
        JLabel adminLabel = new JLabel("List of Movie Suggestions:");
        adminLabel.setBounds(50, 60, 300, 25);
        adminLabel.setForeground(Color.WHITE);
        panel.add(adminLabel);

        JTextArea suggestionsList = new JTextArea();
        suggestionsList.setEditable(false);
        suggestionsList.setBounds(50, 90, 450, 300);
        panel.add(suggestionsList);

        loadMovieSuggestions(suggestionsList);
    }

    private void saveMovieSuggestion(String movieName, String description) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/moviebeats"; // Replace with your database
        String dbUser = "root"; // Replace with your DB username
        String dbPassword = "sharafat@321"; // Replace with your DB password

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            // Check if the same user has already submitted the same movie
            String checkQuery = "SELECT * FROM movie_suggestions WHERE movie_name = ? AND user_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, movieName);
            checkStmt.setInt(2, userId); // Use setInt for userId (now int)
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "You have already suggested this movie!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String insertQuery = "INSERT INTO movie_suggestions (movie_name, description, user_id) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, movieName);
                insertStmt.setString(2, description.isEmpty() ? null : description);
                insertStmt.setInt(3, userId); // Use setInt for userId (now int)
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Movie suggestion added successfully!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadMovieSuggestions(JTextArea suggestionsList) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/moviebeats";
        String dbUser = "root";
        String dbPassword = "sharafat@321";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT movie_name, votes FROM movie_suggestions";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                String movieName = rs.getString("movie_name");
                int votes = rs.getInt("votes");
                sb.append("Movie: ").append(movieName).append(", Votes: ").append(votes).append("\n");
            }
            suggestionsList.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
