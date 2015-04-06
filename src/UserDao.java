import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public interface UserDao {

	public void insert(User user);

	public void delete(int userId);

	public User get(int userId);

	public List<User> list();

}
