package com.lab6.compulsory6;

import com.lab6.compulsory6.DAO.ActorDAO;
import com.lab6.compulsory6.DAO.MovieDAO;
import com.lab6.compulsory6.databases.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = DataBase.getConnection();
            if (con != null) {
                System.out.println("Connected successfully");
            } else {
                System.out.println("Connection failed");
                return;
            }


            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DELETE FROM movie_actors");
                stmt.executeUpdate("DELETE FROM movies");
                stmt.executeUpdate("DELETE FROM actors");
                stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='actors'");
                stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='movies'");
            }


            ActorDAO actorDao = new ActorDAO();
            MovieDAO movieDao = new MovieDAO();


            actorDao.create("Chuck Norris");
            int movieId = movieDao.create("Walker, Texas Ranger");


            Integer chuckId = actorDao.findByName("Chuck Norris");
            if (chuckId != null) {
                movieDao.addActor(movieId, chuckId);
            }


            System.out.println("Actor ID Chuck Norris: " + chuckId);
            System.out.println("Movie ID Walker, Texas Ranger: " + movieId);


            System.out.println("Actor list:");
            try (ResultSet rs = con.createStatement().executeQuery("SELECT * FROM actors")) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
                }
            }


            System.out.println("Movie list:");
            try (ResultSet rs = con.createStatement().executeQuery("SELECT * FROM movies")) {
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Title: " + rs.getString("title"));
                }
            }


            System.out.println("movie_actors:");
            try (ResultSet rs = con.createStatement().executeQuery("SELECT * FROM movie_actors")) {
                while (rs.next()) {
                    System.out.println("Movie ID: " + rs.getInt("movie_id") + ", Actor ID: " + rs.getInt("actor_id"));
                }
            }


            DataBase.closeConnection();
            System.out.println("Gata! Merge");

        } catch (SQLException e) {
            System.err.println("Eroare JDBC: " + e.getMessage());
        }
    }
}