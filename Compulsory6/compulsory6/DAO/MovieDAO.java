package com.lab6.compulsory6.DAO;
import com.lab6.compulsory6.databases.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieDAO {

    public int create(String title) throws SQLException {
        Connection con = DataBase.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO movies (title) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, title);
            pstmt.executeUpdate();
            var resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) return resultSet.getInt(1);
        }
        return -1;
    }

    public void addActor(int movieId, int actorId) throws SQLException {
        Connection con = DataBase.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO movie_actors (movie_id, actor_id) VALUES (?, ?)")) {
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
        }
    }
}