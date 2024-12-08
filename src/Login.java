import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

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
        JLabel nameText = new JLabel("User Name:");
        nameText.setFont(new Font("Segoe Print", Font.BOLD, 15));
        nameText.setForeground(Color.WHITE);
        nameText.setBounds(50, 100, 100, 30);  // Set bounds
        background.add(nameText);

        // Username Field
        JTextField nameField = new JTextField(15);
        nameField.setBounds(150, 100, 250, 30);  // Set bounds
        background.add(nameField);

        // Password Label
        JLabel passwordText = new JLabel("Password:");
        passwordText.setFont(new Font("Segoe Print", Font.BOLD, 15));
        passwordText.setForeground(Color.WHITE);
        passwordText.setBounds(50, 160, 100, 30);  // Set bounds
        background.add(passwordText);

        // Password Field
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(150, 160, 250, 30);  // Set bounds
        background.add(passwordField);

        // Forgot Password Label
        JLabel forgotText = new JLabel("Forgot Password?");
        forgotText.setFont(new Font("Segoe Print", Font.ITALIC, 10));
        forgotText.setForeground(Color.WHITE);
        forgotText.setBounds(255, 200, 200, 30);  // Set bounds
        background.add(forgotText);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        loginButton.setBackground(new Color(53, 109, 122));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(100, 250, 100, 30);  // Set bounds
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(Login.this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(Login.this, "Login Successful!", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        cancelButton.addActionListener(e -> System.exit(0));
        background.add(cancelButton);

        setVisible(true);
    }


}
