package sql.jdbc;

import sql.model.Customer;
import sql.model.DeliveryAddress;
import utility.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressDAO implements sql.interfaces.IBaseDAO<DeliveryAddress> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(DeliveryAddress delivAdd) {
        String query = "INSERT INTO deliveryaddresses (address_id, customer_id, address) VALUES (?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, delivAdd.getAddressId());
            ps.setInt(2, delivAdd.getCustomerId());
            ps.setString(3, delivAdd.getAddress());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(DeliveryAddress delivAdd) {
        Connection con = conPool.getConnect();
        String query = "UPDATE deliveryaddresses SET customer_id = ?, address = ? WHERE address_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, delivAdd.getCustomerId());
            ps.setString(2, delivAdd.getAddress());
            ps.setInt(3, delivAdd.getAddressId());
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
        String query = "DELETE FROM deliveryaddresses WHERE address_id = ?";
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
        List<DeliveryAddress> delivAdds = new ArrayList<DeliveryAddress>();
        String query = "SELECT * FROM deliveryaddresses";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int addId = rs.getInt("address_id");
                    int custId = rs.getInt("customer_id");
                    String address = rs.getString("address");
                    DeliveryAddress deliveryAddress = new DeliveryAddress(addId, custId, address);
                    delivAdds.add(deliveryAddress);
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
        return delivAdds;
    }

    @Override
    public DeliveryAddress getById(int i) {
        DeliveryAddress delivAdd = new DeliveryAddress(0, 0, null);
        String query = "SELECT * FROM deliveryaddresses WHERE address_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int addId = rs.getInt("address_id");
                    int custId = rs.getInt("customer_id");
                    String address = rs.getString("address");
                    delivAdd = new DeliveryAddress(addId, custId, address);
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
        return delivAdd;
    }
}