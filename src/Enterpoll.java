import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class Enterpoll extends JFrame {

    private JComboBox<String> movieComboBox;
    private JTextField movieTextField;

    public Enterpoll() {
        setTitle("Enter a Poll - Movie Booking System");
        initializePage("Enter a Poll",
                "<html>Participate in a poll to help us improve!<br>"
                        + "Enter your favorite movie and submit your vote:</html>");
    }

    private void initializePage(String heading, String question) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        JPanel background = createBackgroundPanel();
        background.setLayout(new BorderLayout());

        // Add Title and Poll Content
        background.add(createTitlePanel(heading), BorderLayout.NORTH);
        background.add(createPollPanel(question), BorderLayout.CENTER);

        add(background);
        setVisible(true);
    }

    private JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JPanel createTitlePanel(String titleText) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel title = new JLabel(titleText);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 30));

        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel createPollPanel(String question) {
        // Main poll panel
        JPanel pollPanel = new JPanel();
        pollPanel.setOpaque(false); // Transparent background
        pollPanel.setLayout(new GridBagLayout()); // Fine-grained control of component placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Minimal spacing
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Question label
        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        questionLabel.setForeground(Color.LIGHT_GRAY);
        pollPanel.add(questionLabel, gbc);

        // Text field for movie input
        movieTextField = new JTextField(20);
        movieTextField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        movieTextField.addActionListener(e -> fetchSuggestions(movieTextField.getText()));
        gbc.gridy++;
        pollPanel.add(movieTextField, gbc);

        // Combo box for suggestions
        movieComboBox = new JComboBox<>();
        movieComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        movieComboBox.setEditable(false);
        movieComboBox.addActionListener(e -> {
            String selectedMovie = (String) movieComboBox.getSelectedItem();
            if (selectedMovie != null) {
                movieTextField.setText(selectedMovie);
            }
        });
        gbc.gridy++;
        pollPanel.add(movieComboBox, gbc);

        // Submit button
        JButton submitButton = new JButton("Submit Vote");
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setBackground(new Color(191, 64, 64));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.addActionListener(e -> submitVote(movieTextField.getText()));
        gbc.gridy++;
        pollPanel.add(submitButton, gbc);

        return pollPanel;
    }

    private void fetchSuggestions(String userInput) {
        if (userInput.isEmpty()) {
            movieComboBox.setModel(new DefaultComboBoxModel<>(new String[0])); // Clear combo box if input is empty
            return;
        }

        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String user = "root";
        String password = "Shabi6264@";

        // Modified query to fetch all movies that have votes
        String query = "SELECT movie_name FROM poll_votes WHERE movie_name LIKE ? AND votes > 0 ORDER BY movie_name LIMIT 10";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + userInput + "%");
            ResultSet rs = stmt.executeQuery();

            Vector<String> suggestions = new Vector<>();
            while (rs.next()) {
                suggestions.add(rs.getString("movie_name"));
            }

            if (suggestions.isEmpty()) {
                suggestions.add("No suggestions found.");
            }

            movieComboBox.setModel(new DefaultComboBoxModel<>(suggestions));

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching suggestions from the database!");
        }
    }

    private void submitVote(String movieName) {
        if (movieName == null || movieName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid movie name!");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String user = "root";
        String password = "Shabi6264@";

        String insertQuery = "INSERT INTO poll_votes (movie_name, votes) VALUES (?, 1) "
                + "ON DUPLICATE KEY UPDATE votes = votes + 1";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

            stmt.setString(1, movieName.trim());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Thank you for voting!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting your vote. Please try again!");
        }
    }
}