import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class InterFace1 extends JFrame {


    InterFace1() {
        //frame
        setTitle("InterFace 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        ImageIcon Logo = new ImageIcon(getClass().getResource("Logo.png"));
        setIconImage(Logo.getImage());

        //background
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bg.jpg"));
        Image i2 = i1.getImage().getScaledInstance(650, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 650, 450);

        add(background);
        {

            //header
            JPanel heading = new JPanel();
            heading.setBackground(new Color(0, 0, 0, 20));
            heading.setBounds(0, 0, 650, 80);
            JLabel headingText = new JLabel("Welcome to Movie Ticket Booking System");
            headingText.setFont(new Font("segoe print", Font.BOLD | Font.ITALIC, 25));
            headingText.setForeground(Color.white);
            headingText.setBounds(200, 50, 400, 50);
            heading.add(headingText);

            background.add(heading);
        }
        {//User & Admin Panel
            JPanel User_Admin = new JPanel();
            User_Admin.setBackground(new Color(0, 0, 0, 100));
            User_Admin.setBounds(130, 120, 350, 250);
            User_Admin.setLayout(null);
            {
                //User
                JLabel user_text = new JLabel("Enter as a User: ");
                user_text.setFont(new Font("Poppins", Font.ITALIC, 15));
                user_text.setForeground(Color.white);
                user_text.setBounds(10, 25, 200, 20);
                //usrBtn
                JButton userButton = getJButton();
                User_Admin.add(user_text);
                User_Admin.add(userButton);
            }

            {
                //Admin
                JLabel admin_text = new JLabel("Enter as an Admin: ");
                admin_text.setFont(new Font("Poppins", Font.ITALIC, 15));
                admin_text.setForeground(Color.white);
                admin_text.setBounds(10, 85, 200, 20);
                JButton adminButton =getAdminButton();
                User_Admin.add(admin_text);
                User_Admin.add(adminButton);
                background.add(User_Admin);
            }
            {
                //Guest
                JLabel Guest_text = new JLabel("Enter as a Guest: ");
                Guest_text.setFont(new Font("Poppins", Font.ITALIC, 15));
                Guest_text.setForeground(Color.white);
                Guest_text.setBounds(10, 145, 200, 20);
                JButton GuestButton =getGuestButton();

                User_Admin.add(Guest_text);
                User_Admin.add(GuestButton);
                background.add(User_Admin);
            }
        }
        {
            JPanel footer = new JPanel();
            footer.setBackground(new Color(255,255,255,45));
            footer.setBounds(0, 380, 650, 50);
            footer.setLayout(null);
            JLabel footerText = new JLabel("Copyright © 2024 - moviesFeed®. All rights reserved.");
            footerText.setFont(new Font("Poppins", Font.ITALIC, 8));
            footerText.setForeground(Color.white);
            footerText.setBounds(200, 5, 200, 20);
            footer.add(footerText);
            background.add(footer);
        }

        add(background);
        setVisible(true);

    }

    private JButton getJButton() {
        JButton userButton = new JButton("User");
        userButton.setBounds(180, 25, 80, 25);
        userButton.setFont(new Font("Poppins", Font.BOLD, 15));
        userButton.setFocusPainted(false);
        userButton.setForeground(Color.BLACK);
        userButton.setBackground(Color.lightGray);
        //mouse pointer
        userButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                userButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
            }
        });
        userButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
        return userButton;
    }

    private JButton getAdminButton() {
        // Create the button
        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(180, 85, 85, 25);
        adminButton.setFont(new Font("Poppins", Font.BOLD, 15));
        adminButton.setFocusPainted(false);
        adminButton.setForeground(Color.black);
        adminButton.setBackground(Color.lightGray);

        // Mouse hover effect to change cursor
        adminButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                adminButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
            }
        });

        // ActionListener for button click
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                adminLogin admin = new adminLogin(); // Open admin login window
            }
        });


        return adminButton; // Returning only the button, you can add the label separately
    }
    //Guest Button
    private JButton getGuestButton() {
        // Create the button
        JButton guestButton = new JButton("Guest");
        guestButton.setBounds(180, 145, 85, 25);
        guestButton.setFont(new Font("Poppins", Font.BOLD, 15));
        guestButton.setFocusPainted(false);
        guestButton.setForeground(Color.black);
        guestButton.setBackground(Color.lightGray);

        // Mouse hover effect to change cursor
        guestButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                guestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Set cursor to pointer
            }
        });

        // ActionListener for button click
        guestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                // main page object
            }
        });



        return guestButton;
    }

}
