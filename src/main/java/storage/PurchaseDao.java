package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface PurchaseDao {
    public void addPurchase(PurchaseBeen purchase) throws SQLException;
    public void updatePurchase(PurchaseBeen purchase) throws SQLException;
    public PurchaseBeen getPurchaseById(Integer id) throws SQLException;
    public Collection<PurchaseBeen> getAllPurchases() throws SQLException;
    public void deletePurchase(PurchaseBeen purchase) throws SQLException;
}
