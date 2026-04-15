package com.lab6.compulsory6.DAO;
import com.lab6.compulsory6.databases.DataBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Actor {
    private Integer id;
    private String name;
}

public class ActorDAO {

    public void create(String name) throws SQLException {
        Connection con = DataBase.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO actors (name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = DataBase.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT id FROM actors WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next() ? resultSet.getInt("id") : null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = DataBase.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT name FROM actors WHERE id = ?")) {
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next() ? resultSet.getString("name") : null;
        }
    }
}