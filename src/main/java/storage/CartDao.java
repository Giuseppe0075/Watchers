package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface CartDao {
    public void addCart(CartBeen cart) throws SQLException;
    public void updateCart(CartBeen cart) throws SQLException;
    public CartBeen getCartById(Integer id) throws SQLException;
    public Collection<CartBeen> getAllCarts() throws SQLException;
    public void deleteCart(CartBeen cart) throws SQLException;
    public void deleteAllCarts() throws SQLException;
}
