package storage;


import database.DatabaseConnectionPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class AdminModel implements DAO<AdminBean>{
    @Override
    public void doSave(AdminBean entity) throws SQLException, Exception {


        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();){

            int rs = connection.executeUpdate("INSERT INTO Admin" + "(email, psw) values (?,?)", List.of(entity.getEmail(), entity.getPassword()));
            if(rs == 0){
                throw new SQLException("Admin | Inserimento non eseguito | 0 righe modificate | Admin: "+ entity.toString());
            }
        }
    }

    @Override
    public void doDelete(AdminBean entity) throws SQLException, Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();){
            int rs = connection.executeUpdate("DELETE FROM Admin WHERE id = ? AND email = ?", List.of(entity.getId(), entity.getEmail()));
            if(rs == 0){
                throw new SQLException("Admin | Cancellazione non eseguita | 0 righe modificate | Admin: "+ entity.toString());
            }
        }
    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public AdminBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        if(key.length != 2) throw new Exception("WatchModel::doRetrieveByKey: Il numero di chiavi deve essere 2. Numero chiavi passate: " + key.length);

        AdminBean admin = new AdminBean();

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();){

            ResultSet rs =  connection.executeQuery("SELECT * FROM Admin WHERE id = ? AND email = ?", List.of(key[0], key[1]));

            if(rs == null){
                throw new SQLException("Admin | Query non riuscita. | id:"+ admin.getId());
            }

            admin = new AdminBean(rs.getLong(0), rs.getString(1), rs.getString(2));
        }
        return null;
    }

    @Override
    public Collection<AdminBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }

    @Override
    public Collection<AdminBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(AdminBean entity) throws SQLException, Exception {

    }
}
