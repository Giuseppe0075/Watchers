package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class OperationModel implements DAO<OperationBean>{
    private static final String TABLE = "Operation";
    private static final List<String> columns = List.of("admin", "watch", "operation", "date");
    @Override
    public void doSave(OperationBean entity) throws SQLException, Exception {
        List<Object> values = List.of(entity.getAdmin(), entity.getWatch(), entity.getOperation(), entity.getDate());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(OperationBean entity) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public OperationBean doRetrieveByKey(List<Object> keys) throws SQLException, Exception {
        if(keys.size() != 1) throw new SQLException("Operation | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("id"), keys);
        return new OperationBean(rs);
    }

    @Override
    public Collection<OperationBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<OperationBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(OperationBean entity) throws SQLException, Exception {

    }
}
