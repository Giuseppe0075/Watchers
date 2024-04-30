package storage;

import database.Connection;
import database.DatabaseConnectionPool;
import org.tinylog.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageModel implements DAO<ImageBean> {
    @Override
    public void doSave(ImageBean image) throws SQLException, Exception {

        try (database.Connection connection = DatabaseConnectionPool.getInstance().getConnection();) {
            int rs = connection.executeUpdate("INSERT INTO Watch" + "(id, watch, image) values (?,?,?)",
                    List.of(image.getId(),image.getWatch(), image.getImage()));

            if (rs == 0) {
                throw new SQLException("Image | Inserimento non eseguito | 0 righe modificate | Image: " + image.toString());
            }
            Logger.debug("Inserimento avvenuto con successo!");
        }
    }

    @Override
    public void doDelete(ImageBean image) throws SQLException, Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws SQLException, Exception {

    }

    @Override
    public ImageBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        //* Checks if the params are 2 ids: image.id and watch.id
        if(key.length != 2) throw new Exception("ImageBean::doRetrieveByKey: Il numero di chiavi deve essere 2. Numero chiavi passate: " + key.length);

        //Declaration

        ImageBean image = null;


        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection();){
            System.out.println("input: " + key[0].toString());
            ResultSet rs = connection.executeQuery("SELECT * FROM Image WHERE id = ? AND watch = ?",
                    List.of(Integer.parseInt((String) key[0]), Integer.parseInt((String) key[1])));
            System.out.println("Rs: " +rs.next());

            if(rs == null){
                throw new SQLException("Image | Query non riuscita. | id:"+ image.getId());// non va bene
            }
            System.out.println("Prova4");

            image = new ImageBean(rs.getInt("id"), rs.getInt("watch"), rs.getBytes("image"));
        }
        catch (Exception e){
            Logger.error(e, "Failed to do the query");
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
        //Declaration

        Collection<ImageBean> images = new ArrayList<>();


        try(Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {

            ResultSet rs = connection.executeQuery("SELECT * FROM Image WHERE " + cond);

            if(rs == null){
                throw new SQLException("Image | Query non riuscita. |");// non va bene
            }
            while(rs.next()) {
                ImageBean imageBean = new ImageBean(rs.getInt("id"), rs.getInt("watch"), rs.getBytes("image"));
                images.add(imageBean);
            }
        }
        catch (Exception e){
            Logger.error(e, "Failed to do the query");
        }

        return images;
    }


}