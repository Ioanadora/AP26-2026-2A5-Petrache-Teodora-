package com.compulsory10.lab10server;

import com.compulsory10.lab10server.model.*;
import com.compulsory10.lab10server.repository.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameServer {

    private boolean running = true;
    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private final List<ClientThread> clients = new ArrayList<>();
    private final Map<String, Player> players = new HashMap<>();
    private final List<Question> questions;
    private final PlayerRepository playerRepository = new PlayerRepository();
    private final QuestionRepository questionRepository = new QuestionRepository();
    private final GameRepository gameRepository = new GameRepository();
    private final ResultRepository resultRepository = new ResultRepository();
    private GameEntity currentGame;

    public GameServer() {
        System.out.println(new java.io.File(".").getAbsolutePath());


        List<QuestionEntity> dbQuestions = questionRepository.findAll();
        if (dbQuestions.isEmpty()) {

            questions = QuestionLoader.loadQuestions("question.txt");
            for (Question question : questions) {
                questionRepository.create(new QuestionEntity(question.getText(), question.getAnswer()));
            }
        } else {
            questions = dbQuestions.stream()
                    .map(question -> new Question(question.getText(), question.getAnswer()))
                    .toList();
        }
    }

    public void start(int port) {

        try {

            serverSocket = new ServerSocket(port);
            threadPool = Executors.newFixedThreadPool(20);
            System.out.println("Server started on port " + port);
            while (running) {

                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket, this);

                synchronized (clients) {clients.add(clientThread);}
                threadPool.execute(clientThread);
            }
        } catch (IOException exception) {
            if (running) {
                exception.printStackTrace();
            }
        }}

    public synchronized void addPlayer(String name) {

        players.putIfAbsent(name, new Player(name));


        if (playerRepository.findByName(name) == null) {
            playerRepository.create(new PlayerEntity(name));
        }
    }

    public synchronized Player getPlayer(String name) {
        return players.get(name);
    }


    public synchronized void savePlayerResult(String playerName, int score, long totalTime) {
        PlayerEntity player = playerRepository.findByName(playerName);
        if (player != null) {
            player.setScore(player.getScore() + score);
            player.setTotalResponseTime(player.getTotalResponseTime() + totalTime);
            playerRepository.update(player);


            ResultEntity result = new ResultEntity(player, currentGame, score, totalTime);
            resultRepository.create(result);
        }
    }

    public synchronized Question getQuestion(int index) {
        if (index < 0 || index >= questions.size()) {
            return null;
        }
        return questions.get(index);
    }

    public synchronized int getQuestionCount() {
        return questions.size();
    }

    public synchronized void broadcast(String message) {

        for (ClientThread client : clients) {
            client.sendMessage(message);
        }
    }

    public synchronized String getLeaderboard() {

        StringBuilder append = new StringBuilder();

        List<Player> sortedPlayers =
                new ArrayList<>(players.values());

        sortedPlayers.sort((player, player1) -> {

            if (player1.getScore() != player.getScore()) {
                return player1.getScore() - player.getScore();
            }

            return Long.compare(
                    player.getTotalResponseTime(),
                    player1.getTotalResponseTime()
            );
        });

        append.append("= LEADERBOARD =\n");

        for (Player player : sortedPlayers) {
            append.append(player).append("\n");
        }

        return append.toString();
    }

    public synchronized String determineWinner() {

        if (players.isEmpty()) {
            return "No players";
        }

        List<Player> sortedPlayers =
                new ArrayList<>(players.values());

        sortedPlayers.sort((player, player1) -> {

            if (player1.getScore() != player.getScore()) {
                return player1.getScore() - player.getScore();
            }

            return Long.compare(
                    player.getTotalResponseTime(),
                    player1.getTotalResponseTime()
            );
        });

        Player winner = sortedPlayers.get(0);

        return "Winner is: " + winner.getName() + " with score " + winner.getScore() + " and time " + winner.getTotalResponseTime() + "ms";
    }

    public void stop() {

        running = false;

        try {
            serverSocket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        threadPool.shutdown();

        try {

            if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }

        } catch (InterruptedException exception) {

            threadPool.shutdownNow();
        }

        System.out.println("Server stopped ");
    }
}