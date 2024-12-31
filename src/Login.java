import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    private int userId;
    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        // Set icon image
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());

        // Background image
        ImageIcon i1 = new ImageIcon(getClass().getResource("bg2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 450, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setLayout(null);
        add(background);

        // Login Heading
        JLabel heading = new JLabel("Login");
        heading.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 30));
        heading.setForeground(Color.WHITE);
        heading.setBounds(150, 20, 100, 50);  // Set bounds (x, y, width, height)
        background.add(heading);

        // Username Label
        JLabel nameText = new JLabel("User Name/Email:");
        nameText.setFont(new Font("Segoe Print", Font.BOLD, 15));
        nameText.setForeground(Color.WHITE);
        nameText.setBounds(10, 100, 170, 30);  // Set bounds
        background.add(nameText);

        // Username Field
        JTextField nameField = new JTextField(15);
        nameField.setBounds(150, 100, 250, 30);  // Set bounds
        background.add(nameField);

        // Password Label
        JLabel passwordText = new JLabel("Password:");
        passwordText.setFont(new Font("Segoe Print", Font.BOLD, 15));
        passwordText.setForeground(Color.WHITE);
        passwordText.setBounds(10, 160, 100, 30);  // Set bounds
        background.add(passwordText);

        // Password Field
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(150, 160, 250, 30);  // Set bounds
        background.add(passwordField);

        // Forgot Password Label
        JLabel forgotText = new JLabel("Forgot Password?");
        forgotText.setFont(new Font("Segoe Print", Font.ITALIC, 10));
        forgotText.setForeground(Color.WHITE);
        forgotText.setBounds(300, 200, 200, 30);  // Set bounds
        background.add(forgotText);

        //CreateNewAccount
        {
            JButton createAccountButton = new JButton("Create new account");
            createAccountButton.setFont(new Font("Segoe Print", Font.ITALIC, 10));
            createAccountButton.setBackground(new Color(53, 109, 122, 0));
            createAccountButton.setBounds(80, 200, 200, 30);
            // Make the button transparent
            createAccountButton.setContentAreaFilled(false); // Remove background fill
            createAccountButton.setBorderPainted(false);     // Remove button border
            createAccountButton.setFocusPainted(false);      // Remove focus indicator

            background.add(createAccountButton);
            createAccountButton.setForeground(Color.WHITE);
            //eventHandler
            createAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    createAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
                }
            });
            createAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Signup signup = new Signup();
                }
            });
        }
        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        loginButton.setBackground(new Color(53, 109, 122));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(100, 250, 100, 30);  // Set bounds

        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (authenticateUser(username, password)) {
                        dispose();
                        Homepage homepage=new Homepage(username,userId,true);

                        // Proceed to the next screen or functionality
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        background.add(loginButton);

        // Cancel Button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        cancelButton.setBackground(new Color(53, 109, 122));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBounds(250, 250, 100, 30);  // Set bounds
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
            }
        });
        cancelButton.addActionListener(e -> System.exit(0));
        background.add(cancelButton);

        setVisible(true);
    }

    private boolean authenticateUser(String usernameOrEmail, String password) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/moviebeats"; // Database URL
        String dbUser = "root"; // Replace with your MySQL username
        String dbPassword = "sharafat@321"; // Replace with your MySQL password

        // Updated query to search for both username and email using the same input field
        String query = "SELECT user_id, password_hash FROM users WHERE username = ? OR email = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the value (which could be either username or email) in both fields
            stmt.setString(1, usernameOrEmail);
            stmt.setString(2, usernameOrEmail);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPasswordHash = rs.getString("password_hash");
                userId = rs.getInt("user_id"); // Retrieve and store the user's ID

                // Compare hashed password (use an actual hashing library like bcrypt here)
                if (password.equals(storedPasswordHash)) { // Replace with hashed comparison
                    return true; // Successful login
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false; // Incorrect password
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username or email not found.", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // Username or email not found
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


}