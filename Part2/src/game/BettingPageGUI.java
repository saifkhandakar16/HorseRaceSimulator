package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class BettingPageGUI extends JFrame {

    private double balance = 1000.00; // starting balance
    private JTextArea historyArea;
    private JTextField betAmountInput;
    private JButton betButton;
    private JComboBox<String> horseComboBox;
    private JTextArea statsArea;
    private double totalEarnings = 0;
    private JButton homeButton;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();


    private static class Horse {
        char symbol;
        String name;
        double confidence;
        double payoutMultiplier;

        public Horse(char symbol, String name, double confidence) {
            this.symbol = symbol;
            this.name = name;
            this.confidence = confidence;
            // calculate payout multiplier based on confidence (higher confidence = lower payout)
            this.payoutMultiplier = 2.5 - (confidence * 1.5); // range from 1.0x to 2.5x
        }

        public boolean raceOutcome() {
            // base win chance is the confidence level
            double winChance = Math.min(0.9, this.confidence * 0.9);
            return Math.random() < winChance;
        }
    }

    // Your 10 horses
    private Horse[] horses = {
            new Horse('a', "Ayo", 0.7),
            new Horse('b', "Bilal", 0.6),
            new Horse('c', "Charlie", 0.7),
            new Horse('d', "Dave", 0.5),
            new Horse('e', "Eric", 0.8),
            new Horse('f', "Friday", 0.6),
            new Horse('g', "Gregory", 0.4),
            new Horse('h', "Harry", 0.4),
            new Horse('i', "Ilyas", 0.6),
            new Horse('j', "Jeremy", 0.8)
    };

    public BettingPageGUI() {
        setTitle("Horse Betting System");
        setSize(800, 600); // slightly larger to accommodate more horses
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initUI();
        updateStats(); // show initial balance
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // history display area
        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane historyScroll = new JScrollPane(historyArea);

        // stats display area
        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane statsScroll = new JScrollPane(statsArea);

        // combo box for selecting a horse
        String[] horseNames = new String[horses.length];
        for (int i = 0; i < horses.length; i++) {
            horseNames[i] = String.format("%c - %s (%.1fx)",
                    horses[i].symbol, horses[i].name, horses[i].payoutMultiplier);
        }
        horseComboBox = new JComboBox<>(horseNames);

        // input field for the bet amount
        betAmountInput = new JTextField();
        betAmountInput.setPreferredSize(new Dimension(100, 30));

        // button to place a bet
        betButton = new JButton("Place Bet");
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeBet();
            }
        });

        // home button to go back to the main menu
        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuGUI().setVisible(true);
                dispose(); // close current window
            }
        });

        // add components to panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Pick a Horse:"));
        topPanel.add(horseComboBox);
        topPanel.add(new JLabel("Bet Amount:"));
        topPanel.add(betAmountInput);
        topPanel.add(betButton);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(historyScroll, BorderLayout.CENTER);

        // create a bottom panel for stats and home button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statsScroll, BorderLayout.CENTER);

        JPanel homeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        homeButtonPanel.add(homeButton);
        bottomPanel.add(homeButtonPanel, BorderLayout.SOUTH);

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
            JOptionPane.showMessageDialog(this, "You don't have enough money for that bet!");
            return;
        }

        // get selected horse
        int selectedIndex = horseComboBox.getSelectedIndex();
        Horse selectedHorse = horses[selectedIndex];

        // simulate race outcome (true = win, false = loss)
        boolean raceWon = selectedHorse.raceOutcome();
        if (raceWon) {
            double winnings = betAmount * selectedHorse.payoutMultiplier;
            balance += winnings;
            totalEarnings += (winnings - betAmount); // Only count profit
            historyArea.append(String.format("Bet on %s (%c) - Win! Winnings: %s\n",
                    selectedHorse.name, selectedHorse.symbol, currencyFormat.format(winnings)));
        } else {
            balance -= betAmount;
            totalEarnings -= betAmount;
            historyArea.append(String.format("Bet on %s (%c) - Lost. Bet amount: %s\n",
                    selectedHorse.name, selectedHorse.symbol, currencyFormat.format(betAmount)));
        }

        // update stats
        updateStats();

        // reset the bet amount input field
        betAmountInput.setText("");
    }

    private void updateStats() {
        statsArea.setText(String.format("Current Balance: %s\n", currencyFormat.format(balance)));
        statsArea.append(String.format("Total Earnings: %s\n", currencyFormat.format(totalEarnings)));
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