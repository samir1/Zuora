import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samir Undavia
 *
 */
public class AccessDaoImpl implements AccessDao {

	private Connection con;

	public AccessDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Access access) {
		String query = "INSERT INTO Access (RequirementId, UserId) VALUES ("
				+ access.getRequirementId() + ", " + access.getUserId() + ");";
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Access access) {
		String query = "UPDATE Access SET RequirementId="
				+ access.getRequirementId() + ", UserId="
				+ access.getUserId() + " WHERE id=" + access.getId();
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int accessId) {
		String query = "DELETE FROM Access WHERE id=" + accessId;
		try {
			con.prepareStatement(query).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Access get(int accessId) {
		Access a = null;
		try {
			ResultSet resultSet = con.prepareStatement(
					"SELECT id, RequirementId, UserId FROM Access WHERE id="
							+ accessId).executeQuery();
			if (resultSet.next())
				a = new Access(resultSet.getInt("id"),
						resultSet.getInt("RequirementId"),
						resultSet.getInt("UserId"));
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Access> list() {
		List<Access> accesses = new ArrayList<Access>();

		try (ResultSet resultSet = con.prepareStatement(
				"SELECT id, RequirementId, UserId FROM Access").executeQuery();) {
			while (resultSet.next()) {
				Access a = new Access(resultSet.getInt("id"),
						resultSet.getInt("RequirementId"),
						resultSet.getInt("UserId"));
				accesses.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accesses;
	}

	public List<Access> getAccessListWithUserId(int userId) {
		List<Access> accesses = new ArrayList<Access>();

		try (ResultSet resultSet = con.prepareStatement(
				"SELECT id, RequirementId, UserId FROM Access WHERE UserId="
						+ userId).executeQuery();) {
			while (resultSet.next()) {
				Access a = new Access(resultSet.getInt("id"),
						resultSet.getInt("RequirementId"),
						resultSet.getInt("UserId"));
				accesses.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accesses;
	}

	public List<Requirement> listOfAccessibleRequirementsForUser(int userId) {
		List<Requirement> finalRequirementsList = new ArrayList<Requirement>();
		List<Access> directAccessRequirements = getAccessListWithUserId(userId);
		List<Requirement> tempRequirementList = new ArrayList<Requirement>();
		for (Access a : directAccessRequirements) {
			try (ResultSet resultSet = con.prepareStatement(
					"SELECT id, ParentRequirementId FROM Requirement WHERE id="
							+ a.getRequirementId()).executeQuery();) {
				while (resultSet.next()) {
					Requirement r = new Requirement(resultSet.getInt("id"),
							resultSet.getInt("ParentRequirementId"));
					tempRequirementList.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		while (tempRequirementList.size() > 0) {
			Requirement req = tempRequirementList.remove(0);
			finalRequirementsList.add(req);
			try (ResultSet resultSet = con.prepareStatement(
					"SELECT id, ParentRequirementId FROM Requirement WHERE ParentRequirementId="
							+ req.getId()).executeQuery();) {
				while (resultSet.next()) {
					Requirement r = new Requirement(resultSet.getInt("id"),
							resultSet.getInt("ParentRequirementId"));
					tempRequirementList.add(r);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return finalRequirementsList;
	}
}
