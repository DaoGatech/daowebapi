package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

public class Image {
    private String url = "";
    private String location = "";
    private int id = 0;

    public Image(String url) {
        this.url = url;
    }

    public Image(int id, String url, String location) {
        this.id = id;
        this.url = url;
        this.location = location;
    }

    public int getId() { return id; }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

}
