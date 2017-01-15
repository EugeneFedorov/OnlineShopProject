package entity;

/**
 * Created by laonen on 15.01.2017.
 */
public class Home {
    private long id;
    private String street;
    private String numberHouse;
    private int numberFlat;

    public Home() {
    }

    public Home(String street, String numberHouse, int numberFlat) {
        this.street = street;
        this.numberHouse = numberHouse;
        this.numberFlat = numberFlat;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberHouse() {
        return numberHouse;
    }

    public void setNumberHouse(String numberHouse) {
        this.numberHouse = numberHouse;
    }

    public int getNumberFlat() {
        return numberFlat;
    }

    public void setNumberFlat(int numberFlat) {
        this.numberFlat = numberFlat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
