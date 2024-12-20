import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovie extends JFrame {
    private JTextField txtMovieName;
    private JTextField txtPosterPath;

    public AddMovie() {
        setTitle("Add Movie");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());
        setSize(550, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Set layout to null for manual positioning
        setLayout(null);

        // Add components
        addComponents();

        setVisible(true);
    }

    private void addComponents() {
        // Label for Movie Name
        JLabel lblMovieName = new JLabel("Enter Movie Name:");
        lblMovieName.setBounds(20, 50, 150, 30);
        add(lblMovieName);

        // Text Field for Movie Name
        txtMovieName = new JTextField();
        txtMovieName.setBounds(180, 50, 300, 30);
        add(txtMovieName);

        // Label for Poster Path
        JLabel lblPosterPath = new JLabel("Enter Movie Poster Path:");
        lblPosterPath.setBounds(20, 100, 150, 30);
        add(lblPosterPath);

        // Text Field for Poster Path
        txtPosterPath = new JTextField();
        txtPosterPath.setBounds(180, 100, 300, 30);
        add(txtPosterPath);

        // Submit Button
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(200, 150, 100, 30);
        add(btnSubmit);

        // Action Listener for Submit Button
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = txtMovieName.getText().trim();
                String posterPath = txtPosterPath.getText().trim();

                // Check if both fields are filled
                if (movieName.isEmpty() || posterPath.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Both fields are required!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Movie Name: " + movieName + "\nPoster Path: " + posterPath);
                }
            }
        });
    }

    public String getMovieName() {
        return txtMovieName.getText().trim(); // Return the trimmed movie name
    }

    public String getPosterPath() {
        return txtPosterPath.getText().trim(); // Return the trimmed poster path
    }

    public static void main(String[] args) {
        new AddMovie(); // Test the class
    }
}
