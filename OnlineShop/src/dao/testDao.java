package dao;

import entity.Goods;

import java.sql.SQLException;


/**
 * Created by laonen on 15.01.2017.
 */

public class testDao {
    public static void main(String[] args) {
        Connector.setTestEnvironment(true);
        Goods goods;
        try {
            goods = new GoodsDao().getById(6);
            new GoodsDao().delete(goods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
