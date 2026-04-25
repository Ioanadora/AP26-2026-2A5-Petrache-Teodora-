package com.compulsory8.lab8;

import com.compulsory8.lab8.view.MainFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.compulsory8.lab8.model.*;
import com.compulsory8.lab8.game.*;

public class Main extends Application {
    @Override
    public void start(Stage stage)
    {
        MainFrame root=new MainFrame();
        root.setStyle("-fx-background-color: pink;");
        Scene scene=new Scene(root,800,800);
        scene.setFill(Color.HOTPINK);
        stage.setTitle("Labirintul nebun");
        stage.setScene(scene);
        stage.show();

        Maze maze = new Maze(10,10);
        maze.generate();

        Game game = new Game(maze);

        Bunny bunny = new Bunny(game, new Position(0,0));

        Robot r1 = new Robot(game, new Position(5,5));
        Robot r2 = new Robot(game, new Position(2,3));
        Robot r3 = new Robot(game, new Position(7,1));

        bunny.start();
        r1.start();
        r2.start();
        r3.start();
    }
}
