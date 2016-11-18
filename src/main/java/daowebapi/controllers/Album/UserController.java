package daowebapi.controllers.Album;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import daowebapi.models.Image;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import daowebapi.models.User;
import java.util.ArrayList;

@RestController
public class UserController {

    private User current_user = new User();

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/user")
    public User user() {
        return current_user;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/user/images")
    public ArrayList<Image> images() {
        return current_user.getImages();
    }
}