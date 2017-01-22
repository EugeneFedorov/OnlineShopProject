package dao;

import entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by laonen on 11.01.2017.
 */
class ResultFormQuery {

    static Goods getGoodsFromQuery(ResultSet set) throws SQLException {
        Goods goods = new Goods();
        goods.setId(set.getLong("idGoods"));
        goods.setName(set.getString("nameGoods"));
        goods.setDescription(set.getString("description"));
        goods.setPrice(set.getDouble("price"));
        goods.setPrice(set.getDouble("remainingAmount"));
        return goods;
    }

    static Adress getAdressFromQuery(ResultSet set) throws SQLException {
        Adress adress = new Adress();
        adress.setId(set.getLong("idAdress"));
        adress.setTown(set.getString("town"));
        adress.setIndex(set.getString("index"));
        adress.setHome(getHomeFromQuery(set));
        return adress;
    }

    static Home getHomeFromQuery(ResultSet set) throws SQLException {
        Home home = new Home();
        home.setId(set.getLong("idHome"));
        home.setStreet(set.getString("street"));
        home.setNumberHouse(set.getString("numberHouse"));
        home.setNumberFlat(set.getInt("numberFlat"));
        return home;
    }

    static Customer getCustomerFromQuery(ResultSet set) throws SQLException {
        Customer customer = new Customer();
        customer.setId(set.getLong("idCustomer"));
        customer.setName(set.getString("name"));
        customer.setSurname(set.getString("surname"));
        customer.setEmail(set.getString("email"));
        customer.setPassword(set.getString("password"));
        Adress adress = new Adress(); //set.getInt("idByAdress")
        customer.setAdress(adress);
        customer.setPhone(set.getString("phone"));
        customer.setAdress(getAdressFromQuery(set));
        return customer;
    }

    static Order getOrderFromQuery(ResultSet set) throws SQLException {
        Order order = new Order();
        order.setId(set.getLong("idOrder"));
        order.setOpenOrder(set.getDate("openOrder").toLocalDate());
        order.setCloseOrder(set.getDate("closeOrder").toLocalDate());
        order.setStatus(OrderStatus.valueOf(set.getString("orderStatus")));
        order.setNumber(set.getInt("number"));
        order.setCustomer(getCustomerFromQuery(set));
        while(set.next()){
            order.addGoodsInOrder(getGoodsFromQuery(set), set.getDouble("quantity"));
        }
        return order;
    }
}
