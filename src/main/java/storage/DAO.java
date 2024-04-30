package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface DAO<T> {
    void doSave(T entity) throws SQLException, Exception;

    void doDelete(T entity) throws SQLException, Exception;

    /**
     * doDeleteByCondition deletes elements from table where a certain condition is valid.
     * The condition MUST be like "WHERE value = "value"... ORDER BY .... ecc"
     * The method DOESN'T parametrize inputs.
     * @param cond
     * @throws SQLException
     * @throws Exception
     */
    void doDeleteByCond(String cond) throws SQLException, Exception;

    Collection<T> doRetrieveByCond(String cond) throws SQLException, Exception;

    T doRetrieveByKey(Object... key) throws SQLException, Exception;

    Collection<T> doRetrieveAll() throws SQLException, Exception;

    void doSaveOrUpdate(T entity) throws SQLException, Exception;
}
