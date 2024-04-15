package LoginValidationExercise4;

import javax.swing.*;
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
                        JOptionPane.showMessageDialog(this,"Login Successful","Success",JOptionPane.PLAIN_MESSAGE);
                        return;
                    } else if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(this,"UserID field can not be left blank","Warning",JOptionPane.WARNING_MESSAGE);
                    }else if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(this,"Password field can not be left blank","Warning",JOptionPane.WARNING_MESSAGE);
                    } else if (username.equals(userId) && !password.equals(pwd)) {
                        JOptionPane.showMessageDialog(this,"Wrong Password","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(this,"User does not exist","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPanel();
            }
        });
    }
}




public class LoginValidation {

    public static void main(String[] args) {

        LoginPanel lp = new LoginPanel();
    }
}
