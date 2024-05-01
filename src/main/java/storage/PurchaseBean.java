package storage;

import java.sql.ResultSet;

/*
CREATE TABLE `Purchase`(
    `id_order` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
    `IVA` SMALLINT NOT NULL,
    `price` DOUBLE(8, 2) NOT NULL,
    PRIMARY KEY(`id_order`, `user`, `watch`),
    CONSTRAINT `purchase_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `purchase_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);
 */
public class PurchaseBean extends Bean{
    private  Long id_order;
    private  Long user;
    private  Long watch;
    private  Integer quantity;
    private  Integer IVA;
    private  Double price;

    public PurchaseBean(Long id_order, Long user, Long watch, Integer quantity, Integer IVA, Double price) {
        this.id_order = id_order;
        this.user = user;
        this.watch = watch;
        this.quantity = quantity;
        this.IVA = IVA;
        this.price = price;
    }

    public PurchaseBean(ResultSet rs){
        super(rs);
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public void setWatch(Long watch) {
        this.watch = watch;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setIVA(Integer IVA) {
        this.IVA = IVA;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId_order() {
        return id_order;
    }

    public Long getUser() {
        return user;
    }

    public Long getWatch() {
        return watch;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getIVA() {
        return IVA;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PurchaseBeen{" +
                "id=" + id_order +
                ", user=" + user +
                ", watch=" + watch +
                ", quantity=" + quantity +
                ", IVA=" + IVA +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PurchaseBean)) return false;

        PurchaseBean that = (PurchaseBean) o;

        if (!getId_order().equals(that.getId_order())) return false;
        if (!getUser().equals(that.getUser())) return false;
        if (!getWatch().equals(that.getWatch())) return false;
        if (!getQuantity().equals(that.getQuantity())) return false;
        if (!getIVA().equals(that.getIVA())) return false;
        return getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getId_order().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getWatch().hashCode();
        result = 31 * result + getQuantity().hashCode();
        result = 31 * result + getIVA().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
