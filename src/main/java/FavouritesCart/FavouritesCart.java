package FavouritesCart;

import jakarta.servlet.http.HttpSession;
import org.tinylog.Logger;
import Model.Beans.FavouriteBean;
import Model.Models.FavouriteModel;

import java.util.*;

public class FavouritesCart {
    Set<FavouriteBean> favouritesCart;
    FavouriteModel FavouriteModel = new FavouriteModel();
    HttpSession session;

    public FavouritesCart(HttpSession session){
        favouritesCart  = session.getAttribute("favourites") == null ? new HashSet<>() : new HashSet<>((Set<FavouriteBean>) session.getAttribute("favourites"));
        this.session = session;
    }

    /**
     * From session
     */
    public synchronized Set<FavouriteBean> getFavourites(){
        Object userIdObject = session.getAttribute("user");
        if(userIdObject != null){
            Long userId = Long.parseUnsignedLong(String.valueOf(userIdObject));
            try {
                var temp = new HashSet<>(FavouriteModel.doRetrieveByCond("WHERE user = ?", List.of(userId)));
                favouritesCart.forEach(favouriteBean -> {
                    if(!temp.contains(favouriteBean)){
                        try {
                            favouriteBean.setUser(userId);
                            FavouriteModel.doSaveOrUpdate(favouriteBean);
                        } catch (Exception e) {
                            Logger.error(e.getMessage());
                        }
                    }
                });
                favouritesCart.addAll(temp);
                session.setAttribute("favourites", favouritesCart);
            } catch (Exception e) {
                Logger.warn(e.getMessage(),"jndskcenjkdsnjkfivkdnkinbjknjk");
            }
        }
        else {
            if(session.getAttribute("favourites") != null)
                favouritesCart.addAll((Set<FavouriteBean>)session.getAttribute("favourites"));
        }
        return favouritesCart;
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
