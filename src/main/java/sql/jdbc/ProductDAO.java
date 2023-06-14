package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.Personnel;
import sql.model.Product;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IBaseDAO<Product>{
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Product product) {
        String query = "INSERT INTO products (product_id, productName, price, category_id) VALUES (?, ?, ?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getCategoryId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Product product) {
        Connection con = conPool.getConnect();
        String query = "UPDATE products SET product_name = ?, price = ?, category_id = ? WHERE product_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCategoryId());
            ps.setInt(4, product.getProductId());
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
        String query = "DELETE FROM products WHERE product_id = ?";
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
        List<Product> products = new ArrayList<Product>();
        String query = "SELECT * FROM products";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int prodId = rs.getInt("product_id");
                    String prodName = rs.getString("product_name");
                    double price = rs.getDouble("price");
                    int catId = rs.getInt("category_id");
                    Product product = new Product(prodId, prodName, price, catId);
                    products.add(product);
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
        return products;
    }

    @Override
    public Product getById(int i) {
        Product product = new Product(0, null, 0, 0);
        String query = "SELECT * FROM products WHERE product_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int prodId = rs.getInt("product_id");
                    String prodName = rs.getString("product_name");
                    double price = rs.getDouble("price");
                    int catId = rs.getInt("category_id");
                    product = new Product(prodId, prodName, price, catId);
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
        return product;
    }
}