package storage;

import database.DatabaseConnectionPool;

import java.sql.*;
import database.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*
CREATE TABLE `Watch`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
    `visible` TINYINT(1) NOT NULL DEFAULT '1',
    CONSTRAINT `watch_brand_foreign` FOREIGN KEY(`brand`) REFERENCES `Brand`(`business_name`)
);
*/
public class WatchModel implements DAO<WatchBean>{
    private static final String TABLE = "Watch";
    private static final List<String> columns = List.of("name", "brand","description", "reviews_avg", "price", "material", "stock", "dimension","IVA","sex","visible");
    @Override
    public void doSave(WatchBean watch) throws SQLException, Exception {
        List<Object> values = List.of(watch.getName(), watch.getBrand(), watch.getDescription(), watch.getReviews_avg(), watch.getPrice(),
                watch.getMaterial(), watch.getStock(), watch.getDimension(), watch.getIVA(), watch.getSex(), watch.getVisible());
        Model.doSave(TABLE, values, columns);
    }

    @Override
    public void doSaveOrUpdate(WatchBean watch) throws Exception {

        // prima verifica che non esista già
//        WatchBean watchBean = this.doRetrieveByKey(watch.getId().intValue());
//        if(watchBean.getId() == 0){
//            this.doSave(watch);
//            return;
//        }

        PreparedStatement preparedStatement = null;
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection();) {
            int rs = connection.executeUpdate("UPDATE Watch SET name = ?, brand = ?, description = ?, reviews_avg = ?, price = ?, material = ?, stock = ?, dimension = ?, IVA = ?, sex = ?, visible = ? WHERE id = ?",
                    List.of(watch.getName(), watch.getBrand(), watch.getDescription(), watch.getReviews_avg(), watch.getPrice(),
                            watch.getMaterial(), watch.getStock(), watch.getDimension(), watch.getIVA(), watch.getVisible(), watch.getId()));

            if(rs == 0){
                throw new SQLException("Watch | Aggiornamento non eseguito | 0 righe modificate | Watch: "+ watch.toString());
            }
        }
    }

    @Override
    public WatchBean doRetrieveByKey(List<Object> keys) throws Exception {
        if(keys.size() != 1) throw new Exception("Watch | doRetrieveByKey: Failed | The number of keys is not 1.");
        ResultSet rs = Model.doRetrieveByKey(TABLE,List.of("id"), keys);

        return new WatchBean(rs);
    }

    @Override
    public Collection<WatchBean> doRetrieveByCond(String cond) throws Exception {
        List<WatchBean> watches = new ArrayList<>();
        ResultSet rs = Model.doRetrieveByCond(TABLE, cond);
        while(rs.next()){
            watches.add(new WatchBean(rs));
        }

        return watches;
    }

    @Override
    public Collection<WatchBean> doRetrieveAll() throws Exception {
        List<WatchBean> watches = new ArrayList<>();
        ResultSet rs = Model.doRetrieveAll(TABLE);
        while(rs.next()){
            watches.add(new WatchBean(rs));
        }
        return watches;
    }

    @Override
    public void doDelete(WatchBean watch) throws Exception {

        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            int rs = connection.executeUpdate("DELETE FROM Watch WHERE id = ?", List.of(watch.getId()));

            if(rs == 0){
                throw new SQLException("Watch | Cancellazione non eseguita | 0 righe modificate | Watch: "+ watch.toString());
            }
        }

    }

    @Override
    public void doDeleteByCond(String cond) throws Exception {
        Model.doDeleteByCond(TABLE, cond);
    }
}
