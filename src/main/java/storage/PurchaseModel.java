package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class PurchaseModel implements DAO<PurchaseBean>{
    private static final String TABLE = "Purchase";
    private static final List<String> columns = List.of("user", "watch", "quantity", "IVA", "price");
    @Override
    public void doSave(PurchaseBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getUser(), entity.getWatch(), entity.getQuantity(), entity.getIVA(), entity.getPrice());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(PurchaseBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public PurchaseBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<PurchaseBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<PurchaseBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(PurchaseBean entity) throws SQLException, Exception {

    }
}
