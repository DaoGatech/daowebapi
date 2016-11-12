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

    User current_user = new User();

    @RequestMapping("/user")
    public User user() {
        return current_user;
    }
}