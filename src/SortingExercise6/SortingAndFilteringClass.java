package SortingExercise6;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SortingAndFilteringFunction extends JFrame{

    private JTable table;
    private DefaultTableModel model;

    public SortingAndFilteringFunction(){

        setTitle("Table Sorting and Filtering Example");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(
                new Object[][]{
                        {"Aniket", "Chaubey", 23},
                        {"Shaurya", "Batra", 23},
                        {"Udit", "Joshi", 22},
                        {"Astha", "Prakhar", 21},
                        {"Paras", "Gupta", 24},
                        {"Shivank", "Verma", 25},
                        {"Saurabh", "Kumar", 20},
                        {"Shivanshi", "Trivedi", 27},
                        {"Aniket", "Chaubey", 23},
                        {"Shaurya", "Batra", 23},
                        {"Udit", "Joshi", 22},
                        {"Astha", "Prakhar", 21},
                        {"Paras", "Gupta", 24},
                        {"Shivank", "Verma", 25},
                        {"Saurabh", "Kumar", 20},
                        {"Shivanshi", "Trivedi", 27},
                        {"Aniket", "Chaubey", 23},
                        {"Shaurya", "Batra", 23},
                        {"Udit", "Joshi", 22},
                        {"Astha", "Prakhar", 21},
                        {"Paras", "Gupta", 24},
                        {"Shivank", "Verma", 25},
                        {"Saurabh", "Kumar", 20},
                        {"Shivanshi", "Trivedi", 27},
                        {"Aniket", "Chaubey", 23},
                        {"Shaurya", "Batra", 23},
                        {"Udit", "Joshi", 22},
                        {"Astha", "Prakhar", 21},
                        {"Paras", "Gupta", 24},
                        {"Shivank", "Verma", 25},
                        {"Saurabh", "Kumar", 20},
                        {"Shivanshi", "Trivedi", 27}
                },
                new String[]{"First Name", "Last Name", "Age"}
        );

        table = new JTable(model);

        TableRowSorter sorter = new TableRowSorter(model);
        table.setRowSorter(sorter);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JLabel filterLabel = new JLabel("Filter:");
        controlPanel.add(filterLabel);

        JTextField filterText = new JTextField(15);
        filterText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = filterText.getText();
                if (text.isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });
        controlPanel.add(filterText);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
public class SortingAndFilteringClass {

    public static void main(String[] args) {
        SortingAndFilteringFunction sf = new SortingAndFilteringFunction();
    }
}
