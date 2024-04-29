package storage;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import storage.model.DatabaseKey;
import storage.model.DatabaseObject;
import storage.model.DatabaseTable;

/*
CREATE TABLE `Watch`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `brand` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `reviews_avg` DOUBLE(8, 2) NOT NULL DEFAULT '0',
    `price` DOUBLE(8, 2) NOT NULL,
    `material` VARCHAR(255) NOT NULL,
    `stock` INT NOT NULL DEFAULT '0',
    `dimension` DOUBLE(8, 2) NOT NULL COMMENT 'in mm',
    `IVA` SMALLINT NOT NULL,
    `sex` ENUM('MAN', 'WOMEN', 'UNISEX') NOT NULL DEFAULT 'UNISEX',
    `visible` BOOLEAN DEFAULT TRUE,
    CONSTRAINT `watch_brand_foreign` FOREIGN KEY(`brand`) REFERENCES `Brand`(`business_name`)
);
 */
@DatabaseTable(tableName = "Watch")
public class WatchBean extends DatabaseObject {
    @DatabaseKey(keyName = "id")
    private Long id;
    private String name;
    private String brand;
    private String description;
    private Double reviews_avg;
    private Double price;
    private String material;
    private Integer stock;
    private Double dimension;
    private Integer IVA;
    private String sex;
    private Boolean visible;


    public WatchBean() {
        this.id = 0L;
    }

    public WatchBean(JsonObject obj){
        this.id = obj.get("id").getAsLong();
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.reviews_avg = reviews_avg;
        this.price = price;
        this.material = material;
        this.stock = stock;
        this.dimension = dimension;
        this.IVA = IVA;
        this.sex = sex;
        this.visible = visible;
    }

    public WatchBean(String name, String brand, String description, Double reviews_avg, Double price, String material, Integer stock, Double dimension, Integer IVA,String sex, Boolean visible) {
        //this.id = id;  // id è autoincrement - viene impostato a segiuto del salvataggio sul db
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.reviews_avg = reviews_avg;
        this.price = price;
        this.material = material;
        this.stock = stock;
        this.dimension = dimension;
        this.IVA = IVA;
        this.sex = sex;
        this.visible = visible;
    }

    public WatchBean(Long id,String name, String brand, String description, Double reviews_avg, Double price, String material, Integer stock, Double dimension, Integer IVA,String sex, Boolean visible) {
        this.id = id;  // utile per l'update
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.reviews_avg = reviews_avg;
        this.price = price;
        this.material = material;
        this.stock = stock;
        this.dimension = dimension;
        this.IVA = IVA;
        this.sex = sex;
        this.visible = visible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getReviews_avg() {
        return reviews_avg;
    }

    public void setReviews_avg(Double reviews_avg) {
        this.reviews_avg = reviews_avg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getDimension() {
        return dimension;
    }

    public void setDimension(Double dimension) {
        this.dimension = dimension;
    }

    public Integer getIVA() {
        return IVA;
    }

    public void setIVA(Integer IVA) {
        this.IVA = IVA;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

/*    public BufferedImage getImage() { return image; }

    public void setImage(BufferedImage image) { this.image = image; }*/


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WatchBean)) return false;

        WatchBean watchBean = (WatchBean) obj;

        if (!getId().equals(watchBean.getId())) return false;
        if (!getName().equals(watchBean.getName())) return false;
        if (!getBrand().equals(watchBean.getBrand())) return false;
        if (!getDescription().equals(watchBean.getDescription())) return false;
        if (!getReviews_avg().equals(watchBean.getReviews_avg())) return false;
        if (!getPrice().equals(watchBean.getPrice())) return false;
        if (!getMaterial().equals(watchBean.getMaterial())) return false;
        if (!getStock().equals(watchBean.getStock())) return false;
        if (!getDimension().equals(watchBean.getDimension())) return false;
        if (!getIVA().equals(watchBean.getIVA())) return false;
        if (!getSex().equals(watchBean.getSex())) return false;
        return getVisible().equals(watchBean.getVisible());
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return "WatchBeen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", reviews_avg=" + reviews_avg +
                ", price=" + price +
                ", material='" + material + '\'' +
                ", stock=" + stock +
                ", dimension=" + dimension +
                ", IVA=" + IVA +
                ", Sex='"+ sex + '\'' +
                ", Visible='"+ visible + '\'' +
                '}';
    }
}
