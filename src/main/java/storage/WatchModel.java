package storage;

import database.DatabaseConnectionPool;

import java.sql.*;
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
public class WatchModel implements WatchDao{
    List<WatchBeen> watches;
    private static final String TABLE = "watch";

    @Override
    public void addWatch(WatchBeen watch) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();

            preparedStatement =  connection.prepareStatement("INSERT INTO Watch" + "(name, brand, description, reviews_avg, price, material, stock,dimension,IVA,sex,visible) values (?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, watch.getName());
            preparedStatement.setString(2, watch.getBrand());
            preparedStatement.setString(3, watch.getDescription());
            preparedStatement.setDouble(4, watch.getReviews_avg());
            preparedStatement.setDouble(5, watch.getPrice());
            preparedStatement.setString(6, watch.getMaterial());
            preparedStatement.setInt(7, watch.getStock());
            preparedStatement.setDouble(8, watch.getDimension());
            preparedStatement.setInt(9, watch.getIVA());
            preparedStatement.setString(10, watch.getSex());
            preparedStatement.setInt(11, watch.getVisible());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Watch | Inserimento non eseguito | 0 righe modificate | Watch: "+ watch.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void updateWatch(WatchBeen watch) throws SQLException {

        PreparedStatement preparedStatement = null;
        try (Connection connection = DatabaseConnectionPool.getInstance().getConnection();) {

            preparedStatement = connection.prepareStatement("UPDATE Watch SET name = ?, brand = ?, description = ?, reviews_avg = ?, price = ?, material = ?, stock = ?, dimension = ?, IVA = ?, sex = ?, visible = ? WHERE id = ?");

            preparedStatement.setInt(12, watch.getId());

            preparedStatement.setString(1, watch.getName());
            preparedStatement.setString(2, watch.getBrand());
            preparedStatement.setString(3, watch.getDescription());
            preparedStatement.setDouble(4, watch.getReviews_avg());
            preparedStatement.setDouble(5, watch.getPrice());
            preparedStatement.setString(6, watch.getMaterial());
            preparedStatement.setInt(7, watch.getStock());
            preparedStatement.setDouble(8, watch.getDimension());
            preparedStatement.setInt(9, watch.getIVA());
            preparedStatement.setString(10, watch.getSex());
            preparedStatement.setInt(11, watch.getVisible());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Watch | Aggiornamento non eseguito | 0 righe modificate | Watch: "+ watch.toString());
            }
        }
    }

    @Override
    public WatchBeen getWatchById(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        WatchBeen watch = new WatchBeen();

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Watch WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                watch.setId(rs.getInt("id"));
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
                watch.setVisible(rs.getInt("visible"));
            }

        }catch (SQLException e){
            System.out.println("Errore: "+ e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }


        return watch;
    }

    @Override
    public Collection<WatchBeen> getAllWatches() throws SQLException {
        List<WatchBeen> watches = new ArrayList<WatchBeen>();

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM watch");

            java.sql.ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                WatchBeen watch = new WatchBeen();
                watch.setId(rs.getInt("id"));
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
                watch.setVisible(rs.getInt("visible"));
                watches.add(watch);
            }

            if(watches.isEmpty() ){
                throw new SQLException("Watch | Nessun elemento trovato");
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } finally {
                DatabaseConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return watches;
    }

    @Override
    public void deleteWatch(WatchBeen watch) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM watch WHERE id = ?");
            preparedStatement.setInt(1, watch.getId());

            int rs = preparedStatement.executeUpdate();
            if(rs == 0){
                throw new SQLException("Watch | Cancellazione non eseguita | 0 righe modificate | Watch: "+ watch.toString());
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }

    }

    @Override
    public void deleteAllWatches() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnectionPool.getInstance().getConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM Watch where id > 0");

            int rs = preparedStatement.executeUpdate();
            if (rs == 0) {
                throw new SQLException("Watch | Cancellazione non eseguita | 0 righe modificate");
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnectionPool.getInstance().releaseConnection(connection);
        }
    }
}
