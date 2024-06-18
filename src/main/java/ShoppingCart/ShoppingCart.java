package ShoppingCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;
import storage.Models.CartElementModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    List<CartElementBean> shoppingCart;
    CartElementModel cartElementModel = new CartElementModel();
    HttpSession session;

    public ShoppingCart(HttpSession session){
        shoppingCart  = new ArrayList<>();
        this.session = session;
    }

    /**
     * From session
     */
    @SuppressWarnings("unchecked")
    public synchronized List<CartElementBean> getCart(){
        Object userIdObject = session.getAttribute("user");
        if(userIdObject != null){
            updateCart();
            Long userId = Long.parseUnsignedLong(String.valueOf(userIdObject));
            try {
                shoppingCart = (List<CartElementBean>) cartElementModel.doRetrieveByCond("WHERE user = ?", List.of(userId));
                session.setAttribute("cart", shoppingCart);
            } catch (Exception e) {
                Logger.warn(e.getMessage());
            }
        }
        else {
            if(session.getAttribute("cart") != null)
                shoppingCart = (List<CartElementBean>) session.getAttribute("cart");
        }
        return shoppingCart;
    }

    public void updateCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            CartElementBean old = shoppingCart.get(index);
            shoppingCart.remove(old);
            removeFromCart(old);
        }
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateCart();
    }

    public synchronized void sumCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            CartElementBean old = shoppingCart.get(index);
            shoppingCart.remove(old);
            cartElementBean.setQuantity(old.getQuantity() + cartElementBean.getQuantity());
        }
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateCart();
    }

    public synchronized void updateCart() {
        // Check if the user is logged
        Long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;

        // Get the cart from the session and update in database for every bean
        Collection<CartElementBean> temp = (List<CartElementBean>) session.getAttribute("cart"); // Create a copy of the shoppingCart
        if(temp == null) return;
        try {
            Collection<CartElementBean> temp2 = cartElementModel.doRetrieveByCond("WHERE user=?", List.of(user));
            if(temp.equals(temp2)) return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // If the user is logged, update the cart in the database
        if (user != 0) {
            // Update the cart in the database
            for (CartElementBean cartElementBean : temp) {
                try {
                    CartElementBean old = cartElementModel.doRetrieveByKey(List.of(user, cartElementBean.getWatch()));
                    cartElementBean.setUser(user);
                    if (old != null) {
                        cartElementBean.setQuantity(cartElementBean.getQuantity() + old.getQuantity());
                    }
                    cartElementModel.doSaveOrUpdate(cartElementBean);
                } catch (Exception e) {
                    Logger.warn(e.getMessage());
                }
            }
        }
        try {
            if(user != 0) {
                temp = cartElementModel.doRetrieveByCond("WHERE user=?", List.of(user));
            }
            session.setAttribute("cart", temp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void removeFromCart(CartElementBean cartElementBean){
        //Check if the user is logged
        Object userIdObject = session.getAttribute("user");
        shoppingCart = this.getCart();

        if(userIdObject != null){
            try {
                cartElementModel.doDelete(cartElementBean);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
        shoppingCart.remove(cartElementBean);
        session.setAttribute("cart", shoppingCart);

    }

}
