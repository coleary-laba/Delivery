package sql.model;

public class PersonnelReview {
    private int reviewId;
    private int personnelId;
    private int orderId;
    private int rating;
    private String reviewText;

    public PersonnelReview(int reviewId, int personnelId, int orderId, int rating, String reviewText) {
        this.reviewId = reviewId;
        this.personnelId = personnelId;
        this.orderId = orderId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getPersonnelId() {
        return personnelId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }
}
