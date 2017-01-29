package service;

import dao.CustomerDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.OrderLineDao;
import dto.OrderDto;
import entity.Customer;
import entity.Goods;
import entity.Order;
import entity.OrderLine;

import java.util.Map;

/**
 * Created by laonen on 29.01.2017.
 */
public class OrderService {
    private static volatile OrderService instance;

    private OrderService() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (GoodsService.class) {
                if (instance == null) instance = new OrderService();
            }
        }
        return instance;
    }

    public boolean isCreateOrder(OrderDto dto) {
        Customer customer = new CustomerDao().getById(dto.getIdCustomer());
        OrderDao orderDao = new OrderDao();
        Order order = new Order(customer);
        order.setId(orderDao.create(order));
        order.setNumber(String.valueOf(order.getId()) + "_" + String.valueOf(customer.getId()));
        orderDao.update(order);
        for (Map.Entry<Long, Double> entry : dto.getGoodsOrder().entrySet()) {
            Long goodsId = entry.getKey();
            Double quantity = entry.getValue();
            if (quantity > 0) {
                OrderLine orderLine = new OrderLine(goodsId, order.getId(), quantity);
                new OrderLineDao().create(orderLine);
            }
            GoodsDao goodsDao = new GoodsDao();
            Goods goods = goodsDao.getById(goodsId);
            goods.setRemainingAmount(goods.getRemainingAmount() - quantity);
            goodsDao.update(goods);
        }
        return order.getId() > 0L;
    }
}
