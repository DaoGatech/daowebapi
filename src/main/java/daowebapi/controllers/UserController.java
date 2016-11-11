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

        try {
            databaseController db = new databaseController();
            db.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new User();
    }
}