package com.compulsory10.lab10client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket("localhost", 1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in))
        {

            Thread listener = new Thread(new ServerListener(in));
            listener.start();
            System.out.println("Commands: join, question, score, exit,leaderboard, stop");
            String command;

            while (true) {

                command = scanner.nextLine();

                out.println(command);

                if (command.equals("exit") || command.equals("stop")) {
                    break;
                }
            }
               // out.println(command);
                //System.out.println("Server: " + in.readLine());

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}