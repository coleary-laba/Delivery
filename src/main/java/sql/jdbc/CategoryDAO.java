package sql.jdbc;

import sql.interfaces.IBaseDAO;
import sql.model.Category;
import utility.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements IBaseDAO<Category> {
    private final ConnectionPool conPool = ConnectionPool.getInstance();
    @Override
    public void insert(Category category) {
        String query = "INSERT INTO categories (category_id, category_name) VALUES (?, ?)";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, category.getCategoryId());
            ps.setString(2, category.getCategoryName());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            conPool.release(con);
        }
    }

    @Override
    public void update(Category category) {
        Connection con = conPool.getConnect();
        String query = "UPDATE category SET category_name = ? WHERE category_id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
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
        String query = "DELETE FROM categories WHERE category_id = ?";
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
        List<Category> categories = new ArrayList<Category>();
        String query = "SELECT * FROM categories";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int catId = rs.getInt("category_id");
                    String catName = rs.getString("category_name");
                    Category cat = new Category(catId, catName);
                    categories.add(cat);
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
        return categories;
    }

    @Override
    public Category getById(int i) {
        Category cat = new Category(0, null);
        String query = "SELECT * FROM categories WHERE category_id = ?";
        Connection con = conPool.getConnect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.execute();
            try{
                ResultSet rs = ps.getResultSet();
                while(rs.next()){
                    int catId = rs.getInt("category_id");
                    String catName = rs.getString("category_name");
                    cat = new Category(catId, catName);
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
        return cat;
    }
}
