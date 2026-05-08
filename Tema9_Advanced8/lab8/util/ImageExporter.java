package com.compulsory8.lab8.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.io.File;

public class ImageExporter {

    public static void export(Canvas canvas, Window ownerWindow) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");



        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files", "*.png"));
        fileChooser.setInitialFileName("maze.png");

        File file = fileChooser.showSaveDialog(ownerWindow);
        if (file != null) {
            WritableImage image = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }
    }
}