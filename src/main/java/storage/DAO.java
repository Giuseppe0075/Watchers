package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface DAO<T> {
    void doSave(T entity) throws Exception;

    void doDelete(T entity) throws Exception;

    /**
     * doDeleteByCondition deletes elements from table where a certain condition is valid.
     * The condition MUST be like "WHERE value = "value"... ORDER BY .... ecc"
     * The method DOESN'T parametrize inputs.
     */
    void doDeleteByCond(String cond) throws Exception;

    Collection<T> doRetrieveByCond(String cond) throws Exception;

    T doRetrieveByKey(List<Object> keys) throws Exception;

    Collection<T> doRetrieveAll() throws Exception;

    void doSaveOrUpdate(T entity) throws Exception;
}
