package Control;

import ShoppingCart.ShoppingCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.Beans.CartElementBean;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart-servlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        ShoppingCart shoppingCart = new ShoppingCart(session);

        Long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
        String action = String.valueOf(req.getParameter("action"));

        switch (action) {
            //Called on login to merge the session cart with the user's cart
            case "merge":
                if(user != 0) {
                    shoppingCart.mergeCarts(user);
                }
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            //Called to add one quantity of a watch in the cart
            case "add": {
                Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
                CartElementBean cartElementBean = new CartElementBean(user, watch, 1);
                shoppingCart.sumCartElementQuantity(cartElementBean);
                break;
            }
            //Called to update the quantity of a watch in the cart
            case "update": {
                Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                CartElementBean cartElementBean = new CartElementBean(user, watch, quantity);
                if(quantity == 0) {
                    shoppingCart.removeFromCart(cartElementBean);
                }else{
                    shoppingCart.updateCartElementQuantity(cartElementBean);
                }
                break;
            }
            //Called to remove a watch from the cart
            case "remove": {
                Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
                CartElementBean cartElementBean = new CartElementBean(user, watch, 0);
                shoppingCart.removeFromCart(cartElementBean);
                break;
            }
        }

        resp.sendRedirect("../cart/cart.jsp");
    }
}

