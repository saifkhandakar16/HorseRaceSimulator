package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPageGUI extends JFrame {

    private JTextArea statsTextArea;
    private JButton refreshStatsButton;
    private JButton compareHorsesButton;
    private JComboBox<String> horseSelectBox;
    private JComboBox<String> trackSelectBox;
    private JButton homeButton;  // Button to go back to the main menu

    public StatsPageGUI() {  // Constructor that matches the class name
        setTitle("Horse Stats and Comparison");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Text area to show stats
        statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        statsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(statsTextArea);

        // Buttons
        refreshStatsButton = new JButton("Refresh Stats");
        refreshStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulating refreshing stats (you would actually pull data here)
                statsTextArea.setText("Refreshing stats...\n");
                updateStats();
            }
        });

        compareHorsesButton = new JButton("Compare Horses");
        compareHorsesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate comparing horses
                String selectedHorse = (String) horseSelectBox.getSelectedItem();
                String selectedTrack = (String) trackSelectBox.getSelectedItem();
                JOptionPane.showMessageDialog(null,
                        "Comparing horse: " + selectedHorse + " on track: " + selectedTrack);
            }
        });

        // ComboBoxes for horse and track selection
        String[] horses = {"Horse 1", "Horse 2", "Horse 3"};
        horseSelectBox = new JComboBox<>(horses);

        String[] tracks = {"Track A", "Track B", "Track C"};
        trackSelectBox = new JComboBox<>(tracks);

        // Home button to go back to the main menu
        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main menu
                new MainMenuGUI().setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Panel for controls like ComboBoxes and Buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2, 10, 10));  // Layout for controls
        controlPanel.add(new JLabel("Select Horse:"));
        controlPanel.add(horseSelectBox);
        controlPanel.add(new JLabel("Select Track:"));
        controlPanel.add(trackSelectBox);
        controlPanel.add(refreshStatsButton);
        controlPanel.add(compareHorsesButton);

        // Panel for the Home button
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(homeButton);  // Home button placed at the bottom

        panel.add(controlPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);  // Home button placed at the bottom

        add(panel);
    }

    private void updateStats() {
        // Simulate updating the stats (you would use actual data here)
        statsTextArea.setText("Horse Stats:\n");
        statsTextArea.append("Average Speed: 40 km/h\n");
        statsTextArea.append("Best Time: 2 min 30 sec\n");
        statsTextArea.append("Win Ratio: 75%\n");
        statsTextArea.append("Confidence Change: +5\n");
        statsTextArea.append("\nTrack Performance:\n");
        statsTextArea.append("Best Time: 2 min 15 sec on Track A\n");
        statsTextArea.append("\nBetting Analysis:\n");
        statsTextArea.append("Betting Trends: High bets on Horse 1\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StatsPageGUI().setVisible(true);
        });
    }
}
