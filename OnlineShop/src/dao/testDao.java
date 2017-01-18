package dao;

import entity.Goods;

import java.sql.SQLException;

/**
 * Created by laonen on 15.01.2017.
 */
public class testDao {
    public static void main(String[] args) {
        Connector.pool();
        Goods goods;
        try {
            goods = new GoodsDao().getById(5);
            new GoodsDao().delete(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
