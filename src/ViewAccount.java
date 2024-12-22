import javax.swing.*;
import java.awt.*;

public class ViewAccount extends JFrame {

    public ViewAccount() {
        setTitle("View Account - Movie Booking System");
        initializePage("View Account",
                "<html>Your account details are as follows:<br>"
                        + "Name: John Doe<br>"
                        + "Email: johndoe@example.com<br>"
                        + "Total Bookings: 5<br></html>");
    }

    private void initializePage(String heading, String details) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        JPanel background = createBackgroundPanel();
        background.setLayout(new BorderLayout());

        // Add Title and Account Details
        background.add(createTitlePanel(heading), BorderLayout.NORTH);
        background.add(createContentPanel(details), BorderLayout.CENTER);

        add(background);
        setVisible(true);
    }

    private JPanel createBackgroundPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.DARK_GRAY, getWidth(), getHeight(), Color.RED);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    private JPanel createTitlePanel(String titleText) {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setOpaque(false);

        JLabel title = new JLabel(titleText);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 30));

        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel createContentPanel(String details) {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel detailsLabel = new JLabel(details);
        detailsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        detailsLabel.setForeground(Color.LIGHT_GRAY);
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(50)); // Spacer
        contentPanel.add(detailsLabel);

        return contentPanel;
    }
}
