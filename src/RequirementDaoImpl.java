import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public class RequirementDaoImpl implements RequirementDao {

	private Connection con;

	/**
	 * @param con
	 */
	public RequirementDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Requirement requirement) {
		String query = "";
		if (requirement.getParentRequirementId() == -1)
			query = "INSERT INTO Requirement () VALUES ();";
		else
			query = "INSERT INTO Requirement (ParentRequirementId) VALUES ("
					+ requirement.getParentRequirementId() + ");";
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//A requirement’s parent requirement cannot be changed.
//	@Override
//	public void update(Requirement requirement) {
//		String query = "UPDATE Requirement SET ParentRequirementId="
//				+ requirement.getParentRequirementId() + " WHERE id="
//				+ requirement.getId();
//		try {
//			con.prepareStatement(query).execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public void delete(int requirementId) {
		String query = "DELETE FROM Requirement WHERE id=" + requirementId;
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Requirement get(int requirementId) {
		Requirement r = null;
		try {
			ResultSet resultSet = con.prepareStatement(
					"SELECT id, ParentRequirementId FROM Requirement WHERE id="
							+ requirementId).executeQuery();
			if (resultSet.next())
				r = new Requirement(resultSet.getInt("id"),
						resultSet.getInt("ParentRequirementId"));
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public List<Requirement> list() {
		List<Requirement> requirements = new ArrayList<Requirement>();

		try (ResultSet resultSet = con.prepareStatement(
				"SELECT id, ParentRequirementId FROM Requirement")
				.executeQuery();) {
			while (resultSet.next()) {
				Requirement requirement = new Requirement(
						resultSet.getInt("id"),
						resultSet.getInt("ParentRequirementId"));
				requirements.add(requirement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requirements;
	}

	public Requirement getParentOf(Requirement requirement) {
		return this.get(requirement.getParentRequirementId());
	}

	public List<Requirement> getChildrenOf(int parentRequirementId) {
		List<Requirement> requirements = new ArrayList<Requirement>();

		try (ResultSet resultSet = con.prepareStatement(
				"SELECT id, ParentRequirementId FROM Requirement WHERE ParentRequirementId="
						+ parentRequirementId).executeQuery();) {
			while (resultSet.next()) {
				Requirement requirement = new Requirement(
						resultSet.getInt("id"),
						resultSet.getInt("ParentRequirementId"));
				requirements.add(requirement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requirements;
	}
}
