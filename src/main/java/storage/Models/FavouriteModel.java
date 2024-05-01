package storage.Models;

import storage.DAO;
import storage.Beans.FavouriteBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FavouriteModel implements DAO<FavouriteBean> {
    private static final String TABLE = "Favourite";
    private static final List<String> COLUMNS = List.of("watch", "user");
    private static final List<String> KEYS = List.of("watch", "user");
    @Override
    public void doSave(FavouriteBean favouriteBean) throws Exception {
        List<Object> values = List.of(favouriteBean.getWatch(), favouriteBean.getUser());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(FavouriteBean favouriteBean) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond, List<Object> values) throws Exception {
        Model.doDeleteByCond(TABLE, cond, values);
    }

    @Override
    public FavouriteBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new SQLException("Favourite | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
        return new FavouriteBean(rs);
    }

    @Override
    public Collection<FavouriteBean> doRetrieveByCond(String cond, List<Object> values) throws Exception {
        List<FavouriteBean> favourites = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond, values);
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
    public void doSaveOrUpdate(FavouriteBean favouriteBean) throws Exception {
        //Cannot update a favourite
        this.doSave(favouriteBean);
    }
}
