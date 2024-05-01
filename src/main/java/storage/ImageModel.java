package storage;

import database.Connection;
import database.DatabaseConnectionPool;
import org.tinylog.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageModel implements DAO<ImageBean> {
    private static final String TABLE = "Image";
    private static final List<String> columns = List.of("id", "watch", "image");
    @Override
    public void doSave(ImageBean image) throws SQLException, Exception {
        List<Object> values = List.of(image.getId(), image.getWatch(), image.getImage());
        Model.doSave(TABLE, values, columns);
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
        if(key.length != 2) throw new Exception("Image | doRetrieveByKey: Failed | The number of keys is not 2.");

        //Declaration
        ImageBean image = null;

        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection();){
            ResultSet rs = connection.executeQuery("SELECT * FROM Image WHERE id = ? AND watch = ?",
                    List.of(Integer.parseInt((String) key[0]), Integer.parseInt((String) key[1])));
            if(rs == null){
                throw new SQLException("Image | doRetrieveByKey: Failed. | key[0]:" + key[0] + " key[1]:" + key[1]);
            }

            image = new ImageBean(rs.getInt("id"), rs.getInt("watch"), rs.getBytes("image"));
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