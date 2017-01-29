package dao;

import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laonen on 22.01.2017.
 */
public class CustomerDao implements GenericDao<Customer> {
    private String strSQL;
    private PreparedStatement statement;

    public CustomerDao() {
    }

    @Override
    public long create(Customer entity) {
        Connection connection = Connector.connect();
        long id = 0L;
        strSQL = new SqlBuilder().insert("customer (name, surname, email, password, idByAdress, phone ) ").
                values(" ?, ?, ?, ?, ?, ? ").build();
        assert connection != null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.setLong(5, entity.getAdress().getId());
            statement.setString(6, entity.getPhone());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            connectionRollback(connection);
            return id;
        } finally {
            Connector.disConnect(connection);
        }
    }

    @Override
    public void update(Customer entity) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().update(" customer ").set(" name = " + entity.getName()
                + " , surname = " + entity.getSurname() + " , email = " + entity.getEmail()
                + " , password = " + entity.getPassword() + " , idByAdress = " + entity.getAdress().getId()
                + " , phone = " + entity.getPhone()).where(" idCustomer = ? ").build();
        assert connection != null;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connectionRollback(connection);
        }
        Connector.disConnect(connection);
    }

    @Override
    public void delete(Customer entity) throws SQLException {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().delete().from("customer").where(" idCustomer = ? ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        statement.setLong(1, entity.getId());
        statement.executeUpdate();
        Connector.disConnect(connection);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" customer ").build();
        assert connection != null;
        statement = connection.prepareStatement(strSQL);
        ResultSet set = statement.getResultSet();
        while (set.next()) {
            customerList.add(ResultFormQuery.getCustomerFromQuery(set));
        }
        return customerList;
    }

    @Override
    public Customer getById(long id) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" * ").from(" customer c ").
                join(" adress a ").on(" c.idByAdress ").equal(" a.idAdress ").where(" c.idCustomer = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, id);
            statement.execute();
            if (statement.getResultSet().next()) {
                return ResultFormQuery.getCustomerFromQuery(statement.getResultSet());
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

    public long idByNamePwd(String user, String pwd) {
        long id = 0L;
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" idCustomer ").from(" customer ").
                where(" name = ? ").and(" password = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setString(1, user);
            statement.setString(2, pwd);
            statement.execute();
            if (statement.getResultSet().next()) {
                id = statement.getResultSet().getLong("idCustomer");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        } finally {
            Connector.disConnect(connection);
        }
    }

    public String getRole(String userName) {
        Connection connection = Connector.connect();
        strSQL = new SqlBuilder().select(" role ").from(" customer ").where(" name = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setString(1, userName);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        } finally {
            Connector.disConnect(connection);
        }
        return "";
    }

    private void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
