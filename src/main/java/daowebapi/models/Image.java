package daowebapi.models;

/**
 * Created by tmizzle2005 on 10/31/16.
 */

public class Image {
    private String url = "";
    private String location = "";

    public Image(String url) {
        this.url = url;
    }

    public Image(String url, String location) {
        this.url = url;
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

}
