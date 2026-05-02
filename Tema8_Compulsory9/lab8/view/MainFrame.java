package com.compulsory8.lab8.view;

import com.compulsory8.lab8.controller.MazeController;
import javafx.scene.layout.BorderPane;

public class MainFrame extends BorderPane {

    private final ConfigPanel configPanel;
    private final ControlPanel controlPanel;
    private final MazeCanvas canvas;
    private final MazeController controller;
    public MainFrame() {
        canvas = new MazeCanvas();
        configPanel = new ConfigPanel();
        controlPanel = new ControlPanel();
        controller = new MazeController(canvas);
        setTop(configPanel);
        setCenter(canvas);
        setBottom(controlPanel);

        configPanel.setController(controller);
        controlPanel.setController(controller);
    }
}