import javax.swing.*;

public class AddMovie extends javax.swing.JFrame {
    public AddMovie() {
        setTitle("Add Movie");
        ImageIcon logo = new ImageIcon(getClass().getResource("/Logo.png"));
        setIconImage(logo.getImage());
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
