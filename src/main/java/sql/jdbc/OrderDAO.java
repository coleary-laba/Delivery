package sql.jdbc;

import sql.model.Order;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements sql.interfaces.IBaseDAO<Order> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Order order) {
        String query = "INSERT INTO orders (order_id, customer_id, personnel_id, order_date) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, order.getOrderId());
            ps.setInt(2, order.getCustomerId());
            ps.setInt(3, order.getPersonnelId());
            ps.setDate(4, (Date) order.getOrderDate());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Order order) {
        Connection con = conPool.getConnect();
        String query = "UPDATE orders SET customer_id = ?, personnel_id = ?, order_date = ? WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, order.getCustomerId());
            ps.setInt(2, order.getPersonnelId());
            ps.setDate(3, (Date) order.getOrderDate());
            ps.setInt(4, order.getOrderId());
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
        String query = "DELETE FROM orders WHERE status_id = ?";
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
        List<Order> orders = new ArrayList<Order>();
        String query = "SELECT * FROM orders";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int orderId = rs.getInt("order_id");
                    int custId = rs.getInt("customer_id");
                    int persId = rs.getInt("personnel_id");
                    Date date = rs.getDate("order_date");
                    Order order = new Order(orderId, custId, persId, date);
                    orders.add(order);
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
        return orders;
    }

    @Override
    public Order getById(int i) {
        Order order = new Order(0, 0, 0, null);
        String query = "SELECT * FROM orders WHERE order_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int orderId = rs.getInt("order_id");
                    int custId = rs.getInt("customer_id");
                    int persId = rs.getInt("personnel_id");
                    Date date = rs.getDate("order_date");
                    order = new Order(orderId, custId, persId, date);
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
        return order;
    }
}