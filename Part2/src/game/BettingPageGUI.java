package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingPageGUI extends JFrame {

    private double balance = 1000.00; // Starting balance
    private JTextArea historyArea;
    private JTextField betAmountInput;
    private JButton betButton;
    private JComboBox<String> horseComboBox;
    private JTextArea statsArea;
    private double totalEarnings = 0;
    private JButton homeButton;

    public BettingPageGUI() {
        setTitle("Betting System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // History display area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane historyScroll = new JScrollPane(historyArea);

        // Stats display area
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane statsScroll = new JScrollPane(statsArea);

        // Combo box for selecting a horse
        String[] horses = {"Horse 1", "Horse 2", "Horse 3"};
        horseComboBox = new JComboBox<>(horses);

        // Input field for the bet amount
        betAmountInput = new JTextField();
        betAmountInput.setPreferredSize(new Dimension(100, 30));

        // Button to place a bet
        betButton = new JButton("Place Bet");
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeBet();
            }
        });

        // Home button to go back to the main menu
        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuGUI().setVisible(true);
                dispose(); // Close current window
            }
        });

        // Add components to panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Pick a Horse:"));
        topPanel.add(horseComboBox);
        topPanel.add(new JLabel("Bet Amount:"));
        topPanel.add(betAmountInput);
        topPanel.add(betButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(historyScroll, BorderLayout.CENTER);
        panel.add(statsScroll, BorderLayout.SOUTH);

        // Add home button at the bottom
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(homeButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void placeBet() {
        String betAmountText = betAmountInput.getText();
        double betAmount = 0;

        try {
            betAmount = Double.parseDouble(betAmountText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the bet!");
            return;
        }

        if (betAmount <= 0) {
            JOptionPane.showMessageDialog(this, "The bet amount must be positive!");
            return;
        }

        if (betAmount > balance) {
            JOptionPane.showMessageDialog(this, "You don’t have enough money for that bet!");
            return;
        }

        // Random outcome for the race
        String selectedHorse = (String) horseComboBox.getSelectedItem();
        double raceResult = Math.random(); // Random result (win or lose)

        if (raceResult > 0.5) {
            // Win case
            double winnings = betAmount * 2; // Assuming 2x payout for a win
            balance += winnings;
            totalEarnings += winnings;
            historyArea.append("Bet on " + selectedHorse + " - Win! Winnings: " + winnings + "\n");
        } else {
            // Loss case
            balance -= betAmount;
            historyArea.append("Bet on " + selectedHorse + " - Lost. Bet amount: " + betAmount + "\n");
        }

        // Update stats
        updateStats();

        // Reset the bet amount input field
        betAmountInput.setText("");
    }

    private void updateStats() {
        statsArea.setText("Balance: " + balance + "\n");
        statsArea.append("Total Earnings: " + totalEarnings + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BettingPageGUI().setVisible(true);
            }
        });
    }
}
