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
    public void doSave(ImageBean image) throws Exception {
        List<Object> values = List.of(image.getId(), image.getWatch(), image.getImage());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doDelete(ImageBean image) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public ImageBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new Exception("Image | doRetrieveByKey: Failed | The number of keys is not 2.");
        ResultSet rs = Model.doRetrieveByKey(TABLE, List.of("id", "watch"), keys);
        return new ImageBean(rs);
    }
    @Override
    public Collection<ImageBean> doRetrieveByCond(String cond) throws Exception {
        List<ImageBean> images = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()) {
            images.add(new ImageBean(rs));
        }
        return images;
    }

    @Override
    public Collection<ImageBean> doRetrieveAll() throws Exception {
        List<ImageBean> images = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()) {
            images.add(new ImageBean(rs));
        }
        return images;
    }

    @Override
    public void doSaveOrUpdate(ImageBean entity) throws Exception {

    }


}