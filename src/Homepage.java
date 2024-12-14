import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {

    public Homepage() {
        // Set the title of the frame
        setTitle("Movie Booking System");

        // Set the size and default close operation
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout to BorderLayout
        setLayout(new BorderLayout());

        // --- NORTH PANEL (Header) ---
        JPanel northPanel = new JPanel(new BorderLayout());
        JLabel systemLabel = new JLabel("Movie Booking System", JLabel.LEFT);
        JLabel welcomeLabel = new JLabel("Welcome back", JLabel.CENTER);
        JLabel usernameLabel = new JLabel("xc kjsacnjscjkan...", JLabel.RIGHT);

        northPanel.add(systemLabel, BorderLayout.WEST);
        northPanel.add(welcomeLabel, BorderLayout.CENTER);
        northPanel.add(usernameLabel, BorderLayout.EAST);

        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add header to frame
        add(northPanel, BorderLayout.NORTH);

        // --- CENTER PANEL (Buttons and Grid Layout) ---
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Add menu buttons (Dashboard, Add Movie, etc.)
        JPanel menuPanel = new JPanel(new GridLayout(1, 5));
        JButton dashboardButton = new JButton("Dashboard");
        JButton addMovieButton = new JButton("Add Movie");
        JButton customersButton = new JButton("Customers");
        JButton availableMoviesButton = new JButton("Available Movies");
        JButton manageAccountButton = new JButton("Manage Account");

        menuPanel.add(dashboardButton);
        menuPanel.add(addMovieButton);
        menuPanel.add(customersButton);
        menuPanel.add(availableMoviesButton);
        menuPanel.add(manageAccountButton);

        centerPanel.add(menuPanel, BorderLayout.NORTH);

        // Add grid area for movie display
        // Add grid area for movie display
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10)); // 4x4 grid with spacing

// Replace these with the paths to your movie poster images
        String[] moviePosters = {
                "poster1.jpg", "poster2.jpg", "poster3.jpg", "poster4.jpg",
                "poster5.jpg", "poster6.jpg", "poster7.jpg", "poster8.jpg",
                "poster9.jpg", "poster10.jpg", "poster11.jpg", "poster12.jpg",
                "poster13.jpg", "poster14.jpg", "poster15.jpg", "poster16.jpg"
        };

// Loop to add images to the grid
        for (String poster : moviePosters) {
            ImageIcon movieImage = new ImageIcon(poster);

            // Resize the image to fit within the grid
            Image img = movieImage.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            movieImage = new ImageIcon(img);

            JLabel movieLabel = new JLabel(movieImage);
            movieLabel.setHorizontalAlignment(JLabel.CENTER);
            movieLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            gridPanel.add(movieLabel);
        }

        centerPanel.add(gridPanel, BorderLayout.CENTER);


        centerPanel.add(gridPanel, BorderLayout.CENTER);

        // Add centerPanel to the frame
        add(centerPanel, BorderLayout.CENTER);

        // --- SOUTH PANEL (Sign Out and Exit) ---
        JPanel southPanel = new JPanel(new BorderLayout());

        JButton signOutButton = new JButton("Sign Out");
        JButton exitButton = new JButton("Exit");

        // Exit ActionListener
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Sign Out ActionListener
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Signed Out Successfully!");
                // Redirect back to login screen
                dispose();
            }
        });

        // Buttons added to southPanel
        southPanel.add(signOutButton, BorderLayout.WEST);
        southPanel.add(exitButton, BorderLayout.EAST);

        add(southPanel, BorderLayout.SOUTH);

        // Make the frame visible
        setVisible(true);

    }

    public static void main(String[] args) {
        // Run the program
        new Homepage();
    }
}
