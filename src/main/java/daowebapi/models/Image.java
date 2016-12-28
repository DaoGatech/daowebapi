package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

public class Image {
    private String url = "";
    private String location = "";
    private int id = 0;
    private String description = "";

    public Image(String url) {
        this.url = url;
    }

    public Image(int id, String url, String location, String description) {
        this.id = id;
        this.url = url;
        this.location = location;
        this.description = description;
    }

    public int getId() { return id; }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

}
