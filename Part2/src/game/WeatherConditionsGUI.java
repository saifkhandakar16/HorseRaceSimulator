package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherConditionsGUI extends JFrame {

    private JComboBox<String> weatherComboBox;
    private JComboBox<String> trackComboBox;
    private JButton nextButton;
    private JButton backButton;
    private JButton homeButton;

    public WeatherConditionsGUI() {
        setTitle("Weather and Track Conditions");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        setupUI();
    }

    private void setupUI() {
        // main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // center panel for weather and track selection
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JLabel weatherLabel = new JLabel("Pick Weather:");
        JLabel trackLabel = new JLabel("Pick Track Condition:");

        String[] weatherOptions = {"Sunny", "Rainy", "Snowy", "Windy", "Foggy"};
        weatherComboBox = new JComboBox<>(weatherOptions);

        String[] trackOptions = {"Dry", "Muddy", "Icy", "Soft", "Hard"};
        trackComboBox = new JComboBox<>(trackOptions);

        centerPanel.add(weatherLabel);
        centerPanel.add(weatherComboBox);
        centerPanel.add(trackLabel);
        centerPanel.add(trackComboBox);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // bottom panel for buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // home button on the left
        homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(e -> {
            new MainMenuGUI().setVisible(true);
            dispose();
        });

        // panel for next/back buttons on the right
        JPanel nextBackPanel = new JPanel();
        nextBackPanel.setLayout(new BoxLayout(nextBackPanel, BoxLayout.Y_AXIS));
        nextBackPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        nextButton = new JButton("➡ Next");
        nextButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        nextButton.addActionListener(e -> {
            String selectedWeather = (String) weatherComboBox.getSelectedItem();
            String selectedTrack = (String) trackComboBox.getSelectedItem();

            GameSettings.weather = selectedWeather;
            GameSettings.trackCondition = selectedTrack;

            new HorseEditorGUI().setVisible(true);
            dispose();
        });

        backButton = new JButton("⬅ Back");
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backButton.addActionListener(e -> {
            new TrackSetupGUI().setVisible(true);
            dispose();
        });

        nextBackPanel.add(nextButton);
        nextBackPanel.add(Box.createVerticalStrut(5)); // small space between buttons
        nextBackPanel.add(backButton);

        bottomPanel.add(homeButton, BorderLayout.WEST);
        bottomPanel.add(nextBackPanel, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeatherConditionsGUI().setVisible(true);
        });
    }
}