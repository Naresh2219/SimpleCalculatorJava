import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CasioLikeCalculatorUi extends JFrame {
    private JTextField display;
    private JButton[] digitButtons;
    private JButton addButton, subButton, mulButton, divButton, equalsButton, clearButton;

    private StringBuilder currentInput;

    public CasioLikeCalculatorUi() {
        setTitle("Casio-like Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Digit buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 3, 5, 5));
        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = new JButton(Integer.toString(i));
            digitButtons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            digitButtons[i].addActionListener(new DigitButtonListener());
            buttonPanel.add(digitButtons[i]);
        }

        // Operation buttons
        addButton = createOperationButton("+");
        subButton = createOperationButton("-");
        mulButton = createOperationButton("x");
        divButton = createOperationButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        equalsButton.setFont(new Font("Arial", Font.PLAIN, 18));
        clearButton.setFont(new Font("Arial", Font.PLAIN, 18));

        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(digitButtons[0]);
        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Initialize current input
        currentInput = new StringBuilder();
    }

    private JButton createOperationButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(new OperationButtonListener());
        return button;
    }

    private class DigitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            currentInput.append(source.getText());
            display.setText(currentInput.toString());
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            currentInput.append(" ").append(source.getText()).append(" ");
            display.setText(currentInput.toString());
        }
    }

    private void calculate() {
        try {
            String[] parts = currentInput.toString().split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid input");
            }

            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[2]);
            String operator = parts[1];

            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "x":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    result = num1 / num2;
                    break;
            }

            display.setText(Double.toString(result));
            currentInput.setLength(0);
        } catch (Exception ex) {
            display.setText("Error");
            currentInput.setLength(0);
        }
    }

    private void clear() {
        currentInput.setLength(0);
        display.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CasioLikeCalculatorUi calculatorUi = new CasioLikeCalculatorUi();
            calculatorUi.setSize(300, 400);
            calculatorUi.setLocationRelativeTo(null);
            calculatorUi.setVisible(true);
        });
    }
}
