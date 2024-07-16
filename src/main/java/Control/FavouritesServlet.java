package Control;

import FavouritesCart.FavouritesCart;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Model.Beans.FavouriteBean;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "FavouritesServlet", value = "/favourites-servlet")
public class FavouritesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
        resp.sendRedirect("../favourites/favourites.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        FavouritesCart favouritesCart = new FavouritesCart(session);

        Long user = session.getAttribute("user") != null ? Long.parseUnsignedLong(String.valueOf(session.getAttribute("user"))) : 0;
        Long watch = Long.parseUnsignedLong(req.getParameter("watch"));
        String action = String.valueOf(req.getParameter("action"));
        FavouriteBean favouriteBean = new FavouriteBean(watch, user);
        switch (action) {
            case "add": {
                favouritesCart.addFavourites(favouriteBean);
                break;
            }
            case "remove": {
                favouritesCart.removeFromFavourites(favouriteBean);
                break;
            }
            case "get":
                Collection<FavouriteBean> favourites = favouritesCart.getFavourites();
                boolean isPresent = favourites.stream().anyMatch(bean -> bean.equals(favouriteBean));
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(new Gson().toJson(isPresent));
                break;
        }
    }
}
