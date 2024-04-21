package storage;

import java.util.Collection;

public interface ImageDao {
    public void addImage(ImageBeen image);
    public void updateImage(ImageBeen image);
    public ImageBeen getImageById(Integer id);
    public Collection<ImageBeen> getAllImages();
    public void deleteImage(ImageBeen image);
}
