package com.compulsory10.lab10server;

import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {

    private final Socket socket;
    private final GameServer server;
    private PrintWriter out;
    private BufferedReader in;
    private String playerName;
    private int currentQuestionIndex = 0;
    private long questionStartTime;
    private static final long TIME_LIMIT = 10000;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Connected to quiz server");

            String request;

            while ((request = in.readLine()) != null) {
                request = request.trim();
                if (request.isEmpty()) continue;

                System.out.println("Received: " + request);
                String[] tokens = request.split(" ", 2);
                String command = tokens[0].toLowerCase();

                switch (command) {
                    case "join":
                        if (tokens.length < 2) {
                            out.println("Usage: join <name>");
                            break;
                        }
                        playerName = tokens[1];
                        server.addPlayer(playerName);
                        out.println("Welcome " + playerName);
                        break;

                    case "question":
                        Question question = server.getQuestion(currentQuestionIndex);
                        if (question == null) {
                            out.println("No more questions");
                            out.println(server.getLeaderboard());
                            out.println(server.determineWinner());
                            break;
                        }
                        questionStartTime = System.currentTimeMillis();
                        out.println("QUESTION: " + question.getText());
                        break;

                    case "answer":
                        if (playerName == null) {
                            out.println("Join first");
                            break;
                        }
                        if (tokens.length < 2) {
                            out.println(" answer <text>");
                            break;
                        }
                        processAnswer(tokens[1]);
                        break;

                    case "score":
                        if (playerName == null) {
                            out.println("Join first");
                            break;
                        }
                        out.println("\n YOUR SCORE ");
                        out.println(server.getPlayer(playerName));

                        break;

                    case "leaderboard":
                        out.println("\n" + server.getLeaderboard());
                        break;

                    case "stop":
                        out.println("Server stopping");
                        server.stop();
                        System.exit(0);
                        return;

                    case "exit":
                        out.println("Goodbye!");
                        return;

                    default:
                        if (playerName != null && server.getQuestion(currentQuestionIndex) != null) {
                            processAnswer(request);
                        } else {
                            out.println("Unknown command: " + command);
                        }
                        break;
                }
            }
        } catch (IOException exception) {
            System.out.println("Client disconnected");
        } finally {
            try {
                socket.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void processAnswer(String answerText) {
        Question current = server.getQuestion(currentQuestionIndex);
        if (current == null) {
            out.println("No question. Type 'question' to start if available.");
            return;
        }

        long responseTimeMs = System.currentTimeMillis() - questionStartTime;
        double responseTimeSec = responseTimeMs / 1000.0;

        Player player = server.getPlayer(playerName);
        player.addResponseTime(responseTimeMs);

        if (responseTimeMs > TIME_LIMIT) {
            out.println("Time exceeded! (" + responseTimeSec + " s)");
        } else if (answerText.trim().equalsIgnoreCase(current.getAnswer())) {
            player.addPoint();
            out.println("Correct! Response time: " + responseTimeSec + " s");
        } else {
            out.println("Wrong answer (" + responseTimeSec + " s)");
        }


        currentQuestionIndex++;
        Question nextQ = server.getQuestion(currentQuestionIndex);
        if (nextQ != null) {
            questionStartTime = System.currentTimeMillis();
            out.println("NEXT QUESTION: " + nextQ.getText());
        } else {
            out.println("Quiz finished!");

            server.savePlayerResult(playerName, player.getScore(), player.getTotalResponseTime());
            out.println(server.getLeaderboard());
            out.println(server.determineWinner());
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }
}