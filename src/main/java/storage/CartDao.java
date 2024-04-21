package storage;

import java.sql.SQLException;
import java.util.Collection;

public interface CartDao {
    public void addCart(CartElementBeen cart) throws SQLException;
    public void updateCart(CartElementBeen cart) throws SQLException;
    public CartElementBeen getCartById(Integer id) throws SQLException;
    public Collection<CartElementBeen> getAllCarts() throws SQLException;
    public void deleteCart(CartElementBeen cart) throws SQLException;
}
