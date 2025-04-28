package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StatsPageGUI extends JFrame {

    private JTextArea statsTextArea;
    private JButton refreshStatsButton;
    private JButton compareHorsesButton;
    private JComboBox<String> horseSelectBox;
    private JComboBox<String> trackSelectBox;
    private JButton homeButton;

    public StatsPageGUI() {
        setTitle("Horse Stats and Comparison");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        // main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // top panel for controls
        JPanel controlPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        // horse selection
        String[] horseNames = GameSettings.horseNames.toArray(new String[0]);  // use the 10 horses from GameSettings
        horseSelectBox = new JComboBox<>(horseNames);
        controlPanel.add(new JLabel("Select Horse:"));
        controlPanel.add(horseSelectBox);

        // track selection
        String[] tracks = {"Oval", "Figure-Eight", "Straight", "Hilly", "Muddy"};
        trackSelectBox = new JComboBox<>(tracks);
        controlPanel.add(new JLabel("Select Track:"));
        controlPanel.add(trackSelectBox);

        // buttons
        refreshStatsButton = new JButton("Refresh Stats");
        refreshStatsButton.addActionListener(e -> updateStats());

        compareHorsesButton = new JButton("Compare Horses");
        compareHorsesButton.addActionListener(e -> {
            String selectedHorse = (String) horseSelectBox.getSelectedItem();
            String selectedTrack = (String) trackSelectBox.getSelectedItem();
            JOptionPane.showMessageDialog(null,
                    "Comparing horse: " + selectedHorse + " on track: " + selectedTrack);
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(refreshStatsButton);
        buttonPanel.add(compareHorsesButton);

        // text area to show stats
        statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        statsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(statsTextArea);

        // bottom panel for buttons (matching HorseEditorGUI layout)
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // home button on the left
        homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(e -> {
            new MainMenuGUI().setVisible(true);
            dispose();
        });
        bottomPanel.add(homeButton, BorderLayout.WEST);

        // add components to main panel
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // adjust layout to match HorseEditorGUI
        mainPanel.remove(buttonPanel); // Remove from SOUTH

        // create a new panel for center content
        JPanel centerContent = new JPanel(new BorderLayout());
        centerContent.add(scrollPane, BorderLayout.CENTER);
        centerContent.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(centerContent, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void updateStats() {
        List<RaceResult> raceHistory = GameSettings.getRaceHistory();

        if (raceHistory.isEmpty()) {
            statsTextArea.setText("No stats available yet.\nRun some races first to collect statistics.");
            return;
        }

        String selectedHorse = (String) horseSelectBox.getSelectedItem();
        String selectedTrack = (String) trackSelectBox.getSelectedItem();

        // calculate stats from race history
        StringBuilder stats = new StringBuilder();
        stats.append("Statistics for ").append(selectedHorse).append(" on ").append(selectedTrack).append(":\n\n");

        // filter results for this horse and track
        int racesOnTrack = 0;
        int wins = 0;
        double totalTime = 0;
        double bestTime = Double.MAX_VALUE;
        double confidenceChange = 0;

        for (RaceResult result : raceHistory) {
            if (result.getTrackName().equals(selectedTrack)) {
                racesOnTrack++;
                for (HorseResult hr : result.getHorseResults()) {
                    if (hr.getHorseName().equals(selectedHorse)) {
                        totalTime += hr.getFinishTime();
                        if (hr.getFinishTime() < bestTime) {
                            bestTime = hr.getFinishTime();
                        }
                        if (hr.getPosition() == 1) {
                            wins++;
                        }
                        confidenceChange += hr.getConfidenceChange();
                        break;
                    }
                }
            }
        }

        if (racesOnTrack == 0) {
            stats.append("No races recorded for this horse on this track.\n");
        } else {
            double winRatio = (wins * 100.0) / racesOnTrack;
            double avgTime = totalTime / racesOnTrack;

            stats.append(String.format("Races on this track: %d\n", racesOnTrack));
            stats.append(String.format("Wins: %d (%.1f%% win ratio)\n", wins, winRatio));
            stats.append(String.format("Average finish time: %.2f seconds\n", avgTime));
            stats.append(String.format("Best time: %.2f seconds\n", bestTime));
            stats.append(String.format("Total confidence change: %.1f\n", confidenceChange));
            stats.append("\n");
        }

        // add general race history
        stats.append("All Race History:\n");
        for (RaceResult result : raceHistory) {
            stats.append(String.format("%s - %s - Winner: %s (%.2f sec)\n",
                    result.getDate(),
                    result.getTrackName(),
                    result.getHorseResults().get(0).getHorseName(),
                    result.getHorseResults().get(0).getFinishTime()));
        }

        statsTextArea.setText(stats.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StatsPageGUI().setVisible(true);
        });
    }
}
