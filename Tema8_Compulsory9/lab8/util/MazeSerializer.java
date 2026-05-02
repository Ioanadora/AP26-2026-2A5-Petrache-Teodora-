package com.compulsory8.lab8.util;

import com.compulsory8.lab8.model.Maze;
import java.io.*;
import javax.swing.JFileChooser;
import java.io.*;

public class MazeSerializer {

    public static void save(Maze maze) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);


        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(maze);
            outputStream.close();
        }}

    public static Maze load() throws IOException, ClassNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            Maze maze = (Maze) inputStream.readObject();
            inputStream.close();
            return maze;
        }return null;
    }}