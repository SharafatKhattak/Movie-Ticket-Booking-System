import javax.swing.*;
import java.awt.*;

public class InterFace1 extends JFrame {
    JFrame frame=new JFrame();
    JLabel label=new JLabel();
    JLabel label1=new JLabel();
    JLabel label2=new JLabel();
    JButton button1=new JButton();
    JButton button2=new JButton();


    InterFace1(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Movie Ticket Booking System");
        frame.setSize(600,500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        //LABEL
        label.setBounds(35,0,600,50);
        label.setFont(new Font("Poppins",Font.BOLD,25));
        label.setText("Welcome to Movie Ticket Booking System");

        //LABEL 1
        label1.setBounds(10,70,200,50);
        label1.setFont(new Font("Poppins",Font.ITALIC,15));
        label1.setText("Enter as a User:");
        /* Button 1*/
        button1.setText("User");
        button1.setBounds(150,84,80,25);
        button1.setFocusPainted(false);
        //LABEL 2
        label2.setBounds(10,150,200,50);
        label2.setFont(new Font("Poppins",Font.ITALIC,15));
        label2.setText("Enter as an Admin:");
        //Button 2
        button2.setText("Admin");
        button2.setBounds(150,160,80,25);
        button2.setFocusPainted(false);
        frame.add(label);
        frame.add(label1);
        frame.add(label2);
        frame.add(button1);
        frame.add(button2);

    }
}