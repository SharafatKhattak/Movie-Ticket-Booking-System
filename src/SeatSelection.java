import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatSelection extends JFrame {

    private String selectedSeat = null;

    public SeatSelection(String movieTitle) {
        // Frame settings
        setTitle("Seat Selection for " + movieTitle);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 30, 30));
        JLabel titleLabel = new JLabel("Select Your Seat");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Seats Panel (Grid Layout for Seats)
        JPanel seatsPanel = new JPanel();
        seatsPanel.setLayout(new GridLayout(5, 5, 10, 10)); // 5 rows and 5 columns
        seatsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        seatsPanel.setBackground(Color.BLACK);

        // Create seat buttons
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 5; col++) {
                String seatNumber = "R" + row + "C" + col;
                JButton seatButton = new JButton(seatNumber);
                seatButton.setBackground(Color.GREEN);
                seatButton.setForeground(Color.BLACK);
                seatButton.setFocusPainted(false);

                // Add action listener for seat selection
                seatButton.addActionListener(e -> {
                    if (selectedSeat == null || !selectedSeat.equals(seatNumber)) {
                        selectedSeat = seatNumber;
                        updateSeatColors(seatsPanel, seatButton); // Update UI to show selected seat
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

        // Action for Proceed Button
        proceedButton.addActionListener(e -> {
            if (selectedSeat != null) {
                showConfirmation(movieTitle, selectedSeat);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a seat before proceeding.");
            }
        });

        proceedPanel.add(proceedButton);
        add(proceedPanel, BorderLayout.SOUTH);

        // Center the window
        setLocationRelativeTo(null);
    }

    private void updateSeatColors(JPanel seatsPanel, JButton selectedButton) {
        // Reset all buttons to green, then set the selected button to yellow
        Component[] components = seatsPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                ((JButton) comp).setBackground(Color.GREEN);
            }
        }
        selectedButton.setBackground(Color.YELLOW);
    }

    private void showConfirmation(String movieTitle, String seatNumber) {
        // Confirmation Dialog
        JDialog confirmationDialog = new JDialog(this, "Ticket Confirmation", true);
        confirmationDialog.setSize(400, 300);
        confirmationDialog.setLayout(new BorderLayout());

        // Confirmation Message
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        messagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel confirmationLabel = new JLabel("Your ticket is confirmed!");
        confirmationLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        confirmationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel movieLabel = new JLabel("Movie: " + movieTitle);
        movieLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        movieLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel seatLabel = new JLabel("Seat: " + seatNumber);
        seatLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        seatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel thankYouLabel = new JLabel("Thank you for booking with us!");
        thankYouLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add labels to panel
        messagePanel.add(confirmationLabel);
        messagePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        messagePanel.add(movieLabel);
        messagePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        messagePanel.add(seatLabel);
        messagePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        messagePanel.add(thankYouLabel);

        confirmationDialog.add(messagePanel, BorderLayout.CENTER);

        // Close Button
        JPanel closePanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        closeButton.addActionListener(e -> confirmationDialog.dispose());
        closePanel.add(closeButton);

        confirmationDialog.add(closePanel, BorderLayout.SOUTH);

        // Show dialog
        confirmationDialog.setLocationRelativeTo(this);
        confirmationDialog.setVisible(true);
    }

    public static void main(String[] args) {
        // Test the Seat Selection Page
        SwingUtilities.invokeLater(() -> {
            SeatSelection seatPage = new SeatSelection("THE IRON MAN");
            seatPage.setVisible(true);
        });
    }
}
