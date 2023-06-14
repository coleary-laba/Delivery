package sql.jdbc;

import sql.model.Payment;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO implements sql.interfaces.IBaseDAO<Payment> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Payment payment) {
        String query = "INSERT INTO payments  (payment_id, order_id, payment_date, amount) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, payment.getPaymentId());
            ps.setInt(2, payment.getOrderId());
            ps.setDate(3, (Date) payment.getPaymentDate());
            ps.setDouble(4, payment.getAmount());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Payment payment) {
        Connection con = conPool.getConnect();
        String query = "UPDATE payments SET order_id = ?, payment_date = ?, amount = ? WHERE payment_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, payment.getOrderId());
            ps.setDate(2, (Date) payment.getPaymentDate());
            ps.setDouble(3, payment.getAmount());
            ps.setInt(4, payment.getPaymentId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void delete(int i) {
        Connection con = conPool.getConnect();
        String query = "DELETE FROM payments WHERE payment_id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, i);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
            conPool.release(con);
        }
    }

    @Override
    public List getAll() {
        Connection con = conPool.getConnect();
        List<Payment> payments = new ArrayList<Payment>();
        String query = "SELECT * FROM payments";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int paymentID = rs.getInt("payment_id");
                    int orderId = rs.getInt("order_id");
                    Date paymentDate = rs.getDate("payment_date");
                    double amount = rs.getDouble("amount");
                    Payment payment = new Payment(paymentID, orderId, paymentDate, amount);
                    payments.add(payment);
                }
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
            finally {
                conPool.release(con);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return payments;
    }

    @Override
    public Payment getById(int i) {
        Payment payment = new Payment(0, 0, null, 0);
        String query = "SELECT * FROM payments WHERE payment_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int paymentID = rs.getInt("payment_id");
                    int orderId = rs.getInt("order_id");
                    Date paymentDate = rs.getDate("payment_date");
                    double amount = rs.getDouble("amount");
                    payment = new Payment(paymentID, orderId, paymentDate, amount);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
        return payment;
    }
}