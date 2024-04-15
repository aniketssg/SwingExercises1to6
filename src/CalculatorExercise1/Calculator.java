package CalculatorExercise1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private JButton b1;
    private JButton b0;
    private JButton bsub;
    private JButton b6;


    double f1;
    double f2;
    double res;
    String operations;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Calculator window = new Calculator();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Calculator() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 468, 569);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(10, 22, 400, 79);
        textField.setFont(new Font("Tahoma", Font.BOLD, 50));
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        /////////////////////////row 1/////////////////////////////

        JButton b7 = new JButton("7");
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b7.getText();
                textField.setText(val);

            }
        });
        b7.setFont(new Font("Tahoma", Font.BOLD, 50));
        b7.setBounds(10, 105, 100, 100);
        frame.getContentPane().add(b7);

        JButton b8 = new JButton("8");
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b8.getText();
                textField.setText(val);

            }
        });
        b8.setFont(new Font("Tahoma", Font.BOLD, 50));
        b8.setBounds(110, 105, 100, 100);
        frame.getContentPane().add(b8);

        JButton b9 = new JButton("9");
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b9.getText();
                textField.setText(val);

            }
        });
        b9.setFont(new Font("Tahoma", Font.BOLD, 50));
        b9.setBounds(210, 105, 100, 100);
        frame.getContentPane().add(b9);

        JButton badd = new JButton("+");
        badd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                f1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operations = "+";

            }
        });
        badd.setFont(new Font("Tahoma", Font.BOLD, 50));
        badd.setBounds(310, 105, 100, 100);
        frame.getContentPane().add(badd);

        //////////////row 2///////////////////

        JButton b4 = new JButton("4");
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b4.getText();
                textField.setText(val);

            }
        });
        b4.setFont(new Font("Tahoma", Font.BOLD, 50));
        b4.setBounds(10, 205, 100, 100);
        frame.getContentPane().add(b4);

        JButton b5 = new JButton("5");
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b5.getText();
                textField.setText(val);

            }
        });
        b5.setFont(new Font("Tahoma", Font.BOLD, 50));
        b5.setBounds(110, 205, 100, 100);
        frame.getContentPane().add(b5);


        b6 = new JButton("6");
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b6.getText();
                textField.setText(val);

            }
        });
        b6.setFont(new Font("Tahoma", Font.BOLD, 50));
        b6.setBounds(210, 205, 100, 100);
        frame.getContentPane().add(b6);


        bsub = new JButton("-");
        bsub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                f1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operations = "-";

            }
        });
        bsub.setFont(new Font("Tahoma", Font.BOLD, 50));
        bsub.setBounds(310, 205, 100, 100);
        frame.getContentPane().add(bsub);

        ///////////////////////row 3///////////////


        b1 = new JButton("1");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b1.getText();
                textField.setText(val);

            }
        });
        b1.setFont(new Font("Tahoma", Font.BOLD, 50));
        b1.setBounds(10, 306, 100, 100);
        frame.getContentPane().add(b1);

        JButton b2 = new JButton("2");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b2.getText();
                textField.setText(val);

            }
        });
        b2.setFont(new Font("Tahoma", Font.BOLD, 50));
        b2.setBounds(110, 306, 100, 100);
        frame.getContentPane().add(b2);

        JButton b3 = new JButton("3");
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b3.getText();
                textField.setText(val);

            }
        });
        b3.setFont(new Font("Tahoma", Font.BOLD, 50));
        b3.setBounds(210, 306, 100, 100);
        frame.getContentPane().add(b3);

        JButton bmul = new JButton("*");
        bmul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                f1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operations = "*";

            }
        });
        bmul.setFont(new Font("Tahoma", Font.BOLD, 50));
        bmul.setBounds(310, 306, 100, 100);
        frame.getContentPane().add(bmul);

        ////////////////////////row 4//////////////////

        JButton bclear = new JButton("C");
        bclear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                textField.setText(null);

            }
        });
        bclear.setFont(new Font("Tahoma", Font.BOLD, 50));
        bclear.setBounds(10, 407, 100, 100);
        frame.getContentPane().add(bclear);

        b0 = new JButton("0");
        b0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String val = textField.getText() + b0.getText();
                textField.setText(val);

            }
        });
        b0.setFont(new Font("Tahoma", Font.BOLD, 50));
        b0.setBounds(110, 407, 100, 100);
        frame.getContentPane().add(b0);

        JButton bdiv = new JButton("/");
        bdiv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                f1 = Double.parseDouble(textField.getText());
                textField.setText("");
                operations = "/";

            }
        });
        bdiv.setFont(new Font("Tahoma", Font.BOLD, 50));
        bdiv.setBounds(310, 407, 100, 100);
        frame.getContentPane().add(bdiv);

        JButton beq = new JButton("=");
        beq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ans;
                f2 = Double.parseDouble(textField.getText());

                if(operations.equals("+")) {
                    res = f1 + f2;
                    ans = String.format("%.2f", res);
                    textField.setText(ans);
                }
                else if(operations.equals("-")) {
                    res = f1 - f2;
                    ans = String.format("%.2f",res);
                    textField.setText(ans);
                }
                else if (operations.equals("*")) {
                    res = f1 * f2;
                    ans = String.format("%.2f",res);
                    textField.setText(ans);
                }
                else {
                    res = f1 / f2;
                    ans = String.format("%.2f", res);
                    textField.setText(ans);
                }

            }
        });
        beq.setFont(new Font("Tahoma", Font.BOLD, 50));
        beq.setBounds(210, 407, 100, 100);
        frame.getContentPane().add(beq);




    }

}


