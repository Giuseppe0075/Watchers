package ShoppingCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;
import storage.Models.CartElementModel;

import java.util.ArrayList;
import java.util.Collection;
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
            updateSessionCart();
        }
        shoppingCart = session.getAttribute("cart") != null ? (List<CartElementBean>) session.getAttribute("cart") : new ArrayList<>();
        return shoppingCart;
    }

    public void updateCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            removeFromCart(shoppingCart.get(index));
        }
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateDatabaseCart(cartElementBean.getUser());
    }

    public synchronized void sumCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            CartElementBean old = shoppingCart.get(index);
            cartElementBean.setQuantity(old.getQuantity() + cartElementBean.getQuantity());
            removeFromCart(old);
        }
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateDatabaseCart(cartElementBean.getUser());
    }

    public synchronized void updateSessionCart() {
        try{
            long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
            if(user == 0) return;
            Collection<CartElementBean> cartElementBeans = cartElementModel.doRetrieveByCond("WHERE user = ?", List.of(user));
            session.setAttribute("cart", cartElementBeans);
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void updateDatabaseCart(Long user) {
        //Check if the user is logged
        if(user == 0) return;

        //Take the cart from the session
        Collection<CartElementBean> sessionCart = (List<CartElementBean>) session.getAttribute("cart");

        //If it's null, nothing to merge
        if (sessionCart == null) return;

        try {
            //For every element in the session cart, update the database
            for (CartElementBean cartElementBean : sessionCart) {
                cartElementModel.doSaveOrUpdate(cartElementBean);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void mergeCarts(Long user){
        //Check if the user is logged
        if(user == 0) return;

        //Take the cart from the session
        Collection<CartElementBean> sessionCart = (List<CartElementBean>) session.getAttribute("cart");

        //If it's null, nothing to merge
        if (sessionCart == null) return;

        try {
            //For every element in the session cart, update the database
            for (CartElementBean cartElementBean : sessionCart) {
                CartElementBean databaseCartElement = cartElementModel.doRetrieveByKey(List.of(user, cartElementBean.getWatch()));
                cartElementBean.setUser(user);
                if(databaseCartElement != null){
                    cartElementBean.setQuantity(databaseCartElement.getQuantity() + cartElementBean.getQuantity());
                }
                cartElementModel.doSaveOrUpdate(cartElementBean);
            }

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
