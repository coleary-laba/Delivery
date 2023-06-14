package sql.jdbc;


import sql.model.DeliveryStatus;
import utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryStatusDAO implements sql.interfaces.IBaseDAO<DeliveryStatus> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(DeliveryStatus deliveryStatus) {
        String query = "INSERT INTO deliverystatus (status_id, order_id, route_id, status) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryStatus.getStatusId());
            ps.setInt(2, deliveryStatus.getOrderId());
            ps.setInt(3, deliveryStatus.getRouteId());
            ps.setString(4, deliveryStatus.getStatus());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(DeliveryStatus deliveryStatus) {
        Connection con = conPool.getConnect();
        String query = "UPDATE deliverystatus SET order_id = ?, route_id = ?, status = ? WHERE status_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryStatus.getOrderId());
            ps.setInt(2, deliveryStatus.getRouteId());
            ps.setString(3, deliveryStatus.getStatus());
            ps.setInt(4, deliveryStatus.getStatusId());
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
        String query = "DELETE FROM deliverystatus WHERE status_id = ?";
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
        List<DeliveryStatus> deliveryStatuses = new ArrayList<DeliveryStatus>();
        String query = "SELECT * FROM deliverystatus";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int statusId = rs.getInt("status_id");
                    int ordId = rs.getInt("order_id");
                    int routeId = rs.getInt("route_id");
                    String status = rs.getString("status");
                    DeliveryStatus deliveryStatus = new DeliveryStatus(statusId, ordId, routeId, status);
                    deliveryStatuses.add(deliveryStatus);
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
        return deliveryStatuses;
    }

    @Override
    public DeliveryStatus getById(int i) {
        DeliveryStatus deliveryStatus = new DeliveryStatus(0, 0, 0, null);
        String query = "SELECT * FROM deliverystatus WHERE status_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int statusId = rs.getInt("status_id");
                    int ordId = rs.getInt("order_id");
                    int routeId = rs.getInt("route_id");
                    String status = rs.getString("status");
                    deliveryStatus = new DeliveryStatus(statusId, ordId, routeId, status);
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
        return deliveryStatus;
    }
}