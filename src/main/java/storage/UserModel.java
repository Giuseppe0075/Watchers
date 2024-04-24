package storage;

import java.sql.SQLException;
import java.util.Collection;

public class UserModel implements DAO<UserBean>{
    @Override
    public void doSave(UserBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(UserBean entity) throws SQLException, Exception {

    }

    @Override
    public UserBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<UserBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<UserBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(UserBean entity) throws SQLException, Exception {

    }
}
