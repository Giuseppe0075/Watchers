package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T> {
    public void doSave(T entity) throws SQLException, Exception;
    public void doDelete(T entity) throws SQLException, Exception;
    public T doRetrieveByKey(String... key) throws SQLException, Exception;
    public Collection<T> doRetrieveAll(String order) throws SQLException, Exception;
    public void doSaveOrUpdate(T entity) throws SQLException, Exception;
    public Collection<T> doRetriveByCond(String cond) throws SQLException, Exception;
}
