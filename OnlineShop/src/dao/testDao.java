package dao;

import entity.Goods;

import java.sql.SQLException;

/**
 * Created by laonen on 15.01.2017.
 */
public class testDao {
    public static void main(String[] args) {
        try {
            System.out.println(new GoodsDao().getById(5).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
