package sql.model;

public class CustomerReview {
    private int reviewId;
    private int customerId;
    private int orderId;
    private int rating;
    private String reviewText;

    public CustomerReview(int reviewId, int customerId, int orderId, int rating, String reviewText) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.orderId = orderId;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getCustomerId() {
        return customerId;
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
