import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public interface AccessDao {

	public void insert(Access access);

	public void update(Access access);

	public void delete(int accessId);

	public Access get(int accessId);

	public List<Access> list();
}
