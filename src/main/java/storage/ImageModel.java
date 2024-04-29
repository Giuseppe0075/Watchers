package storage;

import database.DatabaseConnectionPool;

import java.sql.*;
import java.util.Collection;

public class ImageModel implements DAO<ImageBean> {
    @Override
    public void doSave(ImageBean image) throws SQLException, Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement =  connection.prepareStatement("INSERT INTO Watch" + "(name, brand, description, reviews_avg, price, material, stock,dimension,IVA,sex,visible) values (?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, image.getId());
            preparedStatement.setInt(2, image.getWatch());
            preparedStatement.setBytes(3, image.getImage());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Image | Inserimento non eseguito | 0 righe modificate | Image: "+ image.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void doDelete(ImageBean image) throws SQLException, Exception {

    }

    @Override
    public ImageBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        //* Checks if the params are 2 ids: image.id and watch.id
        if(key.length != 2) throw new Exception("ImageBean::doRetrieveByKey: Il numero di chiavi deve essere 2. Numero chiavi passate: " + key.length);

        //Declaration
        Connection connection = null;
        ImageBean image = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement =  connection.prepareStatement("SELECT * FROM Image WHERE id = ? AND watch = ?");

            preparedStatement.setInt(1, (Integer)key[0]);
            preparedStatement.setInt(2, (Integer)key[1]);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs == null){
                throw new SQLException("Image | Query non riuscita. | id:"+ image.getId());// non va bene
            }

            image = new ImageBean(rs.getInt(0), rs.getInt(1), rs.getBytes(2));

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
        return image;
    }

    @Override
    public Collection<ImageBean> doRetrieveAll() throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(ImageBean entity) throws SQLException, Exception {

    }

    @Override
    public Collection<ImageBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        return null;
    }


}