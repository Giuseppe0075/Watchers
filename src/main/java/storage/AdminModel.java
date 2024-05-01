package storage;


import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminModel implements DAO<AdminBean>{
    private static final String TABLE = "Admin";
    private static final List<String> columns = List.of("email", "psw");
    @Override
    public void doSave(AdminBean entity) throws Exception {
        List<Object> values = List.of(entity.getEmail(), entity.getPsw());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(AdminBean entity) throws Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();){
            int rs = connection.executeUpdate("DELETE FROM Admin WHERE id = ? AND email = ?", List.of(entity.getId(), entity.getEmail()));
            if(rs == 0){
                throw new SQLException("Admin | Cancellazione non eseguita | 0 righe modificate | Admin: "+ entity.toString());
            }
        }
    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public AdminBean doRetrieveByKey(List<Object> keys) throws Exception {
        if (keys.size() != 1) throw new SQLException("Admin | doRetrieveByKey: Failed | The number of keys is not 1");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("id"), keys);
        return new AdminBean(rs);
    }

    @Override
    public Collection<AdminBean> doRetrieveByCond(String cond) throws Exception {
        List<AdminBean> admins = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while (rs.next()) {
            admins.add(new AdminBean(rs));
        }
        return admins;
    }

    @Override
    public Collection<AdminBean> doRetrieveAll() throws Exception {
        List<AdminBean> admins = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while (rs.next()) {
            admins.add(new AdminBean(rs));
        }
        return admins;
    }

    @Override
    public void doSaveOrUpdate(AdminBean entity) throws Exception {

    }
}
