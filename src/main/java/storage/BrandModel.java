package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class BrandModel implements DAO<BrandBean>{
    private static final String TABLE = "Brand";
    private static final List<String> columns = List.of("name", "description");
    @Override
    public void doSave(BrandBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getName(), entity.getDescription());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(BrandBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public BrandBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<BrandBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<BrandBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(BrandBean entity) throws SQLException, Exception {

    }
}
