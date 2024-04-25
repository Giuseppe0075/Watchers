package storage;


import database.DatabaseConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Collection;

public class AdminModel implements DAO<AdminBean>{
    @Override
    public void doSave(AdminBean entity) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Admin" + "(email, psw) values (?,?)");

            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getPassword());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Admin | Inserimento non eseguito | 0 righe modificate | Admin: "+ entity.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void doDelete(AdminBean entity) throws SQLException, Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM Admin WHERE id = ? AND email = ?");

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getEmail());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Admin | Cancellazione non eseguita | 0 righe modificate | Admin: "+ entity.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public AdminBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        if(key.length != 2) throw new Exception("WatchModel::doRetrieveByKey: Il numero di chiavi deve essere 2. Numero chiavi passate: " + key.length);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        AdminBean admin = new AdminBean();

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement =  connection.prepareStatement("SELECT * FROM Admin WHERE id = ? AND email = ?");

            preparedStatement.setLong(1, (Long)key[0]);
            preparedStatement.setString(2, (String)key[1]);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs == null){
                throw new SQLException("Admin | Query non riuscita. | id:"+ admin.getId());
            }

            admin = new AdminBean(rs.getLong(0), rs.getString(1), rs.getString(2));
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
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
