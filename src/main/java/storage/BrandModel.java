package storage;

import java.sql.SQLException;
import java.util.Collection;

public class BrandModel implements DAO<BrandBean>{
    @Override
    public void doSave(BrandBean entity) throws SQLException, Exception {
    }

    @Override
    public void doDelete(BrandBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public BrandBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<BrandBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<BrandBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(BrandBean entity) throws SQLException, Exception {

    }
}
