package Control;

import FavouritesCart.FavouritesCart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import Model.Beans.FavouriteBean;

import java.io.IOException;

@WebServlet(name = "FavouritesServlet", value = "/favourites-servlet")
public class FavouritesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        FavouritesCart favouritesCart = new FavouritesCart(session);

        Long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
        Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
        String action = String.valueOf(req.getParameter("action"));

        Logger.info(action, watch);
        switch (action) {
            case "add": {
                favouritesCart.addFavourites(new FavouriteBean(watch,user));
                break;
            }
            case "remove": {
                favouritesCart.removeFromFavourites(new FavouriteBean(watch,user));
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/favourites/favourites.jsp");
    }
}
