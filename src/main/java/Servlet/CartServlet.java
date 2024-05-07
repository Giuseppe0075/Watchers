package Servlet;

import ShoppingCart.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;

import java.util.List;

import storage.Models.CartElementModel;
import java.io.IOException;

public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = new ShoppingCart(session);
        Long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;

        String action = String.valueOf(req.getParameter("action"));
        if(action.equals("add")){
            CartElementModel cartElementModel = new CartElementModel();
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
            CartElementBean old;
            try{
                old = cartElementModel.doRetrieveByKey(List.of(user,watch));
                if(old != null)
                    quantity += old.getQuantity();
            }catch(Exception e){
                Logger.warn(e.getMessage());
            }
            CartElementBean cartElementBean = new CartElementBean(user, watch, quantity);
            shoppingCart.sumCartElementQuantity(cartElementBean);
        }
        else if(action.equals("update")){
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
            CartElementBean cartElementBean = new CartElementBean(user, watch, quantity);
            shoppingCart.updateCartElementQuantity(cartElementBean);
        }
        else if(action.equals("remove")){
            Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
            CartElementBean cartElementBean = new CartElementBean(user, watch, 0);
            shoppingCart.removeFromCart(cartElementBean);
        }
        List<CartElementBean> cart = shoppingCart.getCart();

        session.setAttribute("cart",cart);
        req.getRequestDispatcher("/cart/cart.jsp").forward(req, resp);
    }
}

