import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminLogin extends JFrame {
    private String password1, password2;

    private void setPassword1() {
        password1 = "sharafat@321";
        password2 = "shoaib@321";
    }

    adminLogin() {
        setPassword1();  // Ensure the passwords are set
        setTitle("Admin Login");
        setSize(450, 150);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("adminloginbg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 150, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 450, 150);
        add(background);

        // Label
        JLabel lblNewLabel = new JLabel("Enter Password:");
        lblNewLabel.setFont(new Font("segoe print", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel.setForeground(Color.white);
        lblNewLabel.setBounds(0, 20, 130, 30);
        background.add(lblNewLabel);

        // Password Field
        JPasswordField password = new JPasswordField("");
        password.setBounds(130, 23, 200, 25);
        password.setFont(new Font("segoe print", Font.PLAIN, 15));

        //LoginBtn
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe Print", Font.BOLD, 15));
        loginButton.setBackground(new Color(53, 109, 122));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBounds(350, 23, 80, 30);  // Set bounds
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(password.getPassword());  // Correct method to get password
                if (enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Password is empty.");
                } else {
                    if (enteredPassword.equals(password1)) {
                        JOptionPane.showMessageDialog(null, "Welcome Back Sharafat.");
                        dispose();
                        AdminHomepage adminHomepage = new AdminHomepage("Sharafat");
                    } else if (enteredPassword.equals(password2)) {
                        JOptionPane.showMessageDialog(null, "Welcome Back Shoaib.");
                        dispose();
                        AdminHomepage adminHomepage = new AdminHomepage("Shoaib");

                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong Password.");
                        password.setText("");

                    }
                }
            }
        });
        background.add(loginButton);

        background.add(password);
        setVisible(true);
    }

}