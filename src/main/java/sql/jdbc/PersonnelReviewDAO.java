package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.PersonnelReview;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelReviewDAO implements IBaseDAO<PersonnelReview> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();

    @Override
    public void insert(PersonnelReview personnelReview) {
        String query = "INSERT INTO personnelreviews (review_id, personnel_id, order_id, rating, review_text) VALUES (?, ?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, personnelReview.getReviewId());
            ps.setInt(2, personnelReview.getPersonnelId());
            ps.setInt(3, personnelReview.getOrderId());
            ps.setInt(4, personnelReview.getRating());
            ps.setString(5, personnelReview.getReviewText());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(PersonnelReview personnelReview) {
        Connection con = conPool.getConnect();
        String query = "UPDATE personnelreviews SET  personnel_id = ?, order_id = ?, rating = ?, review_text = ? WHERE review_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, personnelReview.getPersonnelId());
            ps.setInt(2, personnelReview.getOrderId());
            ps.setInt(3, personnelReview.getRating());
            ps.setString(4, personnelReview.getReviewText());
            ps.setInt(5, personnelReview.getReviewId());
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
        String query = "DELETE FROM personnelreviews WHERE review_id = ?";
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
        List<PersonnelReview> personnelReviews = new ArrayList<PersonnelReview>();
        String query = "SELECT * FROM personnelreviews";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int rateId = rs.getInt("rating_id");
                    int perId = rs.getInt("personnel_id");
                    int ordId = rs.getInt("order_id");
                    int rate = rs.getInt("rating");
                    String reviewText = rs.getString("review_text");
                    PersonnelReview personnelReview = new PersonnelReview(rateId, perId, ordId, rate, reviewText);
                    personnelReviews.add(personnelReview);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                conPool.release(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personnelReviews;
    }

    @Override
    public PersonnelReview getById(int i) {
        PersonnelReview personnelReview = new PersonnelReview(0, 0, 0, 0, null);
        String query = "SELECT * FROM personnelReview WHERE review_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try {
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    int rateId = rs.getInt("rating_id");
                    int perId = rs.getInt("personnel_id");
                    int ordId = rs.getInt("order_id");
                    int rate = rs.getInt("rating");
                    String reviewText = rs.getString("review_text");
                    personnelReview = new PersonnelReview(rateId, perId, ordId, rate, reviewText);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conPool.release(con);
        }
        return personnelReview;
    }
}
