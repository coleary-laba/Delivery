package sql.jdbc;

import sql.model.DeliveryRoute;
import utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRouteDAO implements sql.interfaces.IBaseDAO<DeliveryRoute> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(DeliveryRoute delivRoute) {
        String query = "INSERT INTO deliveryroutes (route_id, personnel_id, start_location, end_location) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, delivRoute.getRouteId());
            ps.setInt(2, delivRoute.getPersonnelId());
            ps.setString(3, delivRoute.getStartLocation());
            ps.setString(4, delivRoute.getEndLocation());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(DeliveryRoute deliveryRoute) {
        Connection con = conPool.getConnect();
        String query = "UPDATE deliveryroutes SET personnel_id = ?, start_location = ?, end_location = ? WHERE route_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, deliveryRoute.getPersonnelId());
            ps.setString(2, deliveryRoute.getStartLocation());
            ps.setString(3, deliveryRoute.getEndLocation());
            ps.setInt(4, deliveryRoute.getRouteId());
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
        String query = "DELETE FROM deliveryroutes WHERE route_id = ?";
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
        List<DeliveryRoute> deliveryRoutes = new ArrayList<DeliveryRoute>();
        String query = "SELECT * FROM deliveryroutes";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int routeId = rs.getInt("route_id");
                    int persId = rs.getInt("personnel_id");
                    String start = rs.getString("start_location");
                    String end = rs.getString("end_location");
                    DeliveryRoute delivRoute = new DeliveryRoute(routeId, persId, start, end);
                    deliveryRoutes.add(delivRoute);
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
        return deliveryRoutes;
    }

    @Override
    public DeliveryRoute getById(int i) {
        DeliveryRoute delivRoute = new DeliveryRoute(0, 0, null, null);
        String query = "SELECT * FROM deliveryroutes WHERE route_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int routeId = rs.getInt("route_id");
                    int persId = rs.getInt("personnel_id");
                    String start = rs.getString("start_location");
                    String end = rs.getString("end_location");
                    delivRoute = new DeliveryRoute(routeId, persId, start, end);
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
        return delivRoute;
    }
}