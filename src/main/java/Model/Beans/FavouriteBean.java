package Model.Beans;

import java.sql.ResultSet;

/*
CREATE TABLE `Favourite`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY(`watch`, `user`),
    CONSTRAINT `favourite_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `favourite_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

 */
public class FavouriteBean extends Bean{
    private Long watch;
    private Long user;

    public FavouriteBean(ResultSet rs){
        super(rs);
    }

    public FavouriteBean(Long watch, Long user) {
        this.watch = watch;
        this.user = user;
    }

    public Long getWatch() {
        return watch;
    }

    public void setWatch(Long watch) {
        this.watch = watch;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "FavouriteBeen{" +
                "watch=" + watch +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavouriteBean)) return false;

        FavouriteBean that = (FavouriteBean) o;

        if (!watch.equals(that.watch)) return false;
        return user.equals(that.user);
    }

    @Override
    public int hashCode() {
        int result = watch.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
