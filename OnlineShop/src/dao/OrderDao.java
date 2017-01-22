package dao;

import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laonen on 18.01.2017.
 */
public class OrderDao implements GenericDao<Order> {
    private String strSQL;
    private PreparedStatement statement;

    OrderDao() throws SQLException {
    }

    @Override
    public long create(Order entity) throws SQLException {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("orderinshop (closeOrder, openOrder, orderStatus, number, idByCustomer) ").
                values(" ?, ?, ?, ?, ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        statement.setDate(1, Date.valueOf(entity.getCloseOrder()));
        statement.setDate(2, Date.valueOf(entity.getOpenOrder()));
        statement.setString(3, entity.getStatus().toString());
        statement.setDouble(4, entity.getNumber());
        statement.setLong(5, entity.getIdByCustomer());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        Connector.disConnect(connection);
        return id;
    }

    @Override
    public void update(Order entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" orderinshop ").set(" closeOrder = " + entity.getCloseOrder()
                + " , orderStatus = " + entity.getStatus()).where(" idOrder = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
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
    public List<Order> getAll() throws SQLException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" orderinshop ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        ResultSet set = statement.getResultSet();
        while (set.next()) {
            orderList.add(ResultFormQuery.getOrderFromQuery(set));
        }
        return orderList;
    }

    @Override
    public Order getById(long id) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" orderinshop ").where(" idOrder = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, id);
        statement.execute();
        if (statement.getResultSet().next()) {
            return ResultFormQuery.getOrderFromQuery(statement.getResultSet());
        } else {
            return null;
        }
    }
}
