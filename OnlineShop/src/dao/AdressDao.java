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

    public AdressDao() throws SQLException {
    }

    @Override
    public long create(Adress entity) throws SQLException {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("adress (country, town, index, idByHome ) ").
                values(" ?, ?, ?, ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getCountry());
        statement.setString(2, entity.getTown());
        statement.setString(3, entity.getIndex());
        statement.setLong(4, entity.getHome().getId());
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            id = resultSet.getLong(1);
        }
        Connector.disConnect(connection);
        return id;
    }

    @Override
    public void update(Adress entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" adress ").set(" country = " + entity.getCountry() +
                " , town = " + entity.getTown() + " , index = " + entity.getIndex() +
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
        return adressList;
    }

    @Override
    public Adress getById(long id) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" adress ").where(" idAdress = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, id);
        statement.execute();
        if (statement.getResultSet().next()) {
            return ResultFormQuery.getAdressFromQuery(statement.getResultSet());
        } else {
            return null;
        }
    }
}
