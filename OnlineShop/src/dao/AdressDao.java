package dao;

import entity.Adress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laonen on 22.01.2017.
 */
public class AdressDao implements GenericDao<Adress> {
    private String strSQL;
    private PreparedStatement statement;

    public AdressDao() {
    }

    @Override
    public long create(Adress entity) {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("adress (country, town, post_index, idByHome ) ").
                values(" ?, ?, ?, ? ").build();
        assert connection != null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getCountry());
            statement.setString(2, entity.getTown());
            statement.setString(3, entity.getPost_index());
            statement.setLong(4, entity.getHome().getId());
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
    public void update(Adress entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" adress ").set(" country = " + entity.getCountry() +
                " , town = " + entity.getTown() + " , post_index = " + entity.getPost_index() +
                " , idByHome = " + entity.getHome().getId()).where(" idHome = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public void delete(Adress entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("adress").where(" idAdress = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<Adress> getAll() throws SQLException {
        List<Adress> adressList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" adress ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        ResultSet set = statement.getResultSet();
        while (set.next()) {
            adressList.add(ResultFormQuery.getAdressFromQuery(set));
        }
        Connector.disConnect(connection);
        return adressList;
    }

    @Override
    public Adress getById(long id) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" adress a ").
                join(" home h ").on(" a.idByHome ").equal(" h.idHome ").where(" a.idAdress = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, id);
        statement.execute();
        if (statement.getResultSet().next()) {
            Connector.disConnect(connection);
            return ResultFormQuery.getAdressFromQuery(statement.getResultSet());
        } else {
            Connector.disConnect(connection);
            return null;
        }
    }
}
