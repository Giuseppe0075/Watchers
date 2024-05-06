package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface DAO<T> {

    /**
     * doSave saves an element in the table.
     * The method parametrize inputs.
     */
    void doSave(T entity) throws Exception;

    void doDelete(T entity) throws Exception;

    /**
     * doDeleteByCondition deletes elements from table where a certain condition is valid.
     * The condition MUST be like "WHERE value = ?, ..."
     * The method parametrize inputs.
     */
    void doDeleteByCond(String cond, List<Object> values) throws Exception;

    /**
     * doRetrieveByCond retrieves elements from table where a certain condition is valid.
     * The condition MUST be like "WHERE value = ?, ...,  ORDER BY .... ecc"
     * The method parametrize inputs.
     */
    Collection<T> doRetrieveByCond(String cond, List<Object> values) throws Exception;

    /**
     * doRetrieveByKey retrieves elements from table by the key/keys.
     * The method parametrize inputs.
     */
    T doRetrieveByKey(List<Object> keys) throws Exception;

    /**
     * doRetrieveAll retrieves all elements from table.
     */
    Collection<T> doRetrieveAll() throws Exception;

    /**
     * doSaveOrUpdate saves or updates an element in the table.
     * The method parametrize inputs.
     * To save an element, the id must be 0.
     * To update an element, the id must be different from 0.
     */
    void doSaveOrUpdate(T entity) throws Exception;
}
