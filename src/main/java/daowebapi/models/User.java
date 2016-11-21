package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import daowebapi.controllers.databaseController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class User {

    private String username = "";
    private String alias = "";
    private Connection conn = null;
    private Statement stmt = null;

    public User() {
        try {
            databaseController db = new databaseController();
            conn = db.getConnection();
            stmt = conn.createStatement();
            ResultSet users = stmt.executeQuery("SELECT * FROM user");
            while(users.next()){
                this.username  = users.getString("username");
                this.alias = users.getString("alias");
            }
            users.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) conn.close();
            }catch(Exception se){
            }
            try{
                if(conn!=null) conn.close();
            }catch(Exception se){
                se.printStackTrace();
            }
        }

    }

    public String getUsername() {
        return username;
    }

    public String getAlias() {
        return alias;
    }

    public ArrayList<Image> getImages() {
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            databaseController db = new databaseController();
            conn = db.getConnection();
            stmt = conn.createStatement();
            ResultSet img = stmt.executeQuery("SELECT * FROM images");
            while(img.next()){
                images.add(new Image(img.getString("url"),img.getString("location")));
            }
            img.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) conn.close();
            }catch(Exception se){
            }
            try{
                if(conn!=null) conn.close();
            }catch(Exception se){
                se.printStackTrace();
            }
        }
        return images;
    }
}