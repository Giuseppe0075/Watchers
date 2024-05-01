package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PurchaseModel implements DAO<PurchaseBean>{
    private static final String TABLE = "Purchase";
    private static final List<String> columns = List.of("user", "watch", "quantity", "IVA", "price");
    @Override
    public void doSave(PurchaseBean entity) throws Exception {
        List<Object> values = List.of(entity.getUser(), entity.getWatch(), entity.getQuantity(), entity.getIVA(), entity.getPrice());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(PurchaseBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public PurchaseBean doRetrieveByKey(List<Object> key) throws Exception {
        if(key.size() != 3) throw new SQLException("Purchase | doRetrieveByKey: Failed | The number of keys is not 3");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("id_order","watch", "user"), key);
        return new PurchaseBean(rs);
    }

    @Override
    public Collection<PurchaseBean> doRetrieveByCond(String cond) throws Exception {
        List<PurchaseBean> purchases = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()) {
            purchases.add(new PurchaseBean(rs));
        }
        return purchases;
    }

    @Override
    public Collection<PurchaseBean> doRetrieveAll() throws Exception {
        List<PurchaseBean> purchases = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            purchases.add(new PurchaseBean(rs));
        }
        return purchases;
    }

    @Override
    public void doSaveOrUpdate(PurchaseBean entity) throws Exception {

    }
}
