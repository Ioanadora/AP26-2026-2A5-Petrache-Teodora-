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
        Button genBtn = new Button("Generate (DFS)");
        Slider speedSlider = new Slider(1, 200, 50);
        speedSlider.setShowTickLabels(true);
        speedSlider.setPrefWidth(100);

        this.getChildren().addAll(
                new Label("rows:"), rowField,
                new Label("cols:"), colField,
                button,
                new Label("Speed:"), speedSlider,
                genBtn
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

        genBtn.setOnAction(event -> {
            if (controller != null) {
                controller.setGenDelay((int) speedSlider.getValue());
                controller.generateAnimated();
            }
        });
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (controller != null) {
                controller.setGenDelay(newVal.intValue());
            }
        });
    }


    public void setController(MazeController controller) {
        this.controller = controller;
    }
}