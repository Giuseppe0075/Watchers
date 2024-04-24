package storage;

import java.sql.SQLException;
import java.util.Collection;

public class PurchaseModel implements DAO<PurchaseBean>{
    @Override
    public void doSave(PurchaseBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(PurchaseBean entity) throws SQLException, Exception {

    }

    @Override
    public PurchaseBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<PurchaseBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<PurchaseBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(PurchaseBean entity) throws SQLException, Exception {

    }
}
