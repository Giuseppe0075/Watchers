package storage.Models;

import storage.DAO;
import storage.Beans.OperationBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OperationModel implements DAO<OperationBean> {
    private static final String TABLE = "Operation";
    private static final List<String> COLUMNS = List.of("admin", "watch", "operation", "date");
    private static final List<String> KEYS = List.of("id");
    @Override
    public void doSave(OperationBean operationBean) throws Exception {
        List<Object> values = List.of(operationBean.getAdmin(), operationBean.getWatch(), operationBean.getOperation(), operationBean.getDate());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(OperationBean operationBean) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond, List<Object> values) throws Exception {
        Model.doDeleteByCond(TABLE, cond, values);
    }

    @Override
    public OperationBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 1) throw new SQLException("Operation | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
        return new OperationBean(rs);
    }

    @Override
    public Collection<OperationBean> doRetrieveByCond(String cond, List<Object> values) throws Exception {
        List<OperationBean> operations = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond, values);
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
    public void doSaveOrUpdate(OperationBean operationBean) throws Exception {
        if (operationBean.getId() == 0) {
            this.doSave(operationBean);
            return;
        }
        List<Object> values = List.of(operationBean.getAdmin(), operationBean.getWatch(), operationBean.getOperation(), operationBean.getDate(), operationBean.getId());
        Model.doUpdate(TABLE, COLUMNS, values, KEYS);
    }
}
