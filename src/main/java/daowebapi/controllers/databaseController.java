package daowebapi.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tmizzle2005 on 11/10/16.
 */
public class databaseController {

    public Connection getConnection() throws Exception {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        return DriverManager.getConnection(dbUrl, username, password);
    }
}
