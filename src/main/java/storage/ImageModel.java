package storage;

import database.DatabaseConnectionPool;

import java.awt.*;
import java.sql.*;
import java.util.Collection;

public class ImageModel implements DAO<ImageBeen> {
    @Override
    public void doSave(ImageBeen image) throws SQLException, Exception{
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
    public void doDelete(ImageBeen image) throws SQLException, Exception {

    }

    @Override
    public ImageBeen doRetrieveByKey(String... key) throws SQLException, Exception {
        //* Checks if the params are 2 ids: image.id and watch.id
        if(key.length != 2) throw new Exception("ImageBean::doRetrieveByKey: Il numero di chiavi deve essere 2. Numero chiavi passate: " + key.length);

        //Declaration
        Connection connection = null;
        ImageBeen image = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement =  connection.prepareStatement("SELECT * FROM image WHERE id = ? AND watch = ?");

            preparedStatement.setInt(1, Integer.parseInt(key[0]));
            preparedStatement.setInt(2, Integer.parseInt(key[1]));

            ResultSet rs = preparedStatement.executeQuery();
            if(rs == null){
                throw new SQLException("Image | Query non riuscita. | id:"+ image.getId());
            }

            image = new ImageBeen(rs.getInt(0), rs.getInt(1), rs.getBytes(2));

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
        return image;
    }

    @Override
    public Collection<ImageBeen> doRetrieveAll(String order) throws SQLException, Exception {
        return null;
    }

    @Override
    public void doSaveOrUpdate(ImageBeen entity) throws SQLException, Exception {

    }

    @Override
    public Collection<ImageBeen> doRetriveByCond(String cond) throws SQLException, Exception {
        return null;
    }


}