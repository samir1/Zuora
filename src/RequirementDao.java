import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public interface RequirementDao {

	public void insert(Requirement requirement);
	
//	public void update(Requirement requirement);

	public void delete(int requirementId);

	public Requirement get(int requirementId);

	public List<Requirement> list();

}
