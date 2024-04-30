package storage;

import java.sql.SQLException;
import java.util.Collection;

public class ReviewModel implements DAO<ReviewModel> {
    @Override
    public void doSave(ReviewModel entity) throws SQLException, Exception {

    }

    @Override
    public void doDelete(ReviewModel entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public ReviewModel doRetrieveByKey(Object... key) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<ReviewModel> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<ReviewModel> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(ReviewModel entity) throws SQLException, Exception {

    }
}
