package daowebapi.controllers;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daowebapi.models.User;

import java.sql.Connection;

@RestController
public class UserController {

    @RequestMapping("/user")
    public User user() {
        databaseController db = new databaseController();
        try {
            Connection conn = db.getConnection();
            System.out.println(conn);
        } catch (Exception e) {

        }
        return new User();
    }
}