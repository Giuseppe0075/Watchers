package storage;


import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminModel implements DAO<AdminBean>{
    private static final String TABLE = "Admin";
    private static final List<String> COLUMNS = List.of("email", "psw");
    private static final List<String> KEYS = List.of("id");
    @Override
    public void doSave(AdminBean entity) throws Exception {
        List<Object> values = List.of(entity.getEmail(), entity.getPsw());
        Model.doSave(TABLE, values, COLUMNS);
    }

    @Override
    public void doDelete(AdminBean adminBean) throws Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();){
            int rs = connection.executeUpdate("DELETE FROM Admin WHERE id = ? AND email = ?", List.of(adminBean.getId(), adminBean.getEmail()));
            if(rs == 0){
                throw new SQLException("Admin | doDelete: Failed | admin: " + adminBean);
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
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
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
    public void doSaveOrUpdate(AdminBean adminBean) throws Exception {
        if (adminBean.getId() == 0) {
            this.doSave(adminBean);
            return;
        }
        List<Object> values = List.of(adminBean.getEmail(), adminBean.getPsw(), adminBean.getId());
        Model.doUpdate(TABLE, COLUMNS, values, KEYS);
    }
}
