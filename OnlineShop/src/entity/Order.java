package entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by laonen on 15.01.2017.
 */
public class Order {

    private int number;
    private List<Goods> goodsInOrderList;
    private LocalDate openOrder;
    private LocalDate closeOrder;
    private OrderStatus status;

    public Order(int number, List<Goods> goodsInOrderList) {
        this.number = number;
        this.goodsInOrderList = goodsInOrderList;
        this.openOrder = LocalDate.now();
        this.status = OrderStatus.ORDER_IN_ACCEPTED;
    }

    public int getNumber() {
        return number;
    }

    public List<Goods> getGoodsInOrderList() {
        return goodsInOrderList;
    }

    public LocalDate getOpenOrder() {
        return openOrder;
    }

    public LocalDate getCloseOrder() {
        return closeOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setCloseOrder(LocalDate closeOrder) {
        this.closeOrder = closeOrder;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean addGoodsInOrder(Goods goods, int amount, int id) {
        return goodsInOrderList.add(goods);
    }
}
