package Project2GUIPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SortPanel extends JPanel {

    private JComboBox<String> sortComboBox;
    private JLabel sortLabel;

    public SortPanel() {
        initSortComponents();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        add(sortLabel);  // Add the label
        add(sortComboBox);  // Add the combo box
    }

    // Initialize the sort combo box and label
    private void initSortComponents() {
        sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sortLabel.setForeground(new Color(0, 102, 204));  // Dark blue color

        sortComboBox = new JComboBox<>(new String[]{"City", "Price", "Time"});
        sortComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        sortComboBox.setBackground(new Color(224, 255, 255));  // Light cyan color
        sortComboBox.setForeground(new Color(0, 51, 102));  // Navy blue font color
        sortComboBox.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204)));
        sortComboBox.setToolTipText("Sort available trains by city, price, or time.");
    }

    // Add an action listener to the combo box
    public void addSortListener(ActionListener listener) {
        sortComboBox.addActionListener(listener);
    }

    // Get the selected sort option
    public String getSelectedSortOption() {
        return sortComboBox.getSelectedItem().toString().toUpperCase();
    }
}
