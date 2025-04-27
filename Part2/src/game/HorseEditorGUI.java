package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorseEditorGUI extends JFrame {

    private JComboBox<String> breedDropDown;
    private JComboBox<String> coatColorDropDown;
    private JComboBox<String> symbolDropDown;
    private JComboBox<String> saddleDropDown;
    private JComboBox<String> horseshoesDropDown;
    private JButton nextButton;
    private JButton backButton;
    private JButton homeButton;  // Button to go back to the main menu

    public HorseEditorGUI() {
        setTitle("Customize Your Horse");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10)); // Grid layout for inputs and buttons
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels and ComboBoxes for horse customization options
        JLabel breedLabel = new JLabel("Pick a Horse Breed: 🐎");
        String[] breedChoices = {"Arabian", "Thoroughbred", "Quarter Horse", "Mustang"};
        breedDropDown = new JComboBox<>(breedChoices);

        JLabel coatColorLabel = new JLabel("Pick a Coat Color: 🎨");
        String[] coatColorChoices = {"Brown", "Black", "White", "Gray"};
        coatColorDropDown = new JComboBox<>(coatColorChoices);

        JLabel symbolLabel = new JLabel("Pick a Symbol: 🦄");
        String[] symbolChoices = {"🦄 Unicorn", "🐴 Horse"};
        symbolDropDown = new JComboBox<>(symbolChoices);

        JLabel saddleLabel = new JLabel("Pick a Saddle: 🏇");
        String[] saddleChoices = {"Leather", "Synthetic", "Western", "English"};
        saddleDropDown = new JComboBox<>(saddleChoices);

        JLabel horseshoesLabel = new JLabel("Pick Horseshoes: 👢");
        String[] horseshoesChoices = {"Steel", "Aluminum", "Rubber", "None"};
        horseshoesDropDown = new JComboBox<>(horseshoesChoices);

        // Next button
        nextButton = new JButton("➡️ Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Move to the next screen (e.g., stats page)
                new StatsPageGUI().setVisible(true);
                dispose(); // Close this window
            }
        });

        // Back button
        backButton = new JButton("⬅️ Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the Weather/Track Conditions screen
                new WeatherConditionsGUI().setVisible(true);
                dispose(); // Close this window
            }
        });

        // Home button
        homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go back to the main menu
                new MainMenuGUI().setVisible(true);
                dispose(); // Close this window
            }
        });

        // Add all components to the panel
        panel.add(breedLabel);
        panel.add(breedDropDown);
        panel.add(coatColorLabel);
        panel.add(coatColorDropDown);
        panel.add(symbolLabel);
        panel.add(symbolDropDown);
        panel.add(saddleLabel);
        panel.add(saddleDropDown);
        panel.add(horseshoesLabel);
        panel.add(horseshoesDropDown);
        panel.add(backButton); // Add Back button
        panel.add(nextButton); // Add Next button
        panel.add(homeButton); // Add Home button

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HorseEditorGUI().setVisible(true); // Start the Horse Customization window
        });
    }
}
