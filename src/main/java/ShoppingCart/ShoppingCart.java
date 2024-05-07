package ShoppingCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;
import storage.Models.CartElementModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart {
    List<CartElementBean> shoppingCart;
    CartElementModel cartElementModel = new CartElementModel();
    HttpSession session;

    public ShoppingCart(HttpSession session){
        this.session = session;
        shoppingCart  = new ArrayList<>();
    }

    /**
     * From session
     */
    @SuppressWarnings("unchecked")
    public synchronized List<CartElementBean> getCart(){
        Object userIdObject = session.getAttribute("user");
        if(userIdObject != null){
            Long userId = Long.parseUnsignedLong(String.valueOf(userIdObject));
            updateCart();
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
        shoppingCart = getCart();

        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            CartElementBean old = shoppingCart.get(index);
            shoppingCart.remove(old);

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
            old.setQuantity(10);
            shoppingCart.remove(old);
            cartElementBean.setQuantity(old.getQuantity() + cartElementBean.getQuantity());
        }
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateCart();
    }

    public synchronized void updateCart() {
        // Check if the user is logged
        Object userIdObject = session.getAttribute("user");

        // Get the cart from the session and update in database for every bean
        List<CartElementBean> temp = (List<CartElementBean>) session.getAttribute("cart"); // Create a copy of the shoppingCart
        if(temp == null) return;
        Iterator<CartElementBean> iterator = temp.iterator();
        while (iterator.hasNext()) {
            CartElementBean cartElementBean = iterator.next();
            if (cartElementBean.getQuantity() > 0) {
                if (userIdObject != null) {
                    try {
                        cartElementBean.setUser(Long.parseUnsignedLong(String.valueOf(userIdObject)));
                        cartElementModel.doSaveOrUpdate(cartElementBean);
                    } catch (Exception e) {
                        Logger.warn(e.getMessage());
                    }
                }
            } else {
                if (userIdObject != null) {
                    try {
                        cartElementBean.setUser(Long.parseUnsignedLong(String.valueOf(userIdObject)));
                        cartElementModel.doDelete(cartElementBean);
                    } catch (Exception e) {
                        Logger.error(e.getMessage());
                    }
                }
                iterator.remove(); // Safe removal using iterator
            }
        }
        // Update session attribute with the modified shoppingCart
        session.setAttribute("cart", temp);
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
