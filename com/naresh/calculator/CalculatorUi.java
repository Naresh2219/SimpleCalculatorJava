package com.naresh.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUi extends JFrame {
    private JTextField numField1;
    private JTextField numField2;
    private JComboBox<String> operationComboBox;
    private JLabel resultLabel;

    public CalculatorUi() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 6, 4));

        // Components
        numField1 = new JTextField();
        numField2 = new JTextField();
        operationComboBox = new JComboBox<>(new String[]{"Addition", "Subtraction", "Division", "Multiplication", "Even/Odd Check"});
        JButton calculateButton = new JButton("Calculate");
        resultLabel = new JLabel();

        // Add components to the frame
        add(new JLabel("Enter First Number:"));
        add(numField1);
        add(new JLabel("Enter Second Number:"));
        add(numField2);
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
            String operation = (String) operationComboBox.getSelectedItem();

            // Perform calculation using Calculator class
            int result = 0;
            switch (operation) {
                case "Addition":
                    result = n1 + n2;
                    break;
                case "Subtraction":
                    result = n1 - n2;
                    break;
                case "Division":
                    if (n2 == 0) {
                        JOptionPane.showMessageDialog(this, "Error: Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    result = n1 / n2;
                    break;
                case "Multiplication":
                    result = n1 * n2;
                    break;
                case "Even/Odd Check":
                    resultLabel.setText("Number: " + (n1 % 2 == 0 ? "Even" : "Odd"));
                    return;
                default:
                    resultLabel.setText("Invalid operation");
                    return;
            }
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
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
