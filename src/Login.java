import javax.swing.*;

public class Login extends JFrame {

    Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(650, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon Logo = new ImageIcon(getClass().getClassLoader().getResource("Logo.png"));

        setIconImage(Logo.getImage());

        setVisible(true);

    }
}