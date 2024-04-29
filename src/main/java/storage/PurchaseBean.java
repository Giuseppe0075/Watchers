package storage;
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
public class PurchaseBean {
    private  Integer id;
    private  Integer user;
    private  Integer watch;
    private  Integer quantity;
    private  Integer IVA;
    private  Double price;

    private PurchaseBean() {
    }

    public PurchaseBean(Integer id, Integer user, Integer watch, Integer quantity, Integer IVA, Double price) {
        this.id = id;
        this.user = user;
        this.watch = watch;
        this.quantity = quantity;
        this.IVA = IVA;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setIVA(Integer IVA) {
        this.IVA = IVA;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
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

    public Integer getIVA() {
        return IVA;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PurchaseBeen{" +
                "id=" + id +
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

        if (!getId().equals(that.getId())) return false;
        if (!getUser().equals(that.getUser())) return false;
        if (!getWatch().equals(that.getWatch())) return false;
        if (!getQuantity().equals(that.getQuantity())) return false;
        if (!getIVA().equals(that.getIVA())) return false;
        return getPrice().equals(that.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUser().hashCode();
        result = 31 * result + getWatch().hashCode();
        result = 31 * result + getQuantity().hashCode();
        result = 31 * result + getIVA().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }
}
