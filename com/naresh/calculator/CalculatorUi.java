package com.naresh.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUi extends JFrame {
    private JTextField numField1;
    private JTextField numField2;
    private JTextField numField3;
    private JComboBox<String> operationComboBox;
    private JLabel resultLabel;

    public CalculatorUi() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Components
        numField1 = new JTextField();
        numField2 = new JTextField();
        numField3 = new JTextField();
        operationComboBox = new JComboBox<>(new String[]{"add", "sub", "div", "mul","evenOdd"});
        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();

        // Add components to the frame
        add(new JLabel("Enter First Number:"));
        add(numField1);
        add(new JLabel("Enter Second Number:"));
        add(numField2);
        add(new JLabel("Enter number For check Even or odd"));
        add(numField3);
        add(new JLabel("Select Operation:"));
        add(operationComboBox);
        add(new JLabel()); // Empty label for spacing
        add(calculateButton);
        add(new JLabel("Result:"));
        add(resultLabel);

        // Button click listener
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
    }

    private void calculate() {
        try {
            // Get user input
            int n1 = Integer.parseInt(numField1.getText());
            int n2 = Integer.parseInt(numField2.getText());
            int n3 = Integer.parseInt(numField3.getText());
            String operation = (String) operationComboBox.getSelectedItem();

            // Perform calculation using Calculator class
            int result = 0;
            switch (operation) {
                case "add":
                    result = n1 + n2;
                    break;
                case "sub":
                    result = n1 - n2;
                    break;
                case "div":
                    if (n2 == 0) {
                        resultLabel.setText("Error: Cannot divide by zero");
                        return;
                    }
                    result = n1 / n2;
                    break;
                case "mul":
                    result = n1 * n2;
                    break;
                case "evenOdd":
                    if(n3%2==0){
                    resultLabel.setText("its Even");
                }else{
                    resultLabel.setText("its odd");
                return;
                }
               break;
                default:
                    resultLabel.setText("Invalid operation");
                    return;
            }

            // Display the result
            resultLabel.setText(Integer.toString(result));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error: Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorUi calculatorUi = new CalculatorUi();
            calculatorUi.pack();
            calculatorUi.setLocationRelativeTo(null);
            calculatorUi.setVisible(true);
        });
    }
}