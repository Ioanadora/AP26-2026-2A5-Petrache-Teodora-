package com.lab6.tema6.dao;

import com.lab6.tema6.config.DataSourceManager;
import com.lab6.tema6.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public List<Movie> findAllFromView() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie_view";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setTitle(resultSet.getString("title"));
                movie.setDuration(resultSet.getInt("duration"));
                movie.setScore(resultSet.getDouble("score"));

                movies.add(movie);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return movies;
    }
}