import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {


    Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(650, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());
        /*------Background------*/
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bg2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 450, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 650, 450);

        {
            //LoginHeading
            JLabel heading = new JLabel("Login");
            heading.setBounds(150, 0, 100, 50);
            heading.setFont(new Font("segoe print", Font.BOLD | Font.ITALIC, 30));
            heading.setForeground(Color.white);

            background.add(heading);
        }
        {
            JPanel panel = new JPanel();
            panel.setBounds(10, 60, 370, 315);
            panel.setLayout(null);
            panel.setBackground(new Color(53,109,122,10));
            background.add(panel);
            {
                //name
                JLabel nameText = new JLabel(" User Name:");
                nameText.setBounds(0,10,100,30);
                nameText.setFont(new Font("segoe print", Font.BOLD, 15));
                nameText.setForeground(Color.white);
                nameText.setOpaque(false);

                panel.add(nameText);
                //nameArea
                JTextArea nameArea = new JTextArea();
                nameArea.setBackground(Color.white);
                nameArea.setBounds(100,13,250,25);

                panel.add(nameArea);
            }
            {
                //password
                JLabel password = new JLabel(" Password:");
                password.setBounds(0,80,100,30);
                password.setFont(new Font("segoe print", Font.BOLD, 15));
                password.setForeground(Color.white);

                panel.add(password);

                JTextArea passwordArea = new JTextArea();
                passwordArea.setBackground(Color.white);
                passwordArea.setBounds(100,83,250,25);

                panel.add(passwordArea);
            }
            {
                //Forgot Password
                JLabel forgotText = new JLabel(" Forgot Password");
                forgotText.setBounds(255,100,200,30);
                forgotText.setFont(new Font("segoe print", Font.ITALIC, 10));
                forgotText.setForeground(Color.white);
                panel.add(forgotText);
            }
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(20,200,110,30);
            loginButton.setBackground(new Color(0,0,0,0));
            loginButton.setFocusPainted(false);
            loginButton.setFont(new Font("segoe print", Font.BOLD, 20));
            loginButton.setForeground(Color.white);
            panel.add(loginButton);


            JButton CancelButton = new JButton("Cancel");
            CancelButton.setBounds(220,200,100,30);
            CancelButton.setBackground(new Color(0,0,0,0));
            CancelButton.setFocusPainted(false);
            CancelButton.setFont(new Font("segoe print", Font.BOLD, 20));
            CancelButton.setForeground(Color.white);
            panel.add(CancelButton);



        }

        add(background);
        setVisible(true);

    }
}