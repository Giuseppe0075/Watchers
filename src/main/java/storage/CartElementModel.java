package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class CartElementModel implements DAO<CartElementBean> {
    private static final String TABLE = "Cart";
    private static final List<String> columns = List.of("user", "watch", "quantity");
    @Override
    public void doSave(CartElementBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getUser(), entity.getWatch(), entity.getQuantity());
        Model.doSave(TABLE, values, columns);
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
