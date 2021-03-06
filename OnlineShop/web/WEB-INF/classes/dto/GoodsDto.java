package dto;

/**
 * Created by laonen on 26.01.2017.
 */
public class GoodsDto {
    private long id;
    private String name;
    private String description;
    private double price;
    private double remainingAmount;

    public GoodsDto(String name, String description, double price, double remainingAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
    }

    public GoodsDto(long id, String name, String description, double price, double remainingAmount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.remainingAmount = remainingAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
