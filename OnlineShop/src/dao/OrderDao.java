package dao;

import entity.Order;

import java.sql.*;
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
        strSQL = new SqlBuilder().insert("order (closeOrder, openOrder, ----, number) ").
                values(" ?, ?, ?, ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        statement.setDate(1, Date.valueOf(entity.getCloseOrder()));
        statement.setDate(2, Date.valueOf(entity.getOpenOrder()));
        statement.setString(3, entity.getStatus().toString());
        statement.setDouble(4, entity.getNumber());
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

    }

    @Override
    public void delete(Order entity) throws SQLException {

    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public Order getById(long id) throws SQLException {
        return null;
    }
}
