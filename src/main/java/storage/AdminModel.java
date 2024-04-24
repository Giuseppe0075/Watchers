package storage;

import java.sql.SQLException;
import java.util.Collection;

public class AdminModel implements DAO<AdminBean>{
    @Override
    public void doSave(AdminBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(AdminBean entity) throws SQLException, Exception {

    }

    @Override
    public AdminBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<AdminBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<AdminBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(AdminBean entity) throws SQLException, Exception {

    }
}
