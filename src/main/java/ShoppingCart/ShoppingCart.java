package ShoppingCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import Model.Beans.CartElementBean;
import Model.Models.CartElementModel;

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
     * Get the cart from the session.
     * If the user is logged, firstly updates the cart in the session with the one in the database.
     * If the cart is null, creates a new one.
     * @return List<CartElementBean> of cart elements
     */
    @SuppressWarnings("unchecked")
    public synchronized List<CartElementBean> getCart(){
        //Check if the user is logged
        Object userIdObject = session.getAttribute("user");
        if(userIdObject != null){
            //If the user is logged, update the session cart with the one in the database
            updateSessionCart();
        }
        //Take the cart from the session or create a new one
        shoppingCart = session.getAttribute("cart") != null ? (List<CartElementBean>) session.getAttribute("cart") : new ArrayList<>();
        return shoppingCart;
    }

    /**
     *  Modifies the quantity of a watch in the cart in the session and in the database.
     */
    public void updateCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();

        //Check if the cartElementBean is already in the cart
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            //If it's already in the cart, remove the old element
            removeFromCart(shoppingCart.get(index));
        }

        //Add the updated element to the cart
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateDatabaseCart(cartElementBean.getUser());
    }

    /**
     * Adds 1 to the quantity of a watch in the cart in the session and in the database.
     * If the watch isn't already in the cart, it adds it.
     */
    public synchronized void sumCartElementQuantity(CartElementBean cartElementBean){
        shoppingCart = this.getCart();

        //Check if the cartElementBean is already in the cart
        int index = shoppingCart.indexOf(cartElementBean);
        if(index != -1) {
            //If it's already in the cart, sum the quantity and remove the old element
            CartElementBean old = shoppingCart.get(index);
            cartElementBean.setQuantity(old.getQuantity() + cartElementBean.getQuantity());
            removeFromCart(old);
        }
        //Add the new element to the cart
        shoppingCart.add(cartElementBean);
        session.setAttribute("cart", shoppingCart);
        this.updateDatabaseCart(cartElementBean.getUser());
    }

    /**
     * Updates the cart in the session with the one in the database.
     */
    public synchronized void updateSessionCart() {
        try{
            //Check if the user is logged
            long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
            if(user == 0) return;

            //Take the cart from the database
            Collection<CartElementBean> cartElementBeans = cartElementModel.doRetrieveByCond("WHERE user = ?", List.of(user));

            //Update the session cart
            session.setAttribute("cart", cartElementBeans);
        }catch (Exception e){
            Logger.error(e.getMessage());
        }
    }

    /**
     * Updates the database cart with the one in the session.
     */
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

    /**
     * Merges the cart in the session with the one in the database.
     * "Merge" means add all the elements in the session cart to the database cart.
     * If the element is already in the database, it sums the quantity.
     */
    @SuppressWarnings("unchecked")
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
                //If the element is already in the database, sum the quantity
                if(databaseCartElement != null){
                    cartElementBean.setQuantity(databaseCartElement.getQuantity() + cartElementBean.getQuantity());
                }
                cartElementBean.setUser(user);
                cartElementModel.doSaveOrUpdate(cartElementBean);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a watch from the cart in the session and in the database.
     */
    public synchronized void removeFromCart(CartElementBean cartElementBean){
        Object userIdObject = session.getAttribute("user");

        //Take the cart from the session
        shoppingCart = this.getCart();

        //Check if the user is logged
        if(userIdObject != null){
            try {
                //If the user is logged, remove the element from the database
                cartElementModel.doDelete(cartElementBean);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }

        //Remove the element from the session cart
        shoppingCart.remove(cartElementBean);

        //Update the session cart
        session.setAttribute("cart", shoppingCart);
    }
}