package com.naresh.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorFX extends Application {
    private TextField numField1;
    private TextField numField2;
    private ComboBox<String> operationComboBox;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // Components
        numField1 = new TextField();
        numField2 = new TextField();
        operationComboBox = new ComboBox<>();
        operationComboBox.getItems().addAll("Addition", "Subtraction", "Division", "Multiplication", "Even/Odd Check", "Text Length");
        Button calculateButton = new Button("Calculate");
        resultLabel = new Label();

        // Add components to the grid
        grid.add(new Label("Enter First Number:"), 0, 0);
        grid.add(numField1, 1, 0);
        grid.add(new Label("Enter Second Number:"), 0, 1);
        grid.add(numField2, 1, 1);
        grid.add(new Label("Select Operation:"), 0, 2);
        grid.add(operationComboBox, 1, 2);
        grid.add(new Label("Result:"), 0, 4);
        grid.add(resultLabel, 1, 4);
        grid.add(calculateButton, 1, 3);

        // Button click handler
        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                calculate();
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void calculate() {
        try {
            // Get user input
            int n1 = Integer.parseInt(numField1.getText());
            int n2 = Integer.parseInt(numField2.getText());
            String operation = operationComboBox.getValue();

            // Perform calculation
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
                        showAlert("Error: Cannot divide by zero");
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
                case "Text Length":
                    resultLabel.setText("Text Length: " + numField1.getText().length());
                    return;
                default:
                    showAlert("Invalid operation");
                    return;
            }
            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException e) {
            showAlert("Error: Invalid input");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
