package dto;

/**
 * Created by laonen on 24.01.2017.
 */
public class RegistrationDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String country;
    private String town;
    private String post_index;
    private String street;
    private String numberHouse;
    private int numberFlat;

    public RegistrationDto(String name, String surname, String email,
                           String password, String phone, String country, String town,
                           String post_index, String street, String numberHouse, int numberFlat) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.country = country;
        this.town = town;
        this.post_index = post_index;
        this.street = street;
        this.numberHouse = numberHouse;
        this.numberFlat = numberFlat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
