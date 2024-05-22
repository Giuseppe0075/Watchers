package FavouritesCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import storage.Beans.CartElementBean;
import storage.Beans.FavouriteBean;
import storage.Models.FavouriteModel;

import java.util.*;

public class FavouritesCart {
    Set<FavouriteBean> favouritesCart;
    FavouriteModel FavouriteModel = new FavouriteModel();
    HttpSession session;

    public FavouritesCart(HttpSession session){
        favouritesCart  = new HashSet<>();
        this.session = session;
    }

    /**
     * From session
     */
    public synchronized Set<FavouriteBean> getFavourites(){
        Logger.info("test");
        Object userIdObject = session.getAttribute("user");
        var temp = favouritesCart;
        if(userIdObject != null){
            Long userId = Long.parseUnsignedLong(String.valueOf(userIdObject));
            try {
                temp.addAll(FavouriteModel.doRetrieveByCond("WHERE user = ?", List.of(userId)));

            } catch (Exception e) {
                Logger.warn(e.getMessage());
            }
        }
        else {
            if(session.getAttribute("favourites") != null)
                temp.addAll((Collection<? extends FavouriteBean>) session.getAttribute("favourites"));
        }
        session.setAttribute("favourites", temp);
        return temp;
    }

    public synchronized void addFavourites(FavouriteBean favouriteBean){
        //Check if the user is logged
        Object userIdObject = session.getAttribute("user");
        favouritesCart = this.getFavourites();

        if(userIdObject != null){
            try {
                FavouriteModel.doSaveOrUpdate(favouriteBean);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
        favouritesCart.add(favouriteBean);
        session.setAttribute("favourites", this.getFavourites());
    }




    public synchronized void removeFromFavourites(FavouriteBean favouriteBean){
        Object userIdObject = session.getAttribute("user");
        favouritesCart = this.getFavourites();
        //Check if the user is logged
        if(userIdObject != null){
            try {
                FavouriteModel.doDelete(favouriteBean);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
        }
        favouritesCart.remove(favouriteBean);
        session.setAttribute("favourites", favouritesCart);
    }

}
