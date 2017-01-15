package dao;

import java.sql.SQLException;

/**
 * Created by laonen on 15.01.2017.
 */
public interface IDao<T> {
    T create(T entity) throws SQLException;
    T read() throws SQLException;
    void update(T entity) throws SQLException;
    void delete(T entity) throws SQLException;
}
