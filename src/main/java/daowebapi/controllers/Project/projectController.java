package daowebapi.controllers.Project;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

import daowebapi.controllers.databaseController;
import daowebapi.models.Image;
import daowebapi.models.Project;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import daowebapi.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@RestController
public class projectController {

    private ArrayList<Project> projects = new ArrayList<Project>();
    private Connection conn;
    private Statement stmt;

    @RequestMapping("/projects")
    public ArrayList<Project> getProjects() {
        helper_get_projects();
        return projects;
    }

    private void helper_get_projects() {
        try {
            databaseController db = new databaseController();
            conn = db.getConnection();
            stmt = conn.createStatement();
            ResultSet proj = stmt.executeQuery("SELECT * FROM projects");
            while(proj.next()){
                projects.add(new Project(proj.getString("title"),proj.getString("tech_used"),proj.getString("link"),
                        proj.getString("image_url")));
            }
            proj.close();
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

}