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
    List<WatchBean> watches;
    private static final String TABLE = "watch";

    @Override
    public void doSave(WatchBean watch) throws SQLException, Exception {

        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {

            int result =  connection.executeUpdate("INSERT INTO Watch" + "(name, brand, description, reviews_avg, price, material, stock,dimension,IVA,sex,visible) values (?,?,?,?,?,?,?,?,?,?,?)",
                    List.of(watch.getName(), watch.getBrand(), watch.getDescription(), watch.getReviews_avg(), watch.getPrice(), watch.getMaterial(), watch.getStock(), watch.getDimension(), watch.getIVA(), watch.getSex(), watch.getVisible()));


            if(result == 0){
                throw new SQLException("Watch | Inserimento non eseguito | 0 righe modificate | Watch: "+ watch.toString());
            }
        } catch (SQLException e){
            System.out.println("Errore: "+ e.getMessage());
        }
    }

    @Override
    public void doSaveOrUpdate(WatchBean watch) throws SQLException, Exception {

        // prima verifica che non esista già
        WatchBean watchBean = this.doRetrieveByKey(watch.getId().intValue());
        if(watchBean.getId() == 0){
            this.doSave(watch);
            return;
        }

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
    public WatchBean doRetrieveByKey(Object... key) throws SQLException, Exception {
        if(key.length != 1) throw new Exception("WatchModel::doRetrieveByKey: Il numero di chiavi deve essere 1. Numero chiavi passate: " + key.length);


        WatchBean watch = new WatchBean();

        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()) {

            ResultSet rs = connection.executeQuery("SELECT * FROM Watch WHERE id = ?", List.of(key[0]));

            while (rs.next()){
                watch.setId(rs.getLong("id"));
                watch.setName(rs.getString("name"));
                watch.setBrand(rs.getString("brand"));
                watch.setDescription(rs.getString("description"));
                watch.setReviews_avg(rs.getDouble("reviews_avg"));
                watch.setPrice(rs.getDouble("price"));
                watch.setMaterial(rs.getString("material"));
                watch.setStock(rs.getInt("stock"));
                watch.setDimension(rs.getDouble("dimension"));
                watch.setIVA(rs.getInt("IVA"));
                watch.setSex(rs.getString("sex"));
                watch.setVisible(rs.getBoolean("visible"));
            }

        }catch (SQLException e){
            System.out.println("Errore: "+ e.getMessage());
        }


        return watch;
    }

    @Override
    public Collection<WatchBean> doRetrieveByCond(String cond) throws SQLException, Exception {
        List<WatchBean> watches = new ArrayList<>();


        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection();) {
           // WARNING SQL INJECTION !!!
            ResultSet rs = connection.executeQuery("SELECT * FROM Watch WHERE "+ cond);

            while(rs.next()) {
                WatchBean watch = new WatchBean();
                watch.setId(rs.getLong("id"));
                watch.setName(rs.getString("name"));
                watch.setBrand(rs.getString("brand"));
                watch.setDescription(rs.getString("description"));
                watch.setReviews_avg(rs.getDouble("reviews_avg"));
                watch.setPrice(rs.getDouble("price"));
                watch.setMaterial(rs.getString("material"));
                watch.setStock(rs.getInt("stock"));
                watch.setDimension(rs.getDouble("dimension"));
                watch.setIVA(rs.getInt("IVA"));
                watch.setSex(rs.getString("sex"));
                watch.setVisible(rs.getBoolean("visible"));
                watches.add(watch);
            }
        }
        catch (SQLException e){
            System.out.println("Errore: "+ e.getMessage());
        }

        return  watches;
    }

    @Override
    public Collection<WatchBean> doRetrieveAll() throws SQLException, Exception {
        List<WatchBean> watches = new ArrayList<>();



        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection(); ){

            ResultSet rs = connection.executeQuery("SELECT * FROM Watch");

            while(rs.next()){
                WatchBean watch = new WatchBean();
                watch.setId(rs.getLong("id"));
                watch.setName(rs.getString("name"));
                watch.setBrand(rs.getString("brand"));
                watch.setDescription(rs.getString("description"));
                watch.setReviews_avg(rs.getDouble("reviews_avg"));
                watch.setPrice(rs.getDouble("price"));
                watch.setMaterial(rs.getString("material"));
                watch.setStock(rs.getInt("stock"));
                watch.setDimension(rs.getDouble("dimension"));
                watch.setIVA(rs.getInt("IVA"));
                watch.setSex(rs.getString("sex"));
                watch.setVisible(rs.getBoolean("visible"));
                watches.add(watch);
            }

        }
        return watches;
    }

    @Override
    public void doDelete(WatchBean watch) throws SQLException {

        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection()){

            int rs = connection.executeUpdate("DELETE FROM Watch WHERE id = ?", List.of(watch.getId()));

            if(rs == 0){
                throw new SQLException("Watch | Cancellazione non eseguita | 0 righe modificate | Watch: "+ watch.toString());
            }
        }

    }
}
