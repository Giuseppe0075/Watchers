package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class FavouriteModel implements DAO<FavouriteBean>{
    private static final String TABLE = "Favourite";
    private static final List<String> columns = List.of("user", "watch");
    @Override
    public void doSave(FavouriteBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getUser(), entity.getWatch());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(FavouriteBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public FavouriteBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
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
