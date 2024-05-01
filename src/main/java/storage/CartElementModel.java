package storage;

import java.sql.ResultSet;
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
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public CartElementBean doRetrieveByKey(List<Object> keys) throws SQLException, Exception {
        if (keys.size() != 2) throw new SQLException("Cart | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("user", "watch"), keys);
        return new CartElementBean(rs);
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
