package storage;

import storage.model.DatabaseKey;
import storage.model.DatabaseObject;
import storage.model.DatabaseTable;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;

/*
CREATE TABLE `Image`(
    `id` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `image` BLOB NOT NULL,
    PRIMARY KEY(`id`, `watch`),
    CONSTRAINT `image_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);
 */
@DatabaseTable(tableName = "Image")
public class ImageBeen extends DatabaseObject {
    @DatabaseKey(keyName = "id")
    private Integer id;
    private Integer watch;
    private byte[] image;


    public ImageBeen() {
    }

    public ImageBeen(Integer id, Integer watch, byte[] image) {
        this.id = id;
        this.watch = watch;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWatch() {
        return watch;
    }

    public void setWatch(Integer watch) {
        this.watch = watch;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageBeen{" +
                "id=" + id +
                ", watch=" + watch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageBeen imageBeen = (ImageBeen) o;

        if (!id.equals(imageBeen.id)) return false;
        return  watch.equals(imageBeen.watch);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + watch.hashCode();
        return result;
    }
}
