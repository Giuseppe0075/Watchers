package storage;

import java.sql.SQLException;
import java.util.Collection;

public class CartElementModel implements DAO<CartElementBean> {
    @Override
    public void doSave(CartElementBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(CartElementBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public CartElementBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<CartElementBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<CartElementBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(CartElementBean entity) throws SQLException, Exception {

    }
}
