package daowebapi.models;

/**
 * Created by tmizzle2005 on 11/12/16.
 */
public class Project {

    private String title = "";
    private String tech_used = "";
    private String link = "";
    private String image_url = "";


    public Project(String title, String tech_used, String link, String image_url) {
        this.title = title;
        this.tech_used = tech_used;
        this.link = link;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getTech_used() {
        return tech_used;
    }

    public String getLink() {
        return link;
    }

    public String getImage_url() {
        return image_url;
    }

}
