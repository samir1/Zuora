import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public class UserDaoImpl implements UserDao {

	Connection con;

	/**
	 * @param con
	 */
	public UserDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(User user) {
		String query = "INSERT INTO User () VALUES ();";
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int userId) {
		String query = "DELETE FROM User WHERE id=" + userId;
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User get(int userId) {
		User o = null;
		try {
			ResultSet resultSet = con.prepareStatement(
					"SELECT id FROM User WHERE id=" + userId).executeQuery();
			if (resultSet.next())
				o = new User(resultSet.getInt("id"));
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<User>();

		try (ResultSet resultSet = con.prepareStatement("SELECT id FROM User")
				.executeQuery();) {
			while (resultSet.next()) {
				User user = new User(resultSet.getInt("id"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

}
