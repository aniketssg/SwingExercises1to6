package LoginValidationandFileUploaderExercise5;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class LoginPanel extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel() {
        setTitle("Login Panel");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        add(usernameLabel);

        usernameField = new JTextField();
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validateCredentials();
            }
        });
        add(loginButton);


        setVisible(true);
    }

    private void validateCredentials() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            File file = new File("credentials.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("credential");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String userId = element.getElementsByTagName("userid").item(0).getTextContent();
                    String pwd = element.getElementsByTagName("password").item(0).getTextContent();

                    if (username.equals(userId) && password.equals(pwd)) {
                        JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.PLAIN_MESSAGE);
                        RecordUploaderPanel rup = new RecordUploaderPanel();
                        return;
                    } else if (username.equals("")) {
                        JOptionPane.showMessageDialog(this, "UserID field can not be left blank", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else if (password.equals("")) {
                        JOptionPane.showMessageDialog(this, "Password field can not be left blank", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else if (username.equals(userId) && password != pwd) {
                        JOptionPane.showMessageDialog(this, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "User does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    class RecordUploaderPanel extends JFrame {

        private JTextField browseField;
        private JTextField delayField;
        private JTextField fileNameField;
        private JTextField filePathField;
        private JButton browseButton;
        private JButton startUploadButton;
        private JButton resetButton;
        private JProgressBar progressBar;

        public RecordUploaderPanel() {
            setTitle("Record Uploader");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JPanel uploadDetailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            uploadDetailsPanel.setBorder(BorderFactory.createTitledBorder("Upload Details"));

            browseField = new JTextField(20);
            browseField.setEditable(false);
            uploadDetailsPanel.add(browseField);

            browseButton = new JButton("Browse");
            browseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    browseFile();
                }
            });
            uploadDetailsPanel.add(browseButton);

            JLabel delayLabel = new JLabel("Upload Delay (in seconds):");
            uploadDetailsPanel.add(delayLabel);

            delayField = new JTextField(5);
            uploadDetailsPanel.add(delayField);

            panel.add(uploadDetailsPanel);

            JPanel newFileDetailsPanel = new JPanel(new GridLayout(2, 2));
            newFileDetailsPanel.setBorder(BorderFactory.createTitledBorder("New File Details"));

            JLabel fileNameLabel = new JLabel("File Name:");
            newFileDetailsPanel.add(fileNameLabel);

            fileNameField = new JTextField(20);
            newFileDetailsPanel.add(fileNameField);

            JLabel filePathLabel = new JLabel("File Path:");
            newFileDetailsPanel.add(filePathLabel);

            filePathField = new JTextField(20);
            newFileDetailsPanel.add(filePathField);

            panel.add(newFileDetailsPanel);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            startUploadButton = new JButton("Start Upload");
            startUploadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    startUpload();
                }
            });
            buttonPanel.add(startUploadButton);

            resetButton = new JButton("Reset");
            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetFields();
                }
            });
            buttonPanel.add(resetButton);

            panel.add(buttonPanel);

            progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            panel.add(progressBar);

            add(panel);
            setVisible(true);
        }

        private void browseFile() {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files", "xml");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                browseField.setText(selectedFile.getAbsolutePath());
            }
        }

        private void startUpload() {
            String xmlFilePath = browseField.getText();
            String delayStr = delayField.getText();
            String fileName = fileNameField.getText();
            String filePath = filePathField.getText();

            if (xmlFilePath.isEmpty() || delayStr.isEmpty() || fileName.isEmpty() || filePath.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            int delay = Integer.parseInt(delayStr) * 1000; // Convert seconds to milliseconds

            try {
                File xmlFile = new File(xmlFilePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();

                NodeList recordList = doc.getElementsByTagName("record");
                int totalRecords = recordList.getLength();
                int progress = 0;

                progressBar.setMaximum(totalRecords);
                progressBar.setValue(0);

                File txtFile = new File(filePath + File.separator + fileName + ".txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile));

                for (int i = 0; i < totalRecords; i++) {
                    Node recordNode = recordList.item(i);

                    if (recordNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element recordElement = (Element) recordNode;
                        String record = recordElement.getTextContent();
                        writer.write(record);
                        writer.newLine();
                        writer.flush();

                        progress++;
                        progressBar.setValue(progress);

                        Thread.sleep(delay);
                    }
                }

                writer.close();

                JOptionPane.showMessageDialog(this, "Upload completed successfully!");
                Desktop.getDesktop().open(txtFile);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void resetFields() {
            browseField.setText("");
            delayField.setText("");
            fileNameField.setText("");
            filePathField.setText("");
            progressBar.setValue(0);
        }

    }
}



public class LoginValidationAndFileUploader {
    public static void main(String[] args) {
        LoginPanel lp = new LoginPanel();
    }

}

