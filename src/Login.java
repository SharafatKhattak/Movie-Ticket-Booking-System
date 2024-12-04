import javax.swing.*;

public class Login extends JFrame {
    JFrame frame=new JFrame();
    Login(){
        ImageIcon bgImage=new ImageIcon("background.jpg");
        JLabel l=new JLabel(bgImage);
        l.setBounds(0,0,500,500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.add(l);
    }
}