package XMLFileVIewerAndEditorExercise3;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class ProjectFrame extends JFrame {

    Container c;
    JButton show, browse;
    JTextField path;
    JTable table;
    JButton save;
    DefaultTableModel model;
    //JLabel spacing1,spacing2,spacing3,spacing4;
    JButton delete;
    JTextField textFieldID, textFieldName, textFieldAddress, textFieldDOJ, textFieldDesignation, textFieldDU;
    JLabel textLabelID, textLabelName, textLabelAddress, textLabelDOJ, textLabelDesignation, textLabelDU;
    ProjectFrame(){



        ///////////////LAYOUT///////////////
        c = getContentPane();
        GridBagLayout gridBagLayout = new GridBagLayout();
        c.setLayout(gridBagLayout);

        GridBagConstraints gbc = new GridBagConstraints();



        ///////////////PATH FIELD///////////////
        path = new JTextField(60);
        path.setToolTipText("The path of the selected XML file will be displayed here");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipady = 8;
        gbc.insets = new Insets(10,10,10,10);
        c.add(path,gbc);


        ///////////////BROWSE BUTTON///////////////
        browse = new JButton("Browse");
        browse.setToolTipText("Browse an XML file for retrieving data");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                int res = jFileChooser.showOpenDialog(ProjectFrame.this);
                if(res == 0){
                    File sel = jFileChooser.getSelectedFile();
                    String str = sel.getAbsolutePath();
                    if(str.endsWith(".xml")){
                        path.setText(sel.getAbsolutePath());
                        show.setEnabled(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(c,"Choose a XML File","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.ipady = 0;
        c.add(browse,gbc);



        ///////////////SHOW BUTTON///////////////
        show = new JButton("Show");
        show.setToolTipText("For displaying the content of the XML file in the form of a table");
        show.setEnabled(false);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    //

                    File xml = new File(path.getText());
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(xml);


                    System.out.println("XML File Path: " + path.getText());


                    NodeList nodeList = doc.getElementsByTagName("recordi");

                    String[] columnNames = {"Emp ID", "Name", "Address", "DOJ", "Designation", "Delivery Unit"};
                    model = new DefaultTableModel(columnNames, 0);




                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            String empId = element.getElementsByTagName("empid").item(0).getTextContent();
                            String name = element.getElementsByTagName("name").item(0).getTextContent();
                            String address = element.getElementsByTagName("address").item(0).getTextContent();
                            String doj = element.getElementsByTagName("doj").item(0).getTextContent();
                            String designation = element.getElementsByTagName("designation").item(0).getTextContent();
                            String delUnit = element.getElementsByTagName("del_unit").item(0).getTextContent();

                            model.addRow(new Object[]{empId, name, address, doj, designation, delUnit});
                        }
                    }

                    table.setModel(model);
                    save.setEnabled(true);
                    delete.setEnabled(true);


                }
                catch(Exception ex){
                    ex.printStackTrace();

                }
            }
        });
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        c.add(show,gbc);






        ///////////////SAVE and DELETE FUNCTIONALITY///////////////
        gbc.ipady = 20;
        gbc.gridx = 0;
        gbc.gridy = 3;
        JPanel panel = new JPanel();
        add(panel,gbc);

        ///////////////TextField for new data///////////////
        textFieldID = new JTextField(15);
        textFieldID.setToolTipText("Enter the EMP ID to add the data in the table");

        textFieldName = new JTextField(15);
        textFieldName.setToolTipText("Enter the EMP Name to add the data in the table");

        textFieldAddress = new JTextField(15);
        textFieldAddress.setToolTipText("Enter the EMP Address to add the data in the table");

        textFieldDOJ = new JTextField(15);
        textFieldDOJ.setToolTipText("Enter the EMP Date of joining to add the data in the table");

        textFieldDesignation = new JTextField(15);
        textFieldDesignation.setToolTipText("Enter the EMP Designation to add the data in the table");

        textFieldDU = new JTextField(15);
        textFieldDU.setToolTipText("Enter the EMP Delivery Unit to add the data in the table");


        ///////////////SAVE BUTTON///////////////
        save = new JButton("Add");
        save.setToolTipText("For adding the entered value in the table");
        save.setEnabled(false);
        save.setFont(new Font("arial",Font.BOLD,15));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valueEmpID = Integer.parseInt(textFieldID.getText().toString());
                String valueEmpName = textFieldName.getText().toString();
                String valueEmpAddress = textFieldAddress.getText().toString();
                String valueEmpDOJ = textFieldDOJ.getText().toString();
                String valueEmpDesignation = textFieldDesignation.getText().toString();
                String valueEmpDU = textFieldDU.getText().toString();

                Object[] newRow = {valueEmpID, valueEmpName, valueEmpAddress, valueEmpDOJ, valueEmpDesignation, valueEmpDU};
                model.addRow(newRow);

                textFieldID.setText(null);
                textFieldName.setText(null);
                textFieldAddress.setText(null);
                textFieldDOJ.setText(null);
                textFieldDesignation.setText(null);
                textFieldDU.setText(null);


            }
        });

        ///////////////DELETE BUTTON///////////////

        delete = new JButton("Delete");
        delete.setToolTipText("Select the row to be deleted");
        delete.setEnabled(false);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(table.getSelectedRow() != -1){
                    model.removeRow(table.getSelectedRow());
                }

            }
        });


        ///////////////MAIN LAYOUT///////////////
        panel.setLayout(new GridBagLayout());

        textLabelID = new JLabel("EMP ID");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(textLabelID, gbc);

        gbc.gridx = 1;
        panel.add(textFieldID,gbc);

        textLabelName = new JLabel("NAME");
        gbc.gridx = 2;
        panel.add(textLabelName,gbc);

        gbc.gridx = 3;
        panel.add(textFieldName,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        textLabelAddress = new JLabel("ADDRESS");
        panel.add(textLabelAddress,gbc);

        gbc.gridx = 1;
        panel.add(textFieldAddress,gbc);


        textLabelDOJ = new JLabel("DOJ");
        gbc.gridx = 2;
        panel.add(textLabelDOJ,gbc);

        gbc.gridx = 3;

        panel.add(textFieldDOJ,gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        textLabelDesignation = new JLabel("DESIGNATION");
        panel.add(textLabelDesignation,gbc);

        gbc.gridx = 1;
        panel.add(textFieldDesignation,gbc);

        gbc.gridx = 2;
        textLabelDU = new JLabel("DU");
        panel.add(textLabelDU,gbc);

        gbc.gridx = 3;
        panel.add(textFieldDU,gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.ipady = 8;
        panel.add(save,gbc);

        gbc.gridy = 2;
        gbc.ipady = 8;
        panel.add(delete,gbc);

        ///////////////TABLE///////////////
        table = new JTable(12,6);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipadx = 20;
        gbc.insets = new Insets(0,0,10,0);
        c.add(table,gbc);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        ///////////////MAIN FRAME///////////////
        setTitle("XML Table Viewer");
        setVisible(true);
        setBounds(50,50,800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        repaint();
        revalidate();


    }
}




public class FileViewerAndEditor {

    public static void main(String[] args) {

        ProjectFrame pf = new ProjectFrame();
    }
}