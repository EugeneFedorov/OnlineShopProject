package entity;

/**
 * Created by laonen on 22.01.2017.
 */
public class OrderLine {
    private Goods idByGoods;
    private Order idByOrder;
    private double quantity;

    public Goods getIdByGoods() {
        return idByGoods;
    }

    public void setIdByGoods(Goods idByGoods) {
        this.idByGoods = idByGoods;
    }

    public Order getIdByOrder() {
        return idByOrder;
    }

    public void setIdByOrder(Order idByOrder) {
        this.idByOrder = idByOrder;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
