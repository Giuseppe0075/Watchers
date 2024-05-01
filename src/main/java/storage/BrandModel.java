package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BrandModel implements DAO<BrandBean>{
    private static final String TABLE = "Brand";
    private static final List<String> columns = List.of("name", "description");
    @Override
    public void doSave(BrandBean entity) throws Exception {
        List<Object> values = List.of(entity.getName(), entity.getDescription());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(BrandBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public BrandBean doRetrieveByKey(List<Object> keys) throws Exception {
        if (keys.size() != 1) throw new SQLException("Brand | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("business_name"), keys);
        return new BrandBean(rs);
    }

    @Override
    public Collection<BrandBean> doRetrieveByCond(String cond) throws Exception {
        List<BrandBean> brands = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while (rs.next()) {
            brands.add(new BrandBean(rs));
        }
        return brands;
    }

    @Override
    public Collection<BrandBean> doRetrieveAll() throws Exception {
        List<BrandBean> brands = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while (rs.next()) {
            brands.add(new BrandBean(rs));
        }
        return brands;
    }

    @Override
    public void doSaveOrUpdate(BrandBean entity) throws Exception {

    }
}
