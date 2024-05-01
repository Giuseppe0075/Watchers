package storage;

import java.sql.ResultSet;
import java.util.Date;

/*
CREATE TABLE `Review`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `stars` SMALLINT UNSIGNED NOT NULL,
    `description` VARCHAR(255) NULL,
    `date` DATE NOT NULL,
    PRIMARY KEY(`watch`, `user`),
    CONSTRAINT `review_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `review_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);
 */
public class ReviewBean extends Bean{
    private Long watch;
    private Long user;
    private Integer stars;
    private String description;
    private Date date;

    public ReviewBean(ResultSet rs) {
        super(rs);
    }

    public ReviewBean(Long watch, Long user, Integer stars, String description, Date date) {
        this.watch = watch;
        this.user = user;
        this.stars = stars;
        this.description = description;
        this.date = date;
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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewBeen{" +
                "watch=" + watch +
                ", user=" + user +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReviewBean)) return false;

        ReviewBean that = (ReviewBean) o;

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
