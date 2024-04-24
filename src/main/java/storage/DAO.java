package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T> {
    void doSave(T entity) throws SQLException, Exception;

    void doDelete(T entity) throws SQLException, Exception;

    T doRetrieveByKey(Object... key) throws SQLException, Exception;

    Collection<T> doRetrieveByCond(String cond) throws SQLException, Exception;

    Collection<T> doRetrieveAll() throws SQLException, Exception;

    void doSaveOrUpdate(T entity) throws SQLException, Exception;
}
