package daowebapi.controllers;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import daowebapi.models.User;

@RestController
public class UserController {

    @RequestMapping("/user")
    public User user() {
        return new User();
    }
}