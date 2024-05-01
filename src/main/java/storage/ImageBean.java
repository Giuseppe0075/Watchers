package storage;

import java.sql.ResultSet;

/*
CREATE TABLE `Image`(
    `id` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `image` BLOB NOT NULL,
    PRIMARY KEY(`id`, `watch`),
    CONSTRAINT `image_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);
 */
public class ImageBean extends Bean{
    private Long id;
    private Long watch;
    private byte[] image;

    public ImageBean(Long id, Long watch, byte[] image) {
        this.id = id;
        this.watch = watch;
        this.image = image;
    }

    public ImageBean(ResultSet rs){
        super(rs);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWatch() {
        return watch;
    }

    public void setWatch(Long watch) {
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

        ImageBean imageBean = (ImageBean) o;

        if (!id.equals(imageBean.id)) return false;
        return  watch.equals(imageBean.watch);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + watch.hashCode();
        return result;
    }
}
