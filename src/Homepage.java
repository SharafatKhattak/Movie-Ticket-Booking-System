import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Homepage extends JFrame {

    private String username;
    private int id;
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/moviebeats";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "sharafat@321";
    private boolean isUser;

    public Homepage(String username, int id, boolean isUser) {
        this.username = username;
        this.id = id;
        this.isUser = isUser;
        // Set frame properties
        setIconImage(new ImageIcon(getClass().getResource("/Logo.png")).getImage());
        setTitle("Movie Booking System");
        setFullScreenWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background Panel with Gradient
        JPanel background = createBackgroundPanel();
        background.setLayout(new BorderLayout());

        // Add Title Panel
        background.add(createTitlePanel(), BorderLayout.NORTH);

        // Add Menu Panel
        JPanel menuPanel = createMenuPanel();
        background.add(menuPanel, BorderLayout.WEST);

        // Add Movie Grid Panel
        JPanel movieGridPanel = createMovieGridPanel();
        background.add(movieGridPanel, BorderLayout.CENTER);

        // Add background to the frame
        add(background);
        setVisible(true);
    }

    private void setFullScreenWindow() {
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int screenWidth = (int) screenBounds.getWidth();
        int screenHeight = (int) screenBounds.getHeight();
        setSize(screenWidth, screenHeight); // Full window size
    }

    private JPanel createBackgroundPanel() {
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

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setOpaque(false);

        // Title Label
        JLabel title = new JLabel("Movie Ticket Booking System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 30));
        title.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                title.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new Homepage(username,id,true).setVisible(true);
            }
        });


        titlePanel.add(title, BorderLayout.WEST); // Add title to the left side

        // Username Label
        JLabel userLabel = new JLabel("Welcome, " + username + "!");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titlePanel.add(userLabel, BorderLayout.EAST); // Add username to the right side

        return titlePanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0, 0, 0, 100)); // semi-transparent background
        menuPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for menu panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add menu buttons
        gbc.anchor = GridBagConstraints.WEST;
        menuPanel.add(createMenuButton("Homepage", e -> new Dashboard()), gbc);
        menuPanel.add(createMenuButton("Available Movies", e -> new AvailableMovei()), gbc);

        // Disable "My Bookings" for guests
        JButton myBookingsButton = createMenuButton("My Bookings", e -> showMessage("My Booking button Clicked"));
        if (!isUser) {
            myBookingsButton.setEnabled(false);  // Disable for guests
            myBookingsButton.setToolTipText("Please log in to access your bookings.");
        }
        menuPanel.add(myBookingsButton, gbc);

        // Disable "Manage Account" for guests
        JButton manageAccountButton = createMenuButton("Manage Account", e -> {
            ManageAccount manageAccount = new UserAccount(id); // Pass userId to ManageAccount
            manageAccount.setVisible(true); // Show the ManageAccount frame
        });
        if (!isUser) {
            manageAccountButton.setEnabled(false);  // Disable for guests
            manageAccountButton.setToolTipText("Please log in to manage your account.");
        }
        menuPanel.add(manageAccountButton, gbc);

        // Add Sign Out Button at the bottom
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        menuPanel.add(createSignOutButton(), gbc);

        return menuPanel;
    }


    private JPanel createMovieGridPanel() {
        JPanel movieGridPanel = new JPanel();
        movieGridPanel.setLayout(new GridLayout(3, 3, 10, 10)); // 3x3 grid with gaps
        movieGridPanel.setOpaque(false); // Make the parent container transparent

        List<Movie> movies = fetchMoviesFromDatabase(); // Fetch movies from the database
        for (Movie movie : movies) {
            movieGridPanel.add(createMoviePanel(movie)); // Add movie panels to the grid
        }

        // Add empty panels to fill the grid if there are fewer than 9 movies
        int emptyPanels = 9 - movies.size();
        for (int i = 0; i < emptyPanels; i++) {
            movieGridPanel.add(createEmptyPanel()); // Add empty panels to fill space
        }

        return movieGridPanel;
    }

    private JPanel createEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false); // Make sure the empty panel is transparent
        return emptyPanel;
    }


    private JPanel createMoviePanel(Movie movie) {
        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BorderLayout());
        moviePanel.setBackground(new Color(0, 0, 0, 150)); // semi-transparent background

        JLabel posterLabel = new JLabel(new ImageIcon(movie.getPosterPath())); // Movie poster
        moviePanel.add(posterLabel, BorderLayout.CENTER);

        JButton bookButton = new JButton("Book");
        bookButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        bookButton.setBackground(new Color(191, 64, 64));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bookButton.addActionListener(e -> {
            if (isUser) {
                new Moviedetail(movie.getMovieName(), movie.getGenre(), movie.getShowingDate(), movie.getPosterPath()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Please login before booking.", "Guest Access", JOptionPane.WARNING_MESSAGE);
            }
        });
        bookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                bookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                bookButton.setCursor(Cursor.getDefaultCursor());
            }
        });

        moviePanel.add(bookButton, BorderLayout.SOUTH);

        return moviePanel;
    }

    private JButton createMenuButton(String name, java.awt.event.ActionListener action) {
        JButton button = new JButton(name);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        button.setBackground(new Color(191, 64, 64)); // Light blue color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addActionListener(action);

        // Change cursor to hand when mouse enters the button
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    private JButton createSignOutButton() {
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        signOutButton.setBackground(new Color(0, 0, 0));
        signOutButton.setForeground(new Color(255, 255, 255)); // Light blue for the text
        signOutButton.setFocusPainted(false);
        signOutButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0))); // Blue border
        signOutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Signed Out Successfully!");
            dispose(); // Close the frame
        });

        // Change cursor to hand when mouse enters the button
        signOutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                signOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                signOutButton.setCursor(Cursor.getDefaultCursor());
            }
        });

        return signOutButton;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private List<Movie> fetchMoviesFromDatabase() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, movieName, posterPath, genre, showing_date FROM movies";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String movieName = rs.getString("movieName");
                String posterPath = rs.getString("posterPath");
                String genre = rs.getString("genre");
                String showingDate = rs.getString("showing_date");
                movies.add(new Movie(id, movieName, posterPath, genre, showingDate));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error fetching movies from database!",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return movies;
    }
}