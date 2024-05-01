package storage;

import java.sql.ResultSet;

/*
    CREATE TABLE `Cart`(
        `user` BIGINT UNSIGNED NOT NULL,
        `watch` BIGINT UNSIGNED NOT NULL,
        `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
        PRIMARY KEY(`user`, `watch`),
        CONSTRAINT `cart_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
        CONSTRAINT `cart_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
    );
 */
public class CartElementBean extends Bean{
    private  Integer user;
    private  Integer watch;
    private  Integer quantity;

    public CartElementBean(ResultSet rs){
        super(rs);
    }

    public CartElementBean(Integer user, Integer watch, Integer quantity) {
        this.user = user;
        this.watch = watch;
        this.quantity = quantity;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public void setWatch(Integer watch) {
        this.watch = watch;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUser() {
        return user;
    }

    public Integer getWatch() {
        return watch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "CartBeen{" +
                "user=" + user +
                ", watch=" + watch +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartElementBean)) return false;

        CartElementBean that = (CartElementBean) o;

        if (!user.equals(that.user)) return false;
        if (!watch.equals(that.watch)) return false;
        return quantity.equals(that.quantity);
    }
}
