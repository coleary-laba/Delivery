package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.Personnel;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDAO implements IBaseDAO<Personnel>{
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Personnel personnel) {
        String query = "INSERT INTO personnel (personnel_id, personnel_name, address, phone_number) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, personnel.getPersonnelId());
            ps.setString(2, personnel.getPersonnelName());
            ps.setString(3, personnel.getAddress());
            ps.setString(4, personnel.getPhoneNumber());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Personnel personnel) {
        Connection con = conPool.getConnect();
        String query = "UPDATE personnel SET personnel_name = ?, address = ?, phone_number = ? WHERE personnel_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, personnel.getPersonnelName());
            ps.setString(2, personnel.getAddress());
            ps.setString(3, personnel.getPhoneNumber());
            ps.setInt(4, personnel.getPersonnelId());
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
        String query = "DELETE FROM personnel WHERE personnel_id = ?";
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
        List<Personnel> personnels = new ArrayList<Personnel>();
        String query = "SELECT * FROM personnel";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int perId = rs.getInt("personnel_id");
                    String perName = rs.getString("personnel_name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone_number");
                    Personnel personnel = new Personnel(perId, perName, address, phone);
                    personnels.add(personnel);
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
        return personnels;
    }

    @Override
    public Personnel getById(int i) {
        Personnel personnel = new Personnel(0, null, null, null);
        String query = "SELECT * FROM personnel WHERE personnel_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int perId = rs.getInt("personnel_id");
                    String perName = rs.getString("personnel_name");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone_number");
                    personnel = new Personnel(perId, perName, address, phone);
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
        return personnel;
    }
}