package storage;

import java.sql.SQLException;
import java.util.Collection;

public class ReviewModel implements DAO<ReviewBean>{
    @Override
    public void doSave(ReviewBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(ReviewBean entity) throws SQLException, Exception {

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
