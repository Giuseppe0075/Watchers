package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface ImageDao {
    public void addImage(ImageBeen image) throws SQLException;
    public void updateImage(ImageBeen image) ;
    public ImageBeen getImageById(Integer id);
    public Collection<ImageBeen> getAllImages();
    public void deleteImage(ImageBeen image);
}
