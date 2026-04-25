package com.compulsory8.lab8.view;

import com.compulsory8.lab8.controller.MazeController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {

    private MazeController controller;

    public ControlPanel() {
        setSpacing(10);

        //Button create = new Button("Create");
        Button solve = new Button("Validate");
        Button save = new Button("Save");
        Button load = new Button("Load");
        Button export = new Button("Export PNG");
        Button reset = new Button("Reset");
        Button exit = new Button("Exit");
        Button startGame = new Button("Start Game");
        startGame.setOnAction(e -> controller.startGame());

        //create.setOnAction(e -> controller.generate());
        solve.setOnAction(e -> controller.validate());
        save.setOnAction(e -> controller.save());
        load.setOnAction(e -> controller.load());
        export.setOnAction(e -> controller.export());
        reset.setOnAction(e -> controller.reset());
        exit.setOnAction(e -> System.exit(0));



        getChildren().addAll( reset, exit,solve,save,load,export,startGame);
    }
    public void setController(MazeController controller) {
        this.controller = controller;
    }
}