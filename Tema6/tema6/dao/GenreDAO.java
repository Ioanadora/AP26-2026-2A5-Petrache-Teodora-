package com.lab6.tema6.dao;

import com.lab6.tema6.config.DataSourceManager;
import com.lab6.tema6.model.Genre;

import java.sql.*;

public class GenreDAO {

    public void create(Genre genre) {
        String sql = "INSERT INTO genres(name) VALUES(?)";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, genre.getNameGenre());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Genre findById(int id) {
        String sql = "SELECT * FROM genres WHERE id=?";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt("id"));
                genre.setNameGenre(rs.getString("name"));
                return genre;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public Genre findByName(String name) {
        String sql = "SELECT * FROM genres WHERE name=?";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setNameGenre(resultSet.getString("name"));
                return genre;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }
}