package Model.Models;

import Model.Beans.CartElementBean;
import Model.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartElementModel implements DAO<CartElementBean> {
    private static final String TABLE = "Cart";
    private static final List<String> COLUMNS = List.of("user", "watch", "quantity");
    private static final List<String> KEYS = List.of("user", "watch");
    @Override
    public void doSave(CartElementBean cartElementBean) throws Exception {
        List<Object> values = List.of(cartElementBean.getUser(), cartElementBean.getWatch(), cartElementBean.getQuantity());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(CartElementBean cartElementBean) throws Exception {
        Model.doDeleteByCond(TABLE,"WHERE user = ? AND watch = ?",List.of(cartElementBean.getUser(),cartElementBean.getWatch()));
    }

    @Override
    public void doDeleteByCond(String cond, List<Object> values) throws Exception {
        Model.doDeleteByCond(TABLE, cond, values);
    }

    @Override
    public CartElementBean doRetrieveByKey(List<Object> keys) throws Exception {
        if (keys.size() != 2) throw new SQLException("Cart | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
        if(!rs.next()) return null;
        return new CartElementBean(rs);
    }

    @Override
    public Collection<CartElementBean> doRetrieveByCond(String cond, List<Object> values) throws Exception {
        List<CartElementBean> cartElements = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond, values);
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
    public void doSaveOrUpdate(CartElementBean cartElementBean) throws Exception {
        CartElementBean temp = this.doRetrieveByKey(List.of(cartElementBean.getUser(), cartElementBean.getWatch()));
        if (temp == null) {
            doSave(cartElementBean);
            return;
        }
        List<Object> values = List.of(cartElementBean.getQuantity(), cartElementBean.getUser(), cartElementBean.getWatch());
        Model.doUpdate(TABLE, COLUMNS.subList(2, COLUMNS.size()), values, KEYS);
    }
}
