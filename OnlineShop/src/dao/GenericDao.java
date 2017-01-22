package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by laonen on 15.01.2017.
 */
public interface GenericDao<T> {

    long create(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(T entity) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(long id) throws SQLException;
}
