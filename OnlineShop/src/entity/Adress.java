package entity;

/**
 * Created by laonen on 15.01.2017.
 */
public class Adress {
    private long id;
    private String country;
    private String town;
    private String post_index;
    private Home home;

    public Adress() {
    }

    public Adress(String country, String town, String index, Home home) {
        this.country = country;
        this.town = town;
        this.post_index = index;
        this.home = home;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPost_index() {
        return post_index;
    }

    public void setPost_index(String post_index) {
        this.post_index = post_index;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}

