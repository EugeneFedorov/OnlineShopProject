package dao;

import entity.Home;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laonen on 22.01.2017.
 */
public class HomeDao implements GenericDao<Home> {
    private String strSQL;
    private PreparedStatement statement;

    public HomeDao() {
    }

    @Override
    public long create(Home entity) {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("home (street, numberHouse, numberFlat ) ").
                values(" ?, ?, ? ").build();
        assert connection != null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getStreet());
            statement.setString(2, entity.getNumberHouse());
            statement.setInt(3, entity.getNumberFlat());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connectionRollback(connection);
        }
        Connector.disConnect(connection);
        return id;
    }

    private void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Home entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" home ").set(" street = " + entity.getStreet() + " , numberHouse = "
                + entity.getNumberHouse() + " , numberFlat = " + entity.getNumberFlat()).where(" idHome = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public void delete(Home entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("home").where(" idHome = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<Home> getAll() throws SQLException {
        List<Home> homeList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" home ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        ResultSet set = statement.getResultSet();
        while (set.next()) {
            homeList.add(ResultFormQuery.getHomeFromQuery(set));
        }
        Connector.disConnect(connection);
        return homeList;
    }

    @Override
    public Home getById(long id) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" home ").where(" idHome = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, id);
        statement.execute();
        if (statement.getResultSet().next()) {
            Connector.disConnect(connection);
            return ResultFormQuery.getHomeFromQuery(statement.getResultSet());
        } else {
            Connector.disConnect(connection);
            return null;
        }
    }
}
