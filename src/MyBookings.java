import javax.swing.*;
import java.awt.*;

public class MyBookings extends JFrame {

    public MyBookings() {
        setTitle("My Bookings - Movie Booking System");
        initializePage("My Bookings", "<html>Here are your previous and current bookings:<br>"
                + "- Movie 1: Booked on 15th Dec<br>"
                + "- Movie 2: Booked on 18th Dec<br>"
                + "- Movie 3: Booked on 20th Dec<br>"
                + "</html>");
    }

    private void initializePage(String heading, String content) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        JPanel background = createBackgroundPanel();
        background.setLayout(new BorderLayout());

        // Add Title and Content
        background.add(createTitlePanel(heading), BorderLayout.NORTH);
        background.add(createContentPanel(content), BorderLayout.CENTER);

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

    private JPanel createContentPanel(String content) {
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel contentLabel = new JLabel(content);
        contentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contentLabel.setForeground(Color.LIGHT_GRAY);
        contentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(50)); // Spacer
        contentPanel.add(contentLabel);

        return contentPanel;
    }
}
