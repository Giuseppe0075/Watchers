package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface WatchDao<T> {
    public T doRetriveByKey(int code) throws SQLException;

    public Collection<T> doRetrieveAll() throws SQLException;

    public void doSave(T object) throws SQLException;

    public void doUpdate(T object) throws SQLException;

    public void doDelete(T object) throws SQLException;
}
