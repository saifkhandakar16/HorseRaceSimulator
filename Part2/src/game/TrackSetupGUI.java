package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackSetupGUI extends JFrame {

    private JComboBox<Integer> laneComboBox;  // ComboBox for lane selection
    private JTextField trackLengthTextField;
    private JComboBox<String> shapeComboBox;
    private JButton startButton;
    private JButton backButton; // Button to go back

    public TrackSetupGUI() {
        setTitle("Track Setup");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Grid layout with 5 rows and 2 columns

        // Labels for options
        JLabel laneLabel = new JLabel("Choose Number of Lanes:");
        JLabel lengthLabel = new JLabel("Track Length (m):");
        JLabel shapeLabel = new JLabel("Choose Track Shape:");

        // ComboBox for selecting number of lanes
        Integer[] laneOptions = {2, 4, 6, 8, 10};
        laneComboBox = new JComboBox<>(laneOptions);  // ComboBox for lanes

        // TextField for track length input
        trackLengthTextField = new JTextField("1000");  // Default value is 1000

        // ComboBox for track shapes
        String[] shapes = {"Oval", "Figure-eight", "ZigZag"};
        shapeComboBox = new JComboBox<>(shapes);

        // Button to start the race
        startButton = new JButton("Start Race");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected options
                int laneCount = (int) laneComboBox.getSelectedItem();
                String trackLength = trackLengthTextField.getText();
                String trackShape = (String) shapeComboBox.getSelectedItem();

                // Validate track length and show selected settings
                try {
                    int length = Integer.parseInt(trackLength);
                    JOptionPane.showMessageDialog(null,
                            "Track Setup:\n" +
                                    "Lanes: " + laneCount + "\n" +
                                    "Track Length: " + length + " meters\n" +
                                    "Track Shape: " + trackShape);

                    // Placeholder for starting the race
                    new RaceSimulationGUI().setVisible(true);  // Go to the race screen
                    dispose(); // Close track setup window
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid track length.");
                }
            }
        });

        // Button to go back
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main menu
                new MainMenuGUI().setVisible(true);
                dispose(); // Close current window
            }
        });

        // Adding components to the panel
        panel.add(laneLabel);
        panel.add(laneComboBox);
        panel.add(lengthLabel);
        panel.add(trackLengthTextField);
        panel.add(shapeLabel);
        panel.add(shapeComboBox);
        panel.add(new JLabel());  // Empty space
        panel.add(startButton);  // Add Start Race button
        panel.add(new JLabel());  // Empty space
        panel.add(backButton);   // Add Back button

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrackSetupGUI().setVisible(true);  // Show Track Setup window
            }
        });
    }
}
