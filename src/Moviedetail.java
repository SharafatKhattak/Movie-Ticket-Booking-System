import javax.swing.*;
import java.awt.*;

public class Moviedetail extends JFrame {

    public Moviedetail(String movieTitle, String languages, String duration, String genre, String releaseDate) {
        // Frame settings
        setTitle(movieTitle + " Details");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel for movie poster
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK);
        leftPanel.setPreferredSize(new Dimension(300, 600));
        JLabel posterLabel = new JLabel(new ImageIcon("C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\IRON MAN 3.jpg")); // Replace with your poster path
        leftPanel.add(posterLabel);

        // Right panel for movie details
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(30, 30, 30));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Movie title
        JLabel titleLabel = new JLabel(movieTitle);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        rightPanel.add(titleLabel);

        // Spacer
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add interested and share buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBackground(new Color(30, 30, 30));

        JButton interestedButton = new JButton("I'm interested");
        interestedButton.setBackground(new Color(34, 139, 34));
        interestedButton.setForeground(Color.WHITE);
        interestedButton.setFocusPainted(false);

        JButton shareButton = new JButton("Share");
        shareButton.setBackground(new Color(100, 100, 100));
        shareButton.setForeground(Color.WHITE);
        shareButton.setFocusPainted(false);

        buttonPanel.add(interestedButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between buttons
        buttonPanel.add(shareButton);

        rightPanel.add(buttonPanel);

        // Spacer
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Language and Genre
        JLabel detailsLabel = new JLabel("Languages: " + languages + " | Duration: " + duration + " | Genre: " + genre);
        detailsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        detailsLabel.setForeground(Color.LIGHT_GRAY);
        rightPanel.add(detailsLabel);

        // Spacer
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Release date
        JLabel releaseDateLabel = new JLabel("Releasing on: " + releaseDate);
        releaseDateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        releaseDateLabel.setForeground(Color.LIGHT_GRAY);
        rightPanel.add(releaseDateLabel);

        // Spacer
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Book Tickets Button
        JButton bookSeatButton = new JButton("Select Seat");
        bookSeatButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bookSeatButton.setBackground(new Color(191, 64, 64));
        bookSeatButton.setForeground(Color.WHITE);
        bookSeatButton.setFocusPainted(false);
        bookSeatButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bookSeatButton.addActionListener(e -> {
            new SeatSelection(movieTitle, genre, languages, releaseDate).setVisible(true); // Navigate to Seat Selection
            dispose(); // Close the current window
        });

        buttonPanel.add(bookSeatButton);

        // Spacer
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Center the window
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Test the MovieDetailsPage
        SwingUtilities.invokeLater(() -> {
            Moviedetail detailsPage = new Moviedetail(
                    "UI (2024)",
                    "Kannada, Hindi, Tamil, Telugu, Malayalam",
                    "2h 12m",
                    "Action, Sci-Fi, Thriller",
                    "20 Dec, 2024"
            );
            detailsPage.setVisible(true);
        });
    }
}
