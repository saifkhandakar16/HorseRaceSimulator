package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaceSimulationGUI extends JFrame {

    private JButton startRaceButton;

    public RaceSimulationGUI() {
        setTitle("Race Simulation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Start Race Button
        startRaceButton = new JButton("Start Race");
        startRaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Placeholder action to simulate starting the race
                JOptionPane.showMessageDialog(null, "The Race is Starting!");
                // Here you can trigger the race logic
            }
        });

        panel.add(startRaceButton, BorderLayout.CENTER);
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RaceSimulationGUI().setVisible(true);  // Start the Race Simulation window
        });
    }
}

