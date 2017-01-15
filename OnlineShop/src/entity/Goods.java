package entity;

/**
 * Created by laonen on 15.01.2017.
 */
public class Goods {
    private long id;
    private String name;
    private String description;
    private double price;
    private double remainingAmount;

    public Goods() {
    }

    public Goods(String name, String description, double price, double remainingAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID_GOOD " + id +
                " NAME_GOOD " + name +
                " DESCRIPTION " + description +
                " PRICE " + price +
                " REMAINING_AMOUNT " + remainingAmount;
    }
}
