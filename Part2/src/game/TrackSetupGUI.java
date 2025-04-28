package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackSetupGUI extends JFrame {

    private JComboBox<Integer> laneComboBox;
    private JTextField trackLengthTextField;
    private JComboBox<String> shapeComboBox;
    private JButton nextButton;
    private JButton backButton;

    public TrackSetupGUI() {
        setTitle("Track Setup");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel laneLabel = new JLabel("Choose Number of Lanes (1-15):");
        JLabel lengthLabel = new JLabel("Track Length (m):");
        JLabel shapeLabel = new JLabel("Choose Track Shape:");

        // create array for 1-15 lane options
        Integer[] laneOptions = new Integer[15];
        for (int i = 0; i < 15; i++) {
            laneOptions[i] = i + 1;
        }
        laneComboBox = new JComboBox<>(laneOptions);

        trackLengthTextField = new JTextField("1000");

        String[] shapes = {"Oval", "Figure-eight", "ZigZag"};
        shapeComboBox = new JComboBox<>(shapes);

        // next button
        nextButton = new JButton("➡ Next");
        nextButton.addActionListener(e -> {
            int laneCount = (int) laneComboBox.getSelectedItem();
            String trackLengthStr = trackLengthTextField.getText();
            String trackShape = (String) shapeComboBox.getSelectedItem();

            try {
                int length = Integer.parseInt(trackLengthStr);

                if (length <= 0) {
                    JOptionPane.showMessageDialog(null, "Track length must be positive.");
                    return;
                }

                // save into GameSettings
                GameSettings.laneCount = laneCount;
                GameSettings.trackLength = length;
                GameSettings.trackShape = trackShape;

                // if more than 10 lanes, set empty lanes
                if (laneCount > 10) {
                    GameSettings.emptyLanes = laneCount - 10;
                } else {
                    GameSettings.emptyLanes = 0;
                }

                new WeatherConditionsGUI().setVisible(true);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid track length.");
            }
        });

        // back button
        backButton = new JButton("⬅ Back");
        backButton.addActionListener(e -> {
            new MainMenuGUI().setVisible(true);
            dispose();
        });

        panel.add(laneLabel);
        panel.add(laneComboBox);
        panel.add(lengthLabel);
        panel.add(trackLengthTextField);
        panel.add(shapeLabel);
        panel.add(shapeComboBox);
        panel.add(new JLabel());
        panel.add(nextButton);
        panel.add(new JLabel());
        panel.add(backButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TrackSetupGUI().setVisible(true);
        });
    }
}