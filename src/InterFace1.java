import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;

public class InterFace1 extends JFrame {


    InterFace1() {
        //header
        JPanel heading = new JPanel();
        heading.setBackground(new Color(0,0,0,20));
        heading.setBounds(0,0,650,80 );
        JLabel headingText= new JLabel("Welcome to Movie Ticket Booking System");
        headingText.setFont(new Font("Algerian",Font.BOLD|Font.ITALIC,25));
        headingText.setForeground(Color.white);
        headingText.setBounds(200,50,400,50);
        heading.add(headingText);

        //User & Admin Panels


        //frame
        setTitle("InterFace 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);


        //background
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
        Image i2=i1.getImage().getScaledInstance(650,450,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel background=new JLabel(i3);
        background.setBounds(0,0,650,450);


        add(background);
        background.add(heading);
        setVisible(true);

    }
}