package dao;

import entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.ClosableUtils.silentClose;

/**
 * Created by laonen on 22.01.2017.
 */
public class CustomerDao implements GenericDao<Customer> {
    private String strSQL;


    public CustomerDao() {
    }

    @Override
    public long create(Customer entity) {
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
        ResultSet rs = null;
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
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            connectionRollback(connection);
            return id;
        } finally {
            silentClose(rs);
            silentClose(statement);
            Connector.disConnect(connection);
        }
    }

    @Override
    public void update(Customer entity) {
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
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
        silentClose(statement);
        Connector.disConnect(connection);
    }

    @Override
    public void delete(Customer entity)  {
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
        strSQL = new SqlBuilder().delete().from("customer").where(" idCustomer = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        silentClose(statement);
        Connector.disConnect(connection);
    }

    @Override
    public List<Customer> getAll()  {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
        ResultSet rs = null;
        strSQL = new SqlBuilder().select(" * ").from(" customer ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            rs = statement.getResultSet();
            while (rs.next()) {
                customerList.add(ResultFormQuery.getCustomerFromQuery(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        silentClose(statement);
        silentClose(rs);
        Connector.disConnect(connection);
        return customerList;
    }

    @Override
    public Customer getById(long id) {
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
        ResultSet rs = null;
        strSQL = new SqlBuilder().select(" * ").from(" customer c ").
                join(" adress a ").on(" c.idByAdress ").equal(" a.idAdress ").where(" c.idCustomer = ? ").build();
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setLong(1, id);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                return ResultFormQuery.getCustomerFromQuery(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            silentClose(statement);
            silentClose(rs);
            Connector.disConnect(connection);
        }
    }

    public long idByNamePwd(String user, String pwd) {
        long id = 0L;
        Connection connection = Connector.connect();
        PreparedStatement statement = null;
        strSQL = new SqlBuilder().select(" idCustomer ").from(" customer ").
                where(" name = ? ").and(" password = ? ").build();
        ResultSet rs = null;
        assert connection != null;
        try {
            statement = connection.prepareStatement(strSQL);
            statement.setString(1, user);
            statement.setString(2, pwd);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                id = rs.getLong("idCustomer");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        } finally {
            silentClose(statement);
            silentClose(rs);
            Connector.disConnect(connection);
        }
    }

    public String getRole(String userName) {
        Connection connection = Connector.connect();
        PreparedStatement statement;
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
