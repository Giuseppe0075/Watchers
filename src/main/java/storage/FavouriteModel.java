package storage;

import java.sql.SQLException;
import java.util.Collection;

public class FavouriteModel implements DAO<FavouriteBean>{
    @Override
    public void doSave(FavouriteBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(FavouriteBean entity) throws SQLException, Exception {

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
