package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FavouriteModel implements DAO<FavouriteBean>{
    private static final String TABLE = "Favourite";
    private static final List<String> columns = List.of("watch", "user");
    @Override
    public void doSave(FavouriteBean entity) throws Exception {
        List<Object> values = List.of(entity.getWatch(), entity.getUser());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(FavouriteBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public FavouriteBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new SQLException("Favourite | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("watch", "user"), keys);
        return new FavouriteBean(rs);
    }

    @Override
    public Collection<FavouriteBean> doRetrieveByCond(String cond) throws Exception {
        List<FavouriteBean> favourites = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()) {
            favourites.add(new FavouriteBean(rs));
        }
        return favourites;
    }

    @Override
    public Collection<FavouriteBean> doRetrieveAll() throws Exception {
        List<FavouriteBean> favourites = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            favourites.add(new FavouriteBean(rs));
        }
        return favourites;
    }

    @Override
    public void doSaveOrUpdate(FavouriteBean entity) throws SQLException, Exception {

    }
}
