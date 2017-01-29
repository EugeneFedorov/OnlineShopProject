package dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by laonen on 29.01.2017.
 */
public class OrderDto {
    private long idCustomer;
    private Map<Long, Double> goodsOrder;

    public OrderDto(long idCustomer, Map<Long, Double> goodsOrder) {
        this.idCustomer = idCustomer;
        this.goodsOrder = goodsOrder;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Map<Long, Double> getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(Map<Long, Double> goodsOrder) {
        this.goodsOrder = goodsOrder;
    }
}
