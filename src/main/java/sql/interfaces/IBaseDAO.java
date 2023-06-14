package sql.interfaces;
import java.util.List;
public interface IBaseDAO<T> {
    void insert(T t);
    void update(T t);
    void delete(int i);
    List<T> getAll();
    T getById(int i);
}
