import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


/**
 * @author Samir Undavia
 *
 */
public class Tests {

	@Test
	public void testIt() throws Exception {

		Connection con = JdbcConnection.connectToDb();
		JdbcConnection.createDb(con);
		JdbcConnection.createRequirementTable(con);
		JdbcConnection.createUserTable(con);
		JdbcConnection.createAccessTable(con);

		RequirementDaoImpl rdi = new RequirementDaoImpl(con);
		UserDaoImpl odi = new UserDaoImpl(con);
		AccessDaoImpl adi = new AccessDaoImpl(con);

		try {

			// Requirement tests
			for (int i = 0; i < 3; i++) {
				rdi.insert(new Requirement());
			}
			for (int i = 1; i <= 3; i++) {
				assertEquals(new Requirement(i, 0), rdi.get(i)); // test to see
																	// if adding
																	// works
			}
			for (int i = 1; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					rdi.insert(new Requirement(i));
				}
			}
			for (int i = 4; i <= 12; i++) {
				rdi.insert(new Requirement(i));
			}
			for (int i = 0; i <= 10; i++) {
				rdi.insert(new Requirement());
			}

			int n = rdi.list().size() - 1;
			rdi.delete(n); // delete test
			assertNull(rdi.get(n));

			n = rdi.list().size() - 1;
			assertEquals(new Requirement(n, 0), rdi.get(n));
			rdi.delete(n); // delete test
			assertNull(rdi.get(n));

			// User tests
			for (int i = 0; i < 3; i++) {
				odi.insert(new User());
			}
			n = odi.list().size() - 1;
			assertEquals(new User(n), odi.get(n)); // exists test
			odi.delete(n); // delete test
			assertNull(odi.get(n));

			// Access tests
			for (int i = 1; i <= 3; i++) {
				adi.insert(new Access(i, 1));
			}
			List<Requirement> list = new ArrayList<Requirement>();
			int counter = 1;
			for (int i = 0; i <= 3; i++) {
				for (int j = 0; j < 3; j++) {
					list.add(new Requirement(counter, i));
					counter++;
				}
			}
			for (int i = 4; i <= 12; i++) {
				list.add(new Requirement(counter, i));
				counter++;
			}
			// test a Java method that returns all requirements that a user has
			// access to, with optimal performance
			assertEquals(list, adi.listOfAccessibleRequirementsForUser(1));
		} finally {
			con.close();
		}
	}

}
