package storage;
/*
CREATE TABLE `Brand`(
    `business_name` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY(`business_name`)
);
 */
public class BrandBean {
    private String business_name;
    private String name;
    private String description;

    private BrandBean() {
    }

    public BrandBean(String business_name, String name, String description) {
        this.business_name = business_name;
        this.name = name;
        this.description = description;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BrandBeen{" +
                "business_name='" + business_name + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrandBean)) return false;

        BrandBean brandBean = (BrandBean) o;

        if (!getBusiness_name().equals(brandBean.getBusiness_name())) return false;
        if (!getName().equals(brandBean.getName())) return false;
        return getDescription().equals(brandBean.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getBusiness_name().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        return result;
    }
}
