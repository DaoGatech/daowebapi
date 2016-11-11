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

    public static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("CLEARDB_DATABASE_URL"));
        System.out.println(System.getenv("CLEARDB_DATABASE_URL"));
        System.out.println(dbUri.getUserInfo().split(":")[0]);
        System.out.println(dbUri.getUserInfo().split(":")[1]);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:mysql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
