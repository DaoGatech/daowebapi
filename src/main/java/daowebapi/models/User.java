package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import java.util.ArrayList;

public class User {

    private String username = "";
    private String alias = "";
    private ArrayList<Image> images = new ArrayList<Image>();

    public User() {
        this.username = "daostephen";
        this.alias = "tmizzle2005";
    }

    public String getUsername() {
        return System.getenv("CLEARDB_DATABASE_URL");
    }

    public String getAlias() {
        return alias;
    }

    public ArrayList<Image> getImages() {
        return images;
    }
}