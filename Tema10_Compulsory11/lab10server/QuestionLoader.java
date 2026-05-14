package com.compulsory10.lab10server;

import java.io.*;
import java.util.*;

public class QuestionLoader {

    public static List<Question> loadQuestions(String fileName) {
        List<Question> questions = new ArrayList<>();
        File file = new File(System.getProperty("user.dir"), fileName);
        System.out.println("Loading questions from: " + file.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    questions.add(new Question(parts[0].trim(), parts[1].trim()));
                }
            }
            System.out.println("loaded questions: " + questions.size());
        } catch (Exception e) {
            System.err.println("Error questions: " + e.getMessage());
            e.printStackTrace();
        }

        return questions;
    }
}