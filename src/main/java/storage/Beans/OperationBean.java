package storage.Beans;

import java.sql.Date;
import java.sql.ResultSet;/*
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
public class OperationBean extends Bean{
    private Long id;
    private Long admin;
    private Long watch;
    private String operation;
    private Date date;

    private OperationBean() {
    }

    public OperationBean(ResultSet rs) {
        super(rs);
    }

    public OperationBean(Long id,Long admin,Long watch, String operation,Date date) {
        this.id = id;
        this.admin = admin;
        this.watch = watch;
        this.operation = operation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdmin() {
        return admin;
    }

    public void setAdmin(Long admin) {
        this.admin = admin;
    }

    public Long getWatch() {
        return watch;
    }

    public void setWatch(Long watch) {
        this.watch = watch;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        if (!(o instanceof OperationBean)) return false;

        OperationBean that = (OperationBean) o;

        if (!id.equals(that.id)) return false;
        if (!admin.equals(that.admin)) return false;
        if (!watch.equals(that.watch)) return false;
        if (!operation.equals(that.operation)) return false;
        return date.equals(that.date);
    }
}
