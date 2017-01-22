package entity;

/**
 * Created by laonen on 22.01.2017.
 */
public class GoodsInOrder {
    private Goods goods;
    private double quantity;

    public GoodsInOrder(Goods goods, double quantity) {
        this.goods = goods;
        this.quantity = quantity;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
