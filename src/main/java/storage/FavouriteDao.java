package storage;

import java.util.Collection;

public interface FavouriteDao {
    public void addFavourite(FavouriteBeen favourite);
    public void updateFavourite(FavouriteBeen favourite);
    public FavouriteBeen getFavouriteById(Integer id);
    public Collection<FavouriteBeen> getAllFavourites();
    public void deleteFavourite(FavouriteBeen favourite);
    
}
