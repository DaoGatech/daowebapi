package daowebapi.controllers.Album;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import daowebapi.models.Image;
import org.springframework.web.bind.annotation.*;
import daowebapi.models.User;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class UserController {

    private User current_user = new User();

    private HashMap<Integer,Image> map = new HashMap<>();
    @RequestMapping("/user")
    public User user() {
        return current_user;
    }

    @RequestMapping("/user/images")
    public ArrayList<Image> images() {
        ArrayList<Image> imgs = current_user.getImages();
        for(int i = 0; i < imgs.size(); i++) {
            if(!map.containsKey(imgs.get(i).getId())) {
                map.put(imgs.get(i).getId(),imgs.get(i));
            }
        }
        return imgs;
    }


    @RequestMapping(value="/user/images/{id}", method= RequestMethod.GET)
    public Image getImageById(@PathVariable int id) {
        //cache that helps improve speed
        if(map.containsKey(id)) return map.get(id);
        return current_user.getImageById(id);
    }


}