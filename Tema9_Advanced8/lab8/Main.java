package com.compulsory8.lab8;

import com.compulsory8.lab8.controller.MazeController;
import com.compulsory8.lab8.view.MainFrame;
import com.compulsory8.lab8.view.MazeCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import com.compulsory8.lab8.model.*;
import com.compulsory8.lab8.game.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {

        MainFrame root = new MainFrame();
        Scene scene = new Scene(root, 800, 800);
        MazeCanvas canvas = root.getCanvas();
        MazeController controller = new MazeController(canvas);
        root.setController(controller);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (controller == null) return;

            switch (e.getCode()) {
                case UP -> controller.speedUpAll();
                case DOWN -> controller.slowDownAll();
                case P -> controller.pauseAll();
                case R -> controller.resumeAll();

                // bunny
                case B -> controller.speedUpBunny();
                case V -> controller.slowDownBunny();
                case N -> controller.pauseBunny();
                case M -> controller.resumeBunny();

                // robot 0
                case DIGIT1 -> controller.speedUpRobot(0);
                case Q -> controller.slowDownRobot(0);
                case A -> controller.pauseRobot(0);
                case Z -> controller.resumeRobot(0);

                // robot 1
                case DIGIT2 -> controller.speedUpRobot(1);
                case W -> controller.slowDownRobot(1);
                case S -> controller.pauseRobot(1);
                case X -> controller.resumeRobot(1);

                // robot 2
                case DIGIT3 -> controller.speedUpRobot(2);
                case E -> controller.slowDownRobot(2);
                case D -> controller.pauseRobot(2);
                case C -> controller.resumeRobot(2);
            }
        });
        stage.setScene(scene);
        stage.setTitle("Labirintul nebun");
        stage.show();
        root.requestFocus();
    }
}
