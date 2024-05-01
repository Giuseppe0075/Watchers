package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class FavouriteModel implements DAO<FavouriteBean>{
    private static final String TABLE = "Favourite";
    private static final List<String> columns = List.of("watch", "user");
    @Override
    public void doSave(FavouriteBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getWatch(), entity.getUser());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(FavouriteBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public FavouriteBean doRetrieveByKey(List<Object> keys) throws SQLException, Exception {
        if(keys.size() != 2) throw new SQLException("Favourite | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("watch", "user"), keys);
        return new FavouriteBean(rs);
    }

    @Override
    public Collection<FavouriteBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<FavouriteBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(FavouriteBean entity) throws SQLException, Exception {

    }
}
