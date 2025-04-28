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
    private JButton homeButton;

    public HorseEditorGUI() {
        setTitle("Customise Your Horse");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents(); // initialise buttons and dropdowns
        setupUI();
    }

    private void initializeComponents() {
        // initialise ComboBoxes
        String[] breedChoices = {"Arabian", "Thoroughbred", "Quarter Horse", "Mustang"};
        breedDropDown = new JComboBox<>(breedChoices);

        String[] coatColorChoices = {"Brown", "Black", "White", "Gray"};
        coatColorDropDown = new JComboBox<>(coatColorChoices);

        String[] symbolChoices = {"🦄 Unicorn", "🐴 Horse"};
        symbolDropDown = new JComboBox<>(symbolChoices);

        String[] saddleChoices = {"Leather", "Synthetic", "Western", "English"};
        saddleDropDown = new JComboBox<>(saddleChoices);

        String[] horseshoesChoices = {"Steel", "Aluminum", "Rubber", "None"};
        horseshoesDropDown = new JComboBox<>(horseshoesChoices);

        // initialise Buttons
        nextButton = new JButton("➡ Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // save selected horse settings into GameSettings
                GameSettings.horseBreed = (String) breedDropDown.getSelectedItem();
                GameSettings.horseCoatColor = (String) coatColorDropDown.getSelectedItem();
                GameSettings.horseSymbol = (String) symbolDropDown.getSelectedItem();
                GameSettings.horseSaddle = (String) saddleDropDown.getSelectedItem();
                GameSettings.horseHorseshoes = (String) horseshoesDropDown.getSelectedItem();

                // then go to the next screen
                new RaceSimulationGUI().setVisible(true);
                dispose();
            }
        });

        backButton = new JButton("⬅ Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WeatherConditionsGUI().setVisible(true);
                dispose();
            }
        });

        homeButton = new JButton("🏠 Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuGUI().setVisible(true);
                dispose();
            }
        });
    }

    private void setupUI() {
        // main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // center panel for horse customization options
        JPanel centerPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // labels
        JLabel breedLabel = new JLabel("Pick a Horse Breed: 🐎");
        JLabel coatColorLabel = new JLabel("Pick a Coat Color: 🎨");
        JLabel symbolLabel = new JLabel("Pick a Symbol: 🦄");
        JLabel saddleLabel = new JLabel("Pick a Saddle: 🏇");
        JLabel horseshoesLabel = new JLabel("Pick Horseshoes: 👢");

        // add components to center panel
        centerPanel.add(breedLabel);
        centerPanel.add(breedDropDown);
        centerPanel.add(coatColorLabel);
        centerPanel.add(coatColorDropDown);
        centerPanel.add(symbolLabel);
        centerPanel.add(symbolDropDown);
        centerPanel.add(saddleLabel);
        centerPanel.add(saddleDropDown);
        centerPanel.add(horseshoesLabel);
        centerPanel.add(horseshoesDropDown);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // bottom panel for buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // home button on the left
        bottomPanel.add(homeButton, BorderLayout.WEST);

        // panel for next/back buttons on the right
        JPanel nextBackPanel = new JPanel();
        nextBackPanel.setLayout(new BoxLayout(nextBackPanel, BoxLayout.Y_AXIS));
        nextBackPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        nextButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        backButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        nextBackPanel.add(nextButton);
        nextBackPanel.add(Box.createVerticalStrut(5)); // small space between buttons
        nextBackPanel.add(backButton);

        bottomPanel.add(nextBackPanel, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HorseEditorGUI().setVisible(true);
        });
    }
}