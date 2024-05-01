package storage;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ReviewModel implements DAO<ReviewBean> {
    private static final String TABLE = "Review";
    private static final List<String> columns = List.of("watch", "user", "stars", "comment", "date");
    @Override
    public void doSave(ReviewBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getWatch(), entity.getUser(), entity.getStars(), entity.getDescription(), entity.getDate());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(ReviewBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public ReviewBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<ReviewBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<ReviewBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(ReviewBean entity) throws SQLException, Exception {

    }
}
