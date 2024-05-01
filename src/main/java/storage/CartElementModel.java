package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartElementModel implements DAO<CartElementBean> {
    private static final String TABLE = "Cart";
    private static final List<String> columns = List.of("user", "watch", "quantity");
    @Override
    public void doSave(CartElementBean entity) throws Exception {
        List<Object> values = List.of(entity.getUser(), entity.getWatch(), entity.getQuantity());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(CartElementBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public CartElementBean doRetrieveByKey(List<Object> keys) throws Exception {
        if (keys.size() != 2) throw new SQLException("Cart | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("user", "watch"), keys);
        return new CartElementBean(rs);
    }

    @Override
    public Collection<CartElementBean> doRetrieveByCond(String cond) throws Exception {
        List<CartElementBean> cartElements = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while (rs.next()) {
            cartElements.add(new CartElementBean(rs));
        }
        return cartElements;
    }

    @Override
    public Collection<CartElementBean> doRetrieveAll() throws Exception {
        List<CartElementBean> cartElements = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while (rs.next()) {
            cartElements.add(new CartElementBean(rs));
        }
        return cartElements;
    }

    @Override
    public void doSaveOrUpdate(CartElementBean entity) throws Exception {

    }
}
