package storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageModel implements DAO<ImageBean> {
    private static final String TABLE = "Image";
    private static final List<String> COLUMNS = List.of("id", "watch", "image");
    private static final List<String> KEYS = List.of("id", "watch");
    @Override
    public void doSave(ImageBean imageBean) throws Exception {
        List<Object> values = List.of(imageBean.getId(), imageBean.getWatch(), imageBean.getImage());
        Model.doSave(TABLE, COLUMNS, values);
    }

    @Override
    public void doDelete(ImageBean imageBean) throws Exception {

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }

    @Override
    public ImageBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 2) throw new Exception("Image | doRetrieveByKey: Failed | The number of keys is not 2.");
        ResultSet rs = Model.doRetrieveByKey(TABLE, KEYS, keys);
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
    public void doSaveOrUpdate(ImageBean imageBean) throws Exception {
        if (imageBean.getId() == 0 || imageBean.getWatch() == 0) {
            this.doSave(imageBean);
            return;
        }
        List<Object> values = List.of(imageBean.getImage(), imageBean.getId(), imageBean.getWatch());
        Model.doUpdate(TABLE, COLUMNS.subList(2,COLUMNS.size()), values, KEYS);
    }


}