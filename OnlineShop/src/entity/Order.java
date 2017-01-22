package entity;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by laonen on 15.01.2017.
 */
public class Order {

    private  long id;
    private Customer customer;
    private int number;
    private List<GoodsInOrder> goodsInOrderList;
    private LocalDate openOrder;
    private LocalDate closeOrder;
    private OrderStatus status;

    public Order() {
    }

    public Order(int number, List<GoodsInOrder> goodsInOrderList) {
        this.number = number;
        this.goodsInOrderList = goodsInOrderList;
        this.openOrder = LocalDate.now();
        this.status = OrderStatus.ORDER_IN_ACCEPTED;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void setOpenOrder(LocalDate openOrder) {
        this.openOrder = openOrder;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean addGoodsInOrder(Goods goods, double quantity) {
        return goodsInOrderList.add(new GoodsInOrder(goods, quantity));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
