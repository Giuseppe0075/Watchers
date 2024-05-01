package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PurchaseModel implements DAO<PurchaseBean>{
    private static final String TABLE = "Purchase";
    private static final List<String> COLUMNS = List.of("user", "watch", "quantity", "IVA", "price");
    private static final List<String> KEYS = List.of("id", "user", "watch");
    @Override
    public void doSave(PurchaseBean purchaseBean) throws Exception {
        List<Object> values = List.of(purchaseBean.getUser(), purchaseBean.getWatch(), purchaseBean.getQuantity(), purchaseBean.getIVA(), purchaseBean.getPrice());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(PurchaseBean purchaseBean) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public PurchaseBean doRetrieveByKey(List<Object> key) throws Exception {
        if(key.size() != 3) throw new SQLException("Purchase | doRetrieveByKey: Failed | The number of keys is not 3");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, key);
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
    public void doSaveOrUpdate(PurchaseBean purchaseBean) throws Exception {
        if (purchaseBean.getId() == 0 || purchaseBean.getUser() == 0 || purchaseBean.getWatch() == 0) {
            this.doSave(purchaseBean);
            return;
        }
        List<Object> values = List.of( purchaseBean.getQuantity(), purchaseBean.getIVA(), purchaseBean.getPrice(), purchaseBean.getId(), purchaseBean.getUser(), purchaseBean.getWatch());
        //needed to remove the first two columns from the columns list because they are keys, so they are used to update the row
        Model.doUpdate(TABLE, COLUMNS.subList(2, COLUMNS.size()), values, KEYS);
    }
}
