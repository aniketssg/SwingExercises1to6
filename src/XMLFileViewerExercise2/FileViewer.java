package XMLFileViewerExercise2;

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
//                        show.setEnabled(true);
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
        //show.setEnabled(false);
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    File xml = new File(path.getText());
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.parse(xml);

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



                }
                catch(Exception ex){
                    ex.printStackTrace();

                }
            }
        });
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 2;
        c.add(show,gbc);




//        ///////////////TABLE///////////////
        table = new JTable(12,6);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.ipadx = 20;
        gbc.insets = new Insets(0,0,10,0);
        c.add(table,gbc);


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


public class FileViewer {

    public static void main(String[] args) {
        ProjectFrame pf = new ProjectFrame();
    }
}

