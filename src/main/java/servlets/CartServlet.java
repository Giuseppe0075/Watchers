package servlets;

import ShoppingCart.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;

import java.util.List;

import storage.Models.CartElementModel;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart-servlet")
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
        Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
        String action = String.valueOf(req.getParameter("action"));

        switch (action) {
            case "add": {
                CartElementModel cartElementModel = new CartElementModel();
                Integer quantity = Integer.parseInt(req.getParameter("quantity"));
                CartElementBean old = null;
                try {
                    List<CartElementBean> cart = (List<CartElementBean>) session.getAttribute("cart");
                    if(cart.contains(new CartElementBean(user, watch, 0))) {
                        old = cart.get(cart.indexOf(new CartElementBean(user, watch, 0)));
                        cart.remove(new CartElementBean(user, watch, 0));
                        quantity += old.getQuantity();
                    }
                } catch (Exception e) {
                    Logger.warn(e.getMessage());
                }
                CartElementBean cartElementBean = new CartElementBean(user, watch, quantity);
                shoppingCart.sumCartElementQuantity(cartElementBean);
                break;
            }
            case "update": {
                Integer quantity = Integer.parseInt(req.getParameter("quantity"));
                CartElementBean cartElementBean = new CartElementBean(user, watch, quantity);
                shoppingCart.updateCartElementQuantity(cartElementBean);
                break;
            }
            case "remove": {
                CartElementBean cartElementBean = new CartElementBean(user, watch, 0);
                shoppingCart.removeFromCart(cartElementBean);
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/cart/cart.jsp");
    }
}

