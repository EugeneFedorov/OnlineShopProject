package dao;

import entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laonen on 15.01.2017.
 */
public class GoodsDao implements GenericDao<Goods> {
    private String strSQL;
    private PreparedStatement statement;

    public GoodsDao() {
    }

    @Override
    public long create(Goods entity) {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("goods (nameGoods, description, price, remainingAmount) ").
                values(" ?, ?, ?, ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDouble(3, entity.getPrice());
            statement.setDouble(4, entity.getRemainingAmount());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
        Connector.disConnect(connection);
        return id;
    }

    @Override
    public void update(Goods entity) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" goods ").set(" remainingAmount = " + entity.getRemainingAmount()).
                where(" idGoods = ? ").build();
        assert connection != null;
        executeUpdate(entity, connection);
        Connector.disConnect(connection);
    }

    private void executeUpdate(Goods entity, Connection connection) {
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Goods entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("goods").where(" idGoods = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<Goods> getAll() {
        List<Goods> goodsList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" goods ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.execute();
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                goodsList.add(ResultFormQuery.getGoodsFromQuery(set));
            }
            return goodsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return goodsList;
        } finally {
            Connector.disConnect(connection);
        }
    }

    @Override
    public Goods getById(long id) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" goods ").where(" idGoods = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, id);
            statement.execute();
            if (statement.getResultSet().next()) {
                return ResultFormQuery.getGoodsFromQuery(statement.getResultSet());
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            Connector.disConnect(connection);
        }
    }
}
