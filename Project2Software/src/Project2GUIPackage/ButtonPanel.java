package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {

    private JButton selectTrainButton;
    private JButton quitButton;
    private JButton backButton;

    public ButtonPanel() {
        initButtons();
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(192, 210, 238));  // Set the background color of the panel to match the frame

        // Add buttons to the panel
        add(selectTrainButton);
        add(quitButton);
        add(backButton);
    }

    // Initialize the buttons
    private void initButtons() {
        selectTrainButton = new JButton("Select Train");
        selectTrainButton.setPreferredSize(new Dimension(130, 25));

        quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(100, 25));
        quitButton.setBackground(Color.RED);
        quitButton.setForeground(Color.WHITE);  // Ensure visibility of the text

        backButton = new JButton("BACK");
        backButton.setPreferredSize(new Dimension(100, 25));
    }

    // Getters for the buttons, so listeners can be added in the main frame class
    public JButton getSelectTrainButton() {
        return selectTrainButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
