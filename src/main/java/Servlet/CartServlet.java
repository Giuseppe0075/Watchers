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
import java.util.Collection;
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
        CartElementModel cartElementModel = new CartElementModel();
        HttpSession session = req.getSession();
        Long user = session.getAttribute("user") != null ?  ((UserBean) session.getAttribute("user")).getId() : (long) 0;
        Collection<CartElementBean> cart;
        try {
            cart = session.getAttribute("cart") != null ? (List<CartElementBean>) session.getAttribute("cart") :
                    cartElementModel.doRetrieveByCond("WHERE user = ?", List.of(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        session.setAttribute("cart",cart);
        req.getRequestDispatcher("/cart/cart.jsp").forward(req, resp);
    }
}

