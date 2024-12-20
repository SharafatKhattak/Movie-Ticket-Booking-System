import javax.swing.*;
import java.awt.*;

public class Enterpoll extends JFrame {

    public Enterpoll() {
        setTitle("Enter a Poll - Movie Booking System");
        initializePage("Enter a Poll",
                "<html>Participate in a poll to help us improve!<br>"
                        + "Choose your favorite genre:</html>");
    }

    private void initializePage(String heading, String question) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);

        JPanel background = createBackgroundPanel();
        background.setLayout(new BorderLayout());

        // Add Title and Poll Content
        background.add(createTitlePanel(heading), BorderLayout.NORTH);
        background.add(createPollPanel(question), BorderLayout.CENTER);

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

    private JPanel createPollPanel(String question) {
        JPanel pollPanel = new JPanel();
        pollPanel.setOpaque(false);
        pollPanel.setLayout(new BoxLayout(pollPanel, BoxLayout.Y_AXIS));

        JLabel questionLabel = new JLabel(question);
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        questionLabel.setForeground(Color.LIGHT_GRAY);
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        pollPanel.add(Box.createVerticalStrut(30)); // Spacer
        pollPanel.add(questionLabel);

        String[] options = {"Action", "Comedy", "Drama", "Horror", "Sci-Fi"};
        ButtonGroup group = new ButtonGroup();

        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            radioButton.setForeground(Color.WHITE);
            radioButton.setOpaque(false);
            group.add(radioButton);
            pollPanel.add(radioButton);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        submitButton.setBackground(new Color(191, 64, 64));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submitButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Thank you for participating!"));

        pollPanel.add(Box.createVerticalStrut(20)); // Spacer
        pollPanel.add(submitButton);

        return pollPanel;
    }
}
