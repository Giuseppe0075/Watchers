package storage;
/*
CREATE TABLE `Operation`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `admin` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `operation` VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT `operation_admin_foreign` FOREIGN KEY(`admin`) REFERENCES `Admin`(`id`),
    CONSTRAINT `operation_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);
 */
public class OperationBeen {
    private Integer id;
    private Integer admin;
    private Integer watch;
    private String operation;
    private String date;

    private OperationBeen() {
    }

    public OperationBeen(Integer id, Integer admin, Integer watch, String operation, String date) {
        this.id = id;
        this.admin = admin;
        this.watch = watch;
        this.operation = operation;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getWatch() {
        return watch;
    }

    public void setWatch(Integer watch) {
        this.watch = watch;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OperationBeen{" +
                "id=" + id +
                ", admin=" + admin +
                ", watch=" + watch +
                ", operation='" + operation + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperationBeen)) return false;

        OperationBeen that = (OperationBeen) o;

        if (!id.equals(that.id)) return false;
        if (!admin.equals(that.admin)) return false;
        if (!watch.equals(that.watch)) return false;
        if (!operation.equals(that.operation)) return false;
        return date.equals(that.date);
    }
}
