package sql.jdbc;


import sql.model.OrderDetail;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO implements sql.interfaces.IBaseDAO<OrderDetail> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(OrderDetail orderDetail) {
        String query = "INSERT INTO orderdetails  (order_id, product_id, quantity) VALUES (?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, orderDetail.getOrderId());
            ps.setInt(2, orderDetail.getProductId());
            ps.setInt(3, orderDetail.getQuantity());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        Connection con = conPool.getConnect();
        String query = "UPDATE orderdetails SET product_id = ?, quantity = ? WHERE order_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, orderDetail.getProductId());
            ps.setInt(2, orderDetail.getQuantity());
            ps.setInt(3, orderDetail.getOrderId());
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
        String query = "DELETE FROM orderdetails WHERE order_id = ?";
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
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        String query = "SELECT * FROM orderdetails";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int orderId = rs.getInt("order_id");
                    int productId = rs.getInt("product_id");
                    int quntity = rs.getInt("quantity");
                    OrderDetail orderDetail = new OrderDetail(orderId, productId, quntity);
                    orderDetails.add(orderDetail);
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
        return orderDetails;
    }

    @Override
    public OrderDetail getById(int i) {
        OrderDetail orderDetail = new OrderDetail(0, 0, 0);
        String query = "SELECT * FROM orderdetails WHERE order_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int orderId = rs.getInt("order_id");
                    int productId = rs.getInt("product_id");
                    int quntity = rs.getInt("quantity");
                    orderDetail = new OrderDetail(orderId, productId, quntity);
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
        return orderDetail;
    }
}