package dao;

import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by laonen on 18.01.2017.
 */
public class OrderDao implements GenericDao<Order> {
    private static final Logger log = Logger.getLogger(Connector.class.getName());
    private String strSQL;
    private PreparedStatement statement;

    public OrderDao() {
    }

    @Override
    public long create(Order entity) {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("orderinshop (openOrder, orderStatus, idByCustomer) ").
                values(" ?, ?, ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, Date.valueOf(entity.getOpenOrder()));
            statement.setString(2, entity.getStatus().toString());
            statement.setLong(3, entity.getCustomer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Connector.disConnect(connection);
        return id;
    }

    @Override
    public void update(Order entity) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" orderinshop ").set(" closeOrder = " + entity.getCloseOrder()
                + " , orderStatus = '" + entity.getStatus().toString()
                + "' , number = '" + entity.getNumber()+ "'").where(" idOrder = ? ").build();
        assert connection != null;
        executeUpdate(entity, connection);
        Connector.disConnect(connection);
    }

    private void executeUpdate(Order order, Connection connection) {
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Was unable to retrieve DB connection", e);
            throw new RuntimeException("jdbc layer error", e);
        }
    }

    @Override
    public void delete(Order entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("orderinshop").where(" idOrder = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<Order> getAll()  {
        List<Order> orderList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" orderinshop ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                orderList.add(ResultFormQuery.getOrderFromQuery(set));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.disConnect(connection);
        }
        return orderList;
    }

    @Override
    public Order getById(long id)  {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" os.*, c.*, g.*, ol.quantity ").from(" orderinshop os ").
                join(" customer c ").on(" c.idCustomer ").equal(" os.idByCustomer ").
                join(" order_line ol ").on(" ol.idByOrder ").equal(" os.idOrder ").
                join(" goods g ").on(" g.idGoods ").equal(" ol.idByGoods ").where(" os.idOrder = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, id);
            statement.execute();
            if (statement.getResultSet().next()) {
                return ResultFormQuery.getOrderFromQuery(statement.getResultSet());
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.disConnect(connection);
        }
        return null;
    }
}