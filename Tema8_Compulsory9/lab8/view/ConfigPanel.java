package com.compulsory8.lab8.view;

import com.compulsory8.lab8.controller.MazeController;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
public class ConfigPanel extends HBox {
    private final TextField rowField = new TextField("10");
    private final TextField colField = new TextField("10");
    private MazeController controller;
    public ConfigPanel() {
        this.setSpacing(10);
        Button button = new Button("Draw grid");

        this.getChildren().addAll(
                new Label("rows:"), rowField,
                new Label("cols:"), colField,
                button
        );

        button.setOnAction(event -> {
            if (controller != null) {
                try {
                    int parseInt = Integer.parseInt(rowField.getText());
                    int anInt = Integer.parseInt(colField.getText());
                    controller.createMaze(parseInt, anInt);
                } catch (NumberFormatException ex) {
                    System.out.println("enter valid numbers");
                }
            }
        });
    }
    public void setController(MazeController controller) {
        this.controller = controller;
    }
}