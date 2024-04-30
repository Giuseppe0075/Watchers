package storage;

import java.sql.SQLException;
import java.util.Collection;

public class OperationModel implements DAO<OperationBean>{
    @Override
    public void doSave(OperationBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(OperationBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public OperationBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<OperationBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<OperationBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(OperationBean entity) throws SQLException, Exception {

    }
}
