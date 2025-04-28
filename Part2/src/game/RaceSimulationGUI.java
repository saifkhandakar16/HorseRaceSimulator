package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RaceSimulationGUI extends JFrame {

    private JPanel raceTrackPanel;
    private JLabel[] horseLabels;
    private JLabel[] nameLabels;
    private Timer raceTimer;
    private int[] horsePositions;
    private int laneCount;
    private int trackLength;
    private double speedModifier;
    private Horse[] horses;
    private boolean[] hasFallen;
    private boolean raceFinished;

    public RaceSimulationGUI() {
        setTitle("Race Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLayout(new BorderLayout());

        // initialise horses
        initializeHorses();

        // top panel for Home and Back buttons
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton homeButton = new JButton("Home");
        JButton backButton = new JButton("Back");
        topPanel.add(homeButton);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // center panel for Race Track
        raceTrackPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // draw lane dividers
                g.setColor(Color.BLACK);
                for (int i = 1; i <= laneCount; i++) {
                    int y = 50 + (i * 60) - 30;
                    g.drawLine(0, y, getWidth(), y);
                }

                // draw finish line
                g.setColor(Color.RED);
                g.fillRect(getWidth() - 100, 0, 5, getHeight());

                // draw starting line
                g.setColor(Color.BLUE);
                g.fillRect(10, 0, 5, getHeight());
            }
        };
        raceTrackPanel.setLayout(null);
        raceTrackPanel.setBackground(new Color(0, 100, 0)); // darker green for better contrast
        add(raceTrackPanel, BorderLayout.CENTER);

        // bottom panel for Start Race button
        JPanel bottomPanel = new JPanel();
        JButton startRaceButton = new JButton("Start Race");
        bottomPanel.add(startRaceButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // set up event listeners
        homeButton.addActionListener(e -> {
            new MainMenuGUI().setVisible(true);
            dispose();
        });

        backButton.addActionListener(e -> {
            new HorseEditorGUI().setVisible(true);
            dispose();
        });

        startRaceButton.addActionListener(e -> startRace());

        setLocationRelativeTo(null);
        setVisible(true);

        setupRace();
    }

    private void initializeHorses() {
        int numberOfHorses = GameSettings.laneCount;  // set based on the selected number of lanes
        horses = new Horse[numberOfHorses];  // create an array of horses for the number of lanes
        hasFallen = new boolean[numberOfHorses];

        // initialise horses based on the selected number of lanes
        for (int i = 0; i < numberOfHorses; i++) {
            horses[i] = new Horse((char) ('a' + i), "Horse " + (i + 1), Math.random() * 0.6 + 0.4);  // Random confidence
            hasFallen[i] = false;
        }
    }

    private void setupRace() {
        laneCount = GameSettings.laneCount;  // fetch from GameSettings instead of horses.length
        trackLength = GameSettings.trackLength;

        // weather impact
        switch (GameSettings.trackCondition) {
            case "Muddy":
                speedModifier = 0.8;
                raceTrackPanel.setBackground(new Color(101, 67, 33)); // muddy brown
                break;
            case "Icy":
                speedModifier = 0.7;
                raceTrackPanel.setBackground(new Color(200, 240, 255)); // light icy blue
                break;
            case "Soft":
                speedModifier = 0.9;
                raceTrackPanel.setBackground(new Color(210, 180, 140)); // soft sand color
                break;
            case "Hard":
                speedModifier = 1.0;
                raceTrackPanel.setBackground(new Color(160, 160, 160)); // light gray
                break;
            default:
                speedModifier = 1.0;
                raceTrackPanel.setBackground(new Color(0, 100, 0)); // darker green
        }

        // setup horse visuals
        horseLabels = new JLabel[laneCount];
        nameLabels = new JLabel[laneCount];
        horsePositions = new int[laneCount];

        for (int i = 0; i < laneCount; i++) {
            JLabel horse = new JLabel(GameSettings.horseSymbol != null ? GameSettings.horseSymbol : "🐎");
            horse.setFont(new Font("Arial", Font.PLAIN, 32));
            horse.setBounds(10, 50 + (i * 60), 150, 50);

            // set the correct horse name label
            JLabel nameLabel = new JLabel(horses[i].getName());  // use the getName() method
            nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setBounds(10, 50 + (i * 60) + 40, 150, 20);

            raceTrackPanel.add(horse);
            raceTrackPanel.add(nameLabel);
            horseLabels[i] = horse;
            nameLabels[i] = nameLabel;
            horsePositions[i] = 10;
            hasFallen[i] = false;
        }

    }

    private void startRace() {
        if (raceTimer != null && raceTimer.isRunning()) {
            return;
        }

        // reset fallen status
        for (int i = 0; i < laneCount; i++) {
            hasFallen[i] = false;
            horseLabels[i].setText(GameSettings.horseSymbol != null ? GameSettings.horseSymbol : "🐎");
            horsePositions[i] = 10;
            horseLabels[i].setLocation(horsePositions[i], horseLabels[i].getY());
        }

        raceTimer = new Timer(50, new ActionListener() {
            Random random = new Random();
            boolean raceFinished = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean allHorsesFallen = true; // flag to track if all horses have fallen

                for (int i = 0; i < laneCount; i++) {
                    if (hasFallen[i]) {
                        continue; // skip movement if horse has fallen
                    } else {
                        allHorsesFallen = false; // at least one horse is still running
                    }

                    // base speed includes the horse's individual speed factor
                    int baseSpeed = (int)(5 * horses[i].getConfidence());

                    // apply breed impact
                    switch (GameSettings.horseBreed) {
                        case "Arabian":
                            baseSpeed += 2;
                            break;
                        case "Quarter Horse":
                            baseSpeed += 1;
                            break;
                        case "Mustang":
                            baseSpeed += random.nextInt(3);
                            break;
                        case "Thoroughbred":
                            baseSpeed += 3;
                            break;
                    }

                    // chance to fall (higher for less confident horses)
                    if (random.nextDouble() < (0.009 * (1 - horses[i].getConfidence()))) {
                        hasFallen[i] = true;
                        horseLabels[i].setText("💥");
                        nameLabels[i].setForeground(Color.RED);
                        continue; // skip movement this turn
                    }

                    // apply saddle impact
                    if ("Western".equals(GameSettings.horseSaddle)) {
                        baseSpeed += 1;
                    } else if ("English".equals(GameSettings.horseSaddle)) {
                        baseSpeed += 2;
                    }

                    // apply horseshoes impact
                    if ("Steel".equals(GameSettings.horseHorseshoes)) {
                        baseSpeed += 1;
                    } else if ("Aluminum".equals(GameSettings.horseHorseshoes)) {
                        baseSpeed += 2;
                    }

                    // apply track condition effect
                    double finalSpeed = baseSpeed * speedModifier;

                    // adjust speed based on track shape
                    if ("Figure-eight".equals(GameSettings.trackShape) && i % 2 == 0) {
                        finalSpeed *= 0.9;  // slower on turns for some lanes
                    }

                    // apply random slight variations
                    finalSpeed += random.nextInt(3) - 1;

                    // Ensure minimum speed
                    if (finalSpeed < 1) finalSpeed = 1;

                    // update horse positions
                    horsePositions[i] += finalSpeed;

                    if (horseLabels[i] != null) {
                        horseLabels[i].setLocation(horsePositions[i], horseLabels[i].getY());
                        nameLabels[i].setLocation(horsePositions[i], nameLabels[i].getY());
                    }

                    // check if any horse finishes
                    if (horsePositions[i] >= raceTrackPanel.getWidth() - 100 && !raceFinished) {
                        raceFinished = true;
                        raceTimer.stop();

                        // check if any horses are still running
                        boolean anyFinished = false;
                        for (int j = 0; j < laneCount; j++) {
                            if (!hasFallen[j] && horsePositions[j] >= raceTrackPanel.getWidth() - 100) {
                                anyFinished = true;
                                break;
                            }
                        }

                        if (anyFinished) {
                            JOptionPane.showMessageDialog(null, horses[i].getName() + " wins the race!");
                        } else {
                            JOptionPane.showMessageDialog(null, "All horses fell! No winner this race.");
                        }

                        new StatsPageGUI().setVisible(true);
                        dispose();
                        break;
                    }
                }

                // check if all horses have fallen
                if (allHorsesFallen && !raceFinished) {
                    raceFinished = true;
                    raceTimer.stop();
                    JOptionPane.showMessageDialog(null, "All horses fell! No winner this race.");
                    new StatsPageGUI().setVisible(true);
                    dispose();
                }
            }
        });

        raceTimer.start();
    }
}
