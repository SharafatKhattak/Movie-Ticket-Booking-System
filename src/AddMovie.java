import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddMovie extends JFrame {
    private JTextField txtMovieName;
    private JTextField txtPosterPath;
    private Runnable onMovieAdded;

    public AddMovie(Runnable onMovieAdded) {
        this.onMovieAdded = onMovieAdded;

        setTitle("Add Movie");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());
        setSize(550, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set layout to null for manual positioning
        setLayout(null);
        JPanel background = setBackgroundPanel();
        background.setLayout(null); // Use null layout for manual positioning
        setContentPane(background);

        // Add components
        addComponents();

        setVisible(true);
    }

    private void addComponents() {
        // Label for Movie Name
        JLabel lblMovieName = new JLabel("Enter Movie Name:");
        lblMovieName.setForeground(Color.white);
        lblMovieName.setBounds(20, 50, 150, 30);
        add(lblMovieName);

        // Text Field for Movie Name
        txtMovieName = new JTextField();
        txtMovieName.setBounds(180, 50, 300, 30);
        add(txtMovieName);

        // Label for Poster Path
        JLabel lblPosterPath = new JLabel("Enter Movie Poster Path:");
        lblPosterPath.setBounds(20, 100, 150, 30);
        lblPosterPath.setForeground(Color.white);
        add(lblPosterPath);

        // Text Field for Poster Path
        txtPosterPath = new JTextField();
        txtPosterPath.setBounds(180, 100, 300, 30);
        add(txtPosterPath);

        // Submit Button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(200, 150, 100, 30);
        add(btnSubmit);

        // Action Listener for Submit Button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = txtMovieName.getText().trim();
                String posterPath = txtPosterPath.getText().trim();

                // Check if both fields are filled
                if (movieName.isEmpty() || posterPath.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Both fields are required!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    saveToDatabase(movieName, posterPath);
                }
            }
        });
    }

    private void saveToDatabase(String movieName, String posterPath) {
        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String user = "root";
        String password = "sharafat@321";

        String query = "INSERT INTO movies (movieName, posterPath) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movieName);
            stmt.setString(2, posterPath);
            stmt.executeUpdate();

            // Retrieve the generated keys
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    JOptionPane.showMessageDialog(null,
                            "Movie saved successfully! Generated Movie ID: " + generatedId,
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            // Notify AdminHomepage
            if (onMovieAdded != null) {
                onMovieAdded.run();
            }

            dispose(); // Close the AddMovie window

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error saving movie to database!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private JPanel setBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    public String getMovieName() {
        return txtMovieName.getText().trim();
    }

    public String getPosterPath() {
        return txtPosterPath.getText();
    }

    public static void main(String[] args) {
        new AddMovie(null);
    }
}