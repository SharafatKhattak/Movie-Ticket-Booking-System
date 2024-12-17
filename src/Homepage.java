import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame {

    public Homepage() {
        // Set up the main frame
        setTitle("Movie Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Gradient background panel
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // --- NORTH PANEL (Header) ---
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel systemLabel = createHeaderLabel("🎥 Movie Booking System", JLabel.LEFT);
        JLabel welcomeLabel = createHeaderLabel("Welcome back!", JLabel.CENTER);
        JLabel usernameLabel = createHeaderLabel("User123", JLabel.R

        northPanel.add(systemLabel, BorderLayout.WEST);
        northPanel.add(welcomeLabel, BorderLayout.CENTER);
        northPanel.add(usernameLabel, BorderLayout.EAST);


        mainPanel.add(northPanel, BorderLayout.NORTH);

        // --- WEST PANEL (Menu with Enhanced Buttons) ---
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(5, 1, 10, 10));
        westPanel.setOpaque(false);
        westPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Stylish menu buttons
        JButton dashboardButton = createStylishButton("Dashboard");
        JButton addMovieButton = createStylishButton("Add Movie");
        JButton customersButton = createStylishButton("Customers");
        JButton availableMoviesButton = createStylishButton("Available Movies");
        JButton manageAccountButton = createStylishButton("Manage Account");

        westPanel.add(dashboardButton);
        westPanel.add(addMovieButton);
        westPanel.add(customersButton);
        westPanel.add(availableMoviesButton);
        westPanel.add(manageAccountButton);

        mainPanel.add(westPanel, BorderLayout.WEST);

        // --- CENTER PANEL (Movie Posters) ---
        JPanel centerPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Replace with your movie poster paths and titles
        String[] moviePosters = {
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\ALEIN.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\INCEPTION.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\IRON MAN 3.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\MOVE.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\The Dragon.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\The Dragon.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\The Dragon.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\The Dragon.jpg",
                "C:\\Users\\DEll\\Movie-Ticket-Booking-System\\src\\The Dragon.jpg"
        };

        String[] movieTitles = {
                "ALIEN", "INCEPTION", "IRON MAN 3",
                "MOVE", "THE DRAGON", "THE DRAGON",
                "THE DRAGON", "THE DRAGON", "THE DRAGON"
        };

        // Create movie cards
        for (int i = 0; i < moviePosters.length; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setOpaque(false);

            // Movie poster
            ImageIcon image = new ImageIcon(moviePosters[i]);
            Image img = image.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            // Movie title
            JLabel titleLabel = new JLabel(movieTitles[i], JLabel.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

            card.add(imageLabel, BorderLayout.CENTER);
            card.add(titleLabel, BorderLayout.SOUTH);

            centerPanel.add(card);
        }

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // --- SOUTH PANEL (Sign Out and Exit) ---
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setOpaque(false);
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton signOutButton = createStylishButton("Sign Out");
        JButton exitButton = createStylishButton("Exit");

        exitButton.addActionListener(e -> System.exit(0));
        signOutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Signed Out Successfully!");
            dispose();
        });

        southPanel.add(signOutButton, BorderLayout.WEST);
        southPanel.add(exitButton, BorderLayout.EAST);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to create a stylish button
    private JButton createStylishButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(new Color(0, 0, 0, 100));
        button.setBorder(null);

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0, 100));
                button.setForeground(Color.WHITE);
            }
        });

        return button;
    }

    // Method to create header labels
    private JLabel createHeaderLabel(String text, int alignment) {
        JLabel label = new JLabel(text, alignment);
        label.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        return label;

    }


}
