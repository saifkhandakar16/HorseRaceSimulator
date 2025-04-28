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

        // create buttons for each option
        JButton setupButton = new JButton("Set up Track");
        JButton weatherButton = new JButton("Track & Weather");
        JButton horsesButton = new JButton("Edit Horses");
        JButton statsButton = new JButton("View Stats");
        JButton bettingButton = new JButton("Place Bets");

        // button actions using lambda expressions
        setupButton.addActionListener(e -> {
            new TrackSetupGUI().setVisible(true);  // open Track Setup screen
            setVisible(false);  // hide the main menu
        });

        weatherButton.addActionListener(e -> {
            new WeatherConditionsGUI().setVisible(true);  // open Weather/Track page
            setVisible(false);  // hide the main menu
        });

        horsesButton.addActionListener(e -> {
            new HorseEditorGUI().setVisible(true);  // open Horse Customization page
            setVisible(false);  // hide the main menu
        });

        statsButton.addActionListener(e -> {
            new StatsPageGUI().setVisible(true);  // open Stats page
            setVisible(false);  // hide the main menu
        });

        bettingButton.addActionListener(e -> {
            new BettingPageGUI().setVisible(true);  // open Betting page
            setVisible(false);  // hide the main menu
        });

        // add buttons to the panel
        panel.add(setupButton);
        panel.add(weatherButton);
        panel.add(horsesButton);
        panel.add(statsButton);
        panel.add(bettingButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuGUI().setVisible(true);  // start the main menu
        });
    }
}
