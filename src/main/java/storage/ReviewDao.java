package storage;

import java.util.Collection;

public interface ReviewDao {
    public void addReview(ReviewBeen review);
    public void updateReview(ReviewBeen review);
    public ReviewBeen getReviewById(Integer id);
    public Collection<ReviewBeen> getAllReviews();
    public void deleteReview(ReviewBeen review);
    public void deleteAllReviews();

}
