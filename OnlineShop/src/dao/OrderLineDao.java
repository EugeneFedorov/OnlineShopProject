package dao;

import entity.OrderLine;

import java.sql.*;
import java.util.List;

/**
 * Created by laonen on 22.01.2017.
 */
public class OrderLineDao implements GenericDao<OrderLine> {
    private String strSQL;
    private PreparedStatement statement;

    @Override
    public long create(OrderLine entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().insert("order_line (idByGoods, idByOrder, quantity ) ").
                values(" ?, ?, ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getIdByGoods().getId());
        statement.setLong(2, entity.getIdByOrder().getId());
        statement.setDouble(3, entity.getQuantity());
        statement.executeUpdate();
        Connector.disConnect(connection);
        return 0L;
    }

    @Override
    public void update(OrderLine entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" order_line ").set(" quantity = " + entity.getQuantity()).
                where(" idByGoods = ? ").and(" idByOrder = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getIdByGoods().getId());
        statement.setLong(2, entity.getIdByOrder().getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public void delete(OrderLine entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("order_line").where(" idByGoods = ? ").and(" idByOrder = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getIdByGoods().getId());
        statement.setLong(2, entity.getIdByOrder().getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<OrderLine> getAll() throws SQLException {
        return null;
    }

    @Override
    public OrderLine getById(long id) throws SQLException {
        return null;
    }


}
