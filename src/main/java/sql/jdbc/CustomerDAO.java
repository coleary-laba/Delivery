package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.Customer;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements IBaseDAO<Customer>{
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Customer customer) {
        String query = "INSERT INTO customers (customer_id, customer_name, phone_number) VALUES (?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getCustomerName());
            ps.setString(3, customer.getPhoneNumber());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Customer customer) {
        Connection con = conPool.getConnect();
        String query = "UPDATE customers SET customer_name = ?, phone_number = ? WHERE customer_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setInt(3, customer.getCustomerId());
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
        String query = "DELETE FROM customers WHERE customer_id = ?";
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
        List<Customer> customers = new ArrayList<Customer>();
        String query = "SELECT * FROM customers";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int custId = rs.getInt("customer_id");
                    String custName = rs.getString("customer_name");
                    String phone = rs.getString("phone_number");
                    Customer cust = new Customer(custId, custName, phone);
                    customers.add(cust);
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
        return customers;
    }

    @Override
    public Customer getById(int i) {
        Customer cust = new Customer(0, null, null);
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int custId = rs.getInt("customer_id");
                    String custName = rs.getString("customer_name");
                    String phone = rs.getString("phone_number");
                    cust = new Customer(custId, custName, phone);
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
        return cust;
    }
}