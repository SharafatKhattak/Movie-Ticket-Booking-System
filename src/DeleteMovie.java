import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMovie extends JFrame {
    private JTextField txtMovieId;
    private Runnable onMovieDeleted;

    public DeleteMovie(Runnable onMovieDeleted) {
        this.onMovieDeleted = onMovieDeleted;

        setTitle("Delete Movie");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set layout to null for manual positioning
        setLayout(null);

        // Set background
        JPanel background = setBackgroundPanel();
        background.setLayout(null); // Use null layout for manual positioning
        setContentPane(background);

        // Add components
        addComponents();

        setVisible(true);
    }

    private void addComponents() {
        // Label for Movie ID
        JLabel lblMovieId = new JLabel("Enter Movie ID to Delete:");
        lblMovieId.setBounds(20, 50, 150, 30);
        lblMovieId.setForeground(Color.WHITE); // Set text color to white
        add(lblMovieId);

        // Text Field for Movie ID
        txtMovieId = new JTextField();
        txtMovieId.setBounds(180, 50, 200, 30);
        add(txtMovieId);

        // Submit Button
        JButton btnSubmit = new JButton("Delete");
        btnSubmit.setBounds(150, 100, 100, 30);
        add(btnSubmit);

        // Action Listener for Submit Button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieIdStr = txtMovieId.getText().trim();

                // Check if the field is filled
                if (movieIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Movie ID is required!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int movieId = Integer.parseInt(movieIdStr);
                        deleteFromDatabase(movieId);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid Movie ID!",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void deleteFromDatabase(int movieId) {
        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String user = "root";
        String password = "sharafat@321";

        String query = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null,
                        "Movie deleted successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                // Notify AdminHomepage
                if (onMovieDeleted != null) {
                    onMovieDeleted.run();
                }

                dispose(); // Close the DeleteMovie window
            } else {
                JOptionPane.showMessageDialog(null,
                        "Movie ID not found!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error deleting movie from database!",
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

}
