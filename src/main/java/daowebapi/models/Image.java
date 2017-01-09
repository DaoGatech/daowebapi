package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

public class Image {
    private String url = "";
    private String location = "";
    private int id = 0;
    private String description = "";
    private String dateposted = "";

    public Image(String url) {
        this.url = url;
    }

    public Image(int id, String url, String location, String description, String dateposted) {
        this.id = id;
        this.url = url;
        this.location = location;
        this.description = description;
        this.dateposted = dateposted;
    }

    public int getId() { return id; }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {return description; }

    public String getdatePosted() {return dateposted; }

}
