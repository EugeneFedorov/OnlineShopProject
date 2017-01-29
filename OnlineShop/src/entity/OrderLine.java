package entity;

/**
 * Created by laonen on 22.01.2017.
 */
public class OrderLine {
    private long idByGoods;
    private long idByOrder;
    private double quantity;

    public OrderLine(long idByGoods, long idByOrder, double quantity) {
        this.idByGoods = idByGoods;
        this.idByOrder = idByOrder;
        this.quantity = quantity;
    }

    public long getIdByGoods() {
        return idByGoods;
    }

    public void setIdByGoods(long idByGoods) {
        this.idByGoods = idByGoods;
    }

    public long getIdByOrder() {
        return idByOrder;
    }

    public void setIdByOrder(long idByOrder) {
        this.idByOrder = idByOrder;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
