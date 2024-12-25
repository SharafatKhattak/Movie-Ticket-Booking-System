import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class SeatSelection extends JFrame {
    private Set<String> selectedSeats = new HashSet<>();
    private boolean[][] seatAvailability;
    private static final int ROWS = 5;
    private static final int COLS = 5;

    // Movie Details to display on the seat selection page
    private String movieTitle;
    private String genre;
    private String language;
    private String releaseDate;

    public SeatSelection(String movieTitle, String genre, String language, String releaseDate) {
        // Set movie details received from Moviedetail page
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.language = language;
        this.releaseDate = releaseDate;

        // Initialize seat availability (true = booked, false = available)
        seatAvailability = new boolean[ROWS][COLS];
        initializeSeats();

        // Frame settings
        setTitle("Seat Selection for " + movieTitle);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 30, 30));
        JLabel titleLabel = new JLabel("Select Your Seats");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Seats Panel (Grid Layout for Seats)
        JPanel seatsPanel = new JPanel();
        seatsPanel.setLayout(new GridLayout(ROWS, COLS, 10, 10));
        seatsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        seatsPanel.setBackground(Color.BLACK);

        // Create seat buttons dynamically
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String seatNumber = "R" + (row + 1) + "C" + (col + 1);
                JButton seatButton = new JButton(seatNumber);

                if (seatAvailability[row][col]) {
                    seatButton.setBackground(Color.RED);
                    seatButton.setEnabled(false);
                } else {
                    seatButton.setBackground(Color.GREEN);
                }

                seatButton.setForeground(Color.BLACK);
                seatButton.setFocusPainted(false);

                final int selectedRow = row;
                final int selectedCol = col;
                seatButton.addActionListener(e -> {
                    if (selectedSeats.contains(seatNumber)) {
                        selectedSeats.remove(seatNumber);
                        seatButton.setBackground(Color.GREEN);
                    } else {
                        selectedSeats.add(seatNumber);
                        seatButton.setBackground(Color.YELLOW);
                    }
                });

                seatsPanel.add(seatButton);
            }
        }

        add(seatsPanel, BorderLayout.CENTER);

        // Proceed Button
        JPanel proceedPanel = new JPanel();
        proceedPanel.setBackground(new Color(30, 30, 30));
        JButton proceedButton = new JButton("Confirm Booking");
        proceedButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        proceedButton.setBackground(new Color(191, 64, 64));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false);

        proceedButton.addActionListener(e -> {
            if (!selectedSeats.isEmpty()) {
                showConfirmation();
            } else {
                JOptionPane.showMessageDialog(this, "Please select at least one seat before proceeding.");
            }
        });

        proceedPanel.add(proceedButton);
        add(proceedPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void initializeSeats() {
        // Example seat initialization (booked and available)
        seatAvailability[0][0] = true; // Booked seat
        seatAvailability[1][1] = true; // Booked seat
    }

    private void showConfirmation() {
        // Create the confirmation dialog
        JDialog confirmationDialog = new JDialog(this, "Booking Confirmation", true);
        confirmationDialog.setSize(400, 400);
        confirmationDialog.setLayout(new BorderLayout());

        // Create a panel for displaying movie and seat details
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel movieLabel = new JLabel("Movie: " + movieTitle);
        movieLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        messagePanel.add(movieLabel);

        JLabel genreLabel = new JLabel("Genre: " + genre);
        genreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(genreLabel);

        JLabel languageLabel = new JLabel("Language: " + language);
        languageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(languageLabel);

        JLabel dateLabel = new JLabel("Date: " + releaseDate);
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(dateLabel);

        JLabel seatLabel = new JLabel("Selected Seats: " + String.join(", ", selectedSeats));
        seatLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        messagePanel.add(seatLabel);

        JLabel confirmationLabel = new JLabel("Your booking is confirmed!");
        confirmationLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        messagePanel.add(confirmationLabel);

        // Add the message panel to the dialog
        confirmationDialog.add(messagePanel, BorderLayout.CENTER);

        // Close Button
        JPanel closePanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closeButton.addActionListener(e -> confirmationDialog.dispose());
        closePanel.add(closeButton);

        confirmationDialog.add(closePanel, BorderLayout.SOUTH);

        // Show the confirmation dialog
        confirmationDialog.setLocationRelativeTo(this);
        confirmationDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeatSelection seatPage = new SeatSelection("THE IRON MAN", "Action", "English", "2024-12-22");
            seatPage.setVisible(true);
        });
    }
}
