package Model.Models;

import Model.DAO;
import Model.Beans.ReviewBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReviewModel implements DAO<ReviewBean> {
    private static final String TABLE = "Review";
    private static final List<String> COLUMNS = List.of("watch", "user", "stars", "description", "date");
    private static final List<String> KEYS = List.of("watch", "user");
    @Override
    public void doSave(ReviewBean reviewBean) throws Exception {
        List<Object> values = List.of(reviewBean.getWatch(), reviewBean.getUser(), reviewBean.getStars(), reviewBean.getDescription(), reviewBean.getDate());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(ReviewBean reviewBean) throws Exception {
        Model.doDeleteByCond(TABLE, "WHERE watch = ? AND user = ?", List.of(reviewBean.getWatch(), reviewBean.getUser()));
    }

    @Override
    public void doDeleteByCond(String cond, List<Object> values) throws Exception {
        Model.doDeleteByCond(TABLE, cond, values);
    }

    @Override
    public ReviewBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new SQLException("Review | doRetrieveByKey: Failed | The number of keys is not 2");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
        if(!rs.next()) return null;
        return new ReviewBean(rs);
    }

    @Override
    public Collection<ReviewBean> doRetrieveByCond(String cond, List<Object> values) throws Exception {
        List<ReviewBean> reviews = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond, values);
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
    public void doSaveOrUpdate(ReviewBean reviewBean) throws Exception {
        ReviewModel reviewModel = new ReviewModel();
        if (reviewModel.doRetrieveByKey(List.of(reviewBean.getWatch(), reviewBean.getUser())) == null) {
            this.doSave(reviewBean);
            return;
        }
        List<Object> values = List.of(reviewBean.getStars(), reviewBean.getDescription(), reviewBean.getDate(), reviewBean.getWatch(), reviewBean.getUser());
        //Need to remove the first two columns from the columns list because they are keys, so they are used to
        //build the WHERE clause of the UPDATE query
        Model.doUpdate(TABLE, COLUMNS.subList(2, COLUMNS.size()), values, KEYS);
    }
}
