package storage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OperationModel implements DAO<OperationBean>{
    private static final String TABLE = "Operation";
    private static final List<String> columns = List.of("admin", "watch", "operation", "date");
    @Override
    public void doSave(OperationBean entity) throws Exception {
        List<Object> values = List.of(entity.getAdmin(), entity.getWatch(), entity.getOperation(), entity.getDate());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(OperationBean entity) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public OperationBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 1) throw new SQLException("Operation | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("id"), keys);
        return new OperationBean(rs);
    }

    @Override
    public Collection<OperationBean> doRetrieveByCond(String cond) throws Exception {
        List<OperationBean> operations = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()) {
            operations.add(new OperationBean(rs));
        }
        return operations;
    }

    @Override
    public Collection<OperationBean> doRetrieveAll() throws Exception {
        List<OperationBean> operations = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            operations.add(new OperationBean(rs));
        }
        return operations;
    }

    @Override
    public void doSaveOrUpdate(OperationBean entity) throws Exception {

    }
}
