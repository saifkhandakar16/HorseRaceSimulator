package game;

import javax.swing.*;
import java.awt.*;

public class MainMenuGUI extends JFrame {

    public MainMenuGUI() {
        setTitle("Horse Racing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupComponents();
    }

    private void setupComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Create buttons for each option
        JButton setupButton = new JButton("Set up Track");
        JButton weatherButton = new JButton("Track & Weather");
        JButton horsesButton = new JButton("Edit Horses");
        JButton statsButton = new JButton("View Stats");
        JButton bettingButton = new JButton("Place Bets");

        // Button actions using lambda expressions
        setupButton.addActionListener(e -> {
            new TrackSetupGUI().setVisible(true);  // Open Track Setup screen
            setVisible(false);  // Hide the main menu
        });

        weatherButton.addActionListener(e -> {
            new WeatherConditionsGUI().setVisible(true);  // Open Weather/Track page
            setVisible(false);  // Hide the main menu
        });

        horsesButton.addActionListener(e -> {
            new HorseEditorGUI().setVisible(true);  // Open Horse Customization page
            setVisible(false);  // Hide the main menu
        });

        statsButton.addActionListener(e -> {
            new StatsPageGUI().setVisible(true);  // Open Stats page
            setVisible(false);  // Hide the main menu
        });

        bettingButton.addActionListener(e -> {
            new BettingPageGUI().setVisible(true);  // Open Betting page
            setVisible(false);  // Hide the main menu
        });

        // Add buttons to the panel
        panel.add(setupButton);
        panel.add(weatherButton);
        panel.add(horsesButton);
        panel.add(statsButton);
        panel.add(bettingButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuGUI().setVisible(true);  // Start the main menu
        });
    }
}
