import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class adminLogin extends JFrame {
    adminLogin() {
        setTitle("Admin Login");
        setSize(450, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("adminloginbg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 450, 200);
        add(background);

        // Username/Email Label
        JLabel userLabel = new JLabel("Enter Username/Email:");
        userLabel.setFont(new Font("Segoe Print", Font.ITALIC, 12));
        userLabel.setForeground(Color.white);
        userLabel.setBounds(20, 20, 180, 30);
        background.add(userLabel);

        // Username/Email Field
        JTextField userField = new JTextField();
        userField.setBounds(200, 23, 200, 25);
        userField.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        background.add(userField);

        // Password Label
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setFont(new Font("Segoe Print",Font.ITALIC, 12));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(20, 60, 130, 30);
        background.add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField("");
        passwordField.setBounds(200, 63, 200, 25);
        passwordField.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        background.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        loginButton.setBackground(new Color(53, 109, 122));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(150, 100, 100, 30); // Set bounds
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUser = userField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (enteredUser.isEmpty() || enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Username/Email or Password is empty.");
                } else {
                    String username = validateAdmin(enteredUser, enteredPassword);
                    if (username != null) {
                        JOptionPane.showMessageDialog(null, "Welcome Back " + username + ".");
                        dispose();
                        AdminHomepage adminHomepage = new AdminHomepage(username);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username/Email or Password.");
                        passwordField.setText("");
                    }
                }
            }
        });
        background.add(loginButton);

        setVisible(true);
    }

    private String validateAdmin(String userInput, String password) {
        String url = "jdbc:mysql://localhost:3306/moviebeats";
        String dbUser = "root";
        String dbPassword = "sharafat@321";
        String query = "SELECT username FROM admins WHERE (email = ? OR username = ?) AND password = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userInput); // Bind the email or username
            stmt.setString(2, userInput); // Bind the same input for username
            stmt.setString(3, password); // Bind the password
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username"); // Return the username if credentials are valid
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the database.");
        }
        return null; // Return null if validation fails
    }


}
