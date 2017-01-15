package dao;

import entity.Goods;

import java.sql.*;
import java.util.List;

/**
 * Created by laonen on 15.01.2017.
 */
public class GoodsDao implements GenericDao<Goods> {
    private Connection connection = Connector.connect();
    private String strSQL;
    private PreparedStatement statement;

    @Override
    public long create(Goods entity) throws SQLException {
        long id = 0L;
        strSQL = new SqlBuilder().insert("goods (nameGoods, description, price, remainingAmount) ").
                values(" ?, ?, ?, ? ").build();
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
        Connector.disConnect(connection);
        return id;
    }

    @Override
    public void update(Goods entity) throws SQLException {
        strSQL = new SqlBuilder().update(" goods ").set(" remainingAmount " + entity.getRemainingAmount()).
                where(" idGoods = ? ").build();
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public boolean delete(Goods entity) throws SQLException {

        return false;
    }

    @Override
    public List<Goods> getAll() throws SQLException {
        return null;
    }

    @Override
    public Goods getById(long id) throws SQLException {
        strSQL = new SqlBuilder().select(" * ").from(" goods ").where(" idGoods = ? ").build();
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, id);
        statement.execute();
        return ResultFormQuery.getGoodsFromQuery(statement.getResultSet());
    }
}