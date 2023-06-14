package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.Customer;
import sql.model.CustomerReview;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerReviewDAO implements IBaseDAO<CustomerReview> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();

    @Override
    public void insert(CustomerReview custReview) {
        String query = "INSERT INTO customerreviews (review_id, customer_id, order_id, rating, review_text) VALUES (?, ?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, custReview.getReviewId());
            ps.setInt(2, custReview.getCustomerId());
            ps.setInt(3, custReview.getOrderId());
            ps.setInt(4, custReview.getRating());
            ps.setString(5, custReview.getReviewText());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(CustomerReview custReview) {
        Connection con = conPool.getConnect();
        String query = "UPDATE customerreviews SET  customer_id = ?, order_id = ?, rating = ?, review_text = ? WHERE review_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, custReview.getCustomerId());
            ps.setInt(2, custReview.getOrderId());
            ps.setInt(3, custReview.getRating());
            ps.setString(4, custReview.getReviewText());
            ps.setInt(5, custReview.getReviewId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
    }

    @Override
    public void delete(int i) {
        Connection con = conPool.getConnect();
        String query = "DELETE FROM customerreviews WHERE review_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
    }

    @Override
    public List getAll() {
        Connection con = conPool.getConnect();
        List<CustomerReview> custReviews = new ArrayList<CustomerReview>();
        String query = "SELECT * FROM customerreviews";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int rateId = rs.getInt("rating_id");
                    int custId = rs.getInt("customer_id");
                    int ordId = rs.getInt("order_id");
                    int rate = rs.getInt("rating");
                    String reviewText = rs.getString("review_text");
                    CustomerReview custRev = new CustomerReview(rateId, custId, ordId, rate, reviewText);
                    custReviews.add(custRev);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                conPool.release(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return custReviews;
    }

    @Override
    public CustomerReview getById(int i) {
        CustomerReview custRev = new CustomerReview(0, 0, 0, 0, null);
        String query = "SELECT * FROM customerreviews WHERE review_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int rateId = rs.getInt("rating_id");
                    int custId = rs.getInt("customer_id");
                    int ordId = rs.getInt("order_id");
                    int rate = rs.getInt("rating");
                    String reviewText = rs.getString("review_text");
                    custRev = new CustomerReview(rateId, custId, ordId, rate, reviewText);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
        return custRev;
    }
}
