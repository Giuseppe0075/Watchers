package storage;

import java.util.Collection;

public interface DAO<T> {
    public void doSave(T entity);
    public void doDelete(T entity);
    public T doRetrieveByKey(String key);
    public Collection<T> doRetrieveAll(String order);
    public void doSaveOrUpdate(T entity);
    public Collection<T> doRetriveByCond(String cond);
}
