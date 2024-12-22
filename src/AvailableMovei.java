import javax.swing.*;
import java.awt.*;

public class AvailableMovei extends JFrame {

    public AvailableMovei() {
        setTitle("Available Movies - Movie Booking System");
        initializePage("Available Movies", "<html>Here is the list of movies currently available:<br>"
                + "- Movie 1: Action<br>"
                + "- Movie 2: Comedy<br>"
                + "- Movie 3: Thriller<br>"
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
