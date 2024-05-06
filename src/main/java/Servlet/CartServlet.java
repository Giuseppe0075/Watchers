package Servlet;

import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;

import java.util.ArrayList;
import java.util.List;
import storage.Beans.UserBean;
import storage.Models.CartElementModel;

import java.io.IOException;

public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        Logger.debug("action: " + action + " watch: " + watch + " quantity: " + req.getParameter("quantity"));

        HttpSession session = req.getSession();

        List<CartElementBean> cart =  session.getAttribute("cart") != null ? (List<CartElementBean>) session.getAttribute("cart") : new ArrayList<>();

        if(action.equals("add")){
            CartElementBean cartElement = new CartElementBean(0L,watch,quantity);
            Long user = (Long) session.getAttribute("user");
            if(user == null){
                cart.add(cartElement);
            }else{
                CartElementModel cartElementModel = new CartElementModel();
                try {
                    cartElement.setUser(user);
                    cartElementModel.doSaveOrUpdate(cartElement);
                    cart.add(cartElement);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        session.setAttribute("cart",cart);
        req.getRequestDispatcher("/cart/cart.jsp").forward(req, resp);
    }
}
