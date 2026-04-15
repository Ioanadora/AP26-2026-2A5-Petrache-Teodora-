package com.lab6.tema6.dao;

import com.lab6.tema6.config.DataSourceManager;
import com.lab6.tema6.model.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

    public void create(Actor actor) {
        String sql = "INSERT INTO actors(name) VALUES(?)";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, actor.getNameActor());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Actor findById(int id) {
        String sql = "SELECT * FROM actors WHERE id=?";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt("id"));
                actor.setNameActor(resultSet.getString("name"));
                return actor;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public List<Actor> findAll() {
        List<Actor> list = new ArrayList<>();
        String sql = "SELECT * FROM actors";

        try (Connection conn = DataSourceManager.getDataSource().getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setId(resultSet.getInt("id"));
                actor.setNameActor(resultSet.getString("name"));
                list.add(actor);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return list;
    }
}