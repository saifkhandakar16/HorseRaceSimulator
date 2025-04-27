package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherConditionsGUI extends JFrame {

    private JComboBox<String> weatherComboBox;
    private JComboBox<String> trackComboBox;
    private JButton nextButton;
    private JButton backButton; // Back button
    private JButton homeButton; // Home button

    public WeatherConditionsGUI() {
        setTitle("Weather and Track Conditions");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Layout for 6 rows (added space for Home button)
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels for options
        JLabel weatherLabel = new JLabel("Pick Weather:");
        JLabel trackLabel = new JLabel("Pick Track Condition:");

        // Weather options
        String[] weatherOptions = {"Sunny", "Rainy", "Snowy", "Windy", "Foggy"};
        weatherComboBox = new JComboBox<>(weatherOptions);

        // Track condition options
        String[] trackOptions = {"Dry", "Muddy", "Icy", "Soft", "Hard"};
        trackComboBox = new JComboBox<>(trackOptions);

        // Next button to move forward
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWeather = (String) weatherComboBox.getSelectedItem();
                String selectedTrack = (String) trackComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null,
                        "Weather: " + selectedWeather + "\nTrack: " + selectedTrack);

                // After clicking next, go to Horse Customization screen
                new HorseEditorGUI().setVisible(true);
                dispose(); // Close current window
            }
        });

        // Back button to go to previous screen
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TrackSetupGUI().setVisible(true); // Go back to Track Setup screen
                dispose(); // Close current window
            }
        });

        // Home button to go back to the main menu
        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuGUI().setVisible(true); // Go back to the main menu
                dispose(); // Close current window
            }
        });

        // Add components to the panel
        panel.add(weatherLabel);
        panel.add(weatherComboBox);
        panel.add(trackLabel);
        panel.add(trackComboBox);
        panel.add(new JLabel()); // Empty space
        panel.add(backButton);  // Add Back button
        panel.add(homeButton);  // Add Home button
        panel.add(new JLabel()); // Empty space
        panel.add(nextButton);   // Add Next button

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherConditionsGUI().setVisible(true); // Start Weather/Track conditions window
            }
        });
    }
}
