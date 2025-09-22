package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://beadz62ifztv88glb5uv-postgresql.services.clever-cloud.com:5432/beadz62ifztv88glb5uv";
    private static final String USER = "uh2hptnfb5w81foz9ty1";
    private static final String PASSWORD = "eYDGN8xXp2GiccP1kXr2fmFcZz3g9s";

    public static Connection connect() throws SQLException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

