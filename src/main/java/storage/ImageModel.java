package storage;

import database.DatabaseConnectionPool;
import org.tinylog.Logger;

import java.sql.*;
import java.util.Collection;
import java.util.List;

public class ImageModel implements DAO<ImageBean> {
    @Override
    public void doSave(ImageBean image) throws SQLException, Exception{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            System.out.println("Creo connessione");
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement =  connection.prepareStatement("INSERT INTO Watch" + "(name, brand, description, reviews_avg, price, material, stock,dimension,IVA,sex,visible) values (?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, image.getId());
            preparedStatement.setInt(2, image.getWatch());
            preparedStatement.setBytes(3, image.getImage());
            System.out.println("Eseguo");
            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Image | Inserimento non eseguito | 0 righe modificate | Image: "+ image.toString());
            }
            System.out.printf("Inserimento avvenuto con successo!");
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
        database.Connection connection = null;
        ImageBean image = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection2();
            System.out.println("input: " + key[0].toString());
            ResultSet rs = connection.executeQuery("SELECT * FROM Image WHERE id = ? AND watch = ?",
                    List.of(Integer.parseInt((String) key[0]), Integer.parseInt((String) key[1])));
            System.out.println("Rs: " +rs.next());

            if(rs == null){
                throw new SQLException("Image | Query non riuscita. | id:"+ image.getId());// non va bene
            }
            System.out.println("Prova4");

            image = new ImageBean(rs.getInt("id"), rs.getInt("watch"), rs.getBytes("image"));
            System.out.println("Prova5");
        }
        catch (Exception e){
            Logger.error(e, "Failed to do the query");
        }
        finally {
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