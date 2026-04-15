package com.lab6.tema6.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceManager {

    private static HikariDataSource dataSource;

    private DataSourceManager() {}

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:sqlite:movies.db");
            config.setDriverClassName("org.sqlite.JDBC");
            config.setMaximumPoolSize(10);

            dataSource = new HikariDataSource(config);
        }
        return dataSource;
    }
}