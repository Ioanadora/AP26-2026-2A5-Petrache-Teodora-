package com.lab6.compulsory6.databases;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTables {
    public static void create() throws Exception {
        Connection con = DataBase.getConnection();
        Statement stmt = con.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS actors (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");

        stmt.execute("CREATE TABLE IF NOT EXISTS movies (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)");

        stmt.execute("CREATE TABLE IF NOT EXISTS movie_actors (movie_id INTEGER, actor_id INTEGER, PRIMARY KEY (movie_id, actor_id))");
    }
}