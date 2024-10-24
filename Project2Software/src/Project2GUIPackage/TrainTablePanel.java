package Project2GUIPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TrainTablePanel extends JPanel {

    private JTable trainTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public TrainTablePanel() {
        initTable();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);  // Add the scroll pane to the panel
    }

    // Initialize the train table and scroll pane
    private void initTable() {
        String[] columnNames = {"Train ID", "City", "Seats", "Price", "Time"};
        tableModel = new DefaultTableModel(columnNames, 0);
        trainTable = new JTable(tableModel);
        trainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Allow single row selection
        scrollPane = new JScrollPane(trainTable);  // Wrap the table inside a scroll pane
    }

    // Return the selected row from the table
    public int getSelectedRow() {
        return trainTable.getSelectedRow();
    }

    // Get data from the table at a specific row and column
    public String getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column).toString();
    }

    // Clear the table rows
    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Add rows to the table
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
}

