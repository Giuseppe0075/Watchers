package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReviewModel implements DAO<ReviewBean> {
    private static final String TABLE = "Review";
    private static final List<String> columns = List.of("watch", "user", "stars", "comment", "date");
    @Override
    public void doSave(ReviewBean entity) throws Exception {
        List<Object> values = List.of(entity.getWatch(), entity.getUser(), entity.getStars(), entity.getDescription(), entity.getDate());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(ReviewBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public ReviewBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new SQLException("Review | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("watch", "user"), keys);

        return new ReviewBean(rs);
    }

    @Override
    public Collection<ReviewBean> doRetrieveByCond(String cond) throws Exception {
        List<ReviewBean> reviews = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()) {
            reviews.add(new ReviewBean(rs));
        }
        return reviews;
    }

    @Override
    public Collection<ReviewBean> doRetrieveAll() throws Exception {
        List<ReviewBean> reviews = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            reviews.add(new ReviewBean(rs));
        }
        return reviews;
    }

    @Override
    public void doSaveOrUpdate(ReviewBean entity) throws Exception {

    }
}
