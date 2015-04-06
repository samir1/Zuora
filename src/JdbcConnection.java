import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Samir Undavia
 *
 */
public class JdbcConnection {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";  // uses MySQL database
	
	// database credentials
	static final String DB_USER = "root";
	static final String DB_PASS = "password";

	public static void main(String[] args) {
		Connection con = connectToDb();
		createDb(con);
		createRequirementTable(con);
		createUserTable(con);
		createAccessTable(con);
		closeConnection(con);
	}

	static void createAccessTable(Connection con) {
		String query = "CREATE TABLE Access "
				+ "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " RequirementId INTEGER NOT NULL, UserId INTEGER NOT NULL, "
				+ " PRIMARY KEY (id));"; // create Access table
		try {
			con.prepareStatement(query).execute();
			System.out.println("Created table Access...");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	static void createUserTable(Connection con) {
		String query = "CREATE TABLE User "
				+ "(id INTEGER NOT NULL AUTO_INCREMENT, PRIMARY KEY (id));"; // create
																				// User
		// table
		try {
			con.prepareStatement(query).execute();
			System.out.println("Created table User...");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	static void createRequirementTable(Connection con) {
		String query = "CREATE TABLE Requirement "
				+ "(id INTEGER NOT NULL AUTO_INCREMENT, "
				+ " ParentRequirementId INTEGER, PRIMARY KEY (id));"; // create
																		// Requirement
																		// table
		try {
			con.prepareStatement(query).execute();
			System.out.println("Created table Requirement...");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	static Connection connectToDb() {
		Connection con = null;
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);
			// Open a connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace(); // Class.forName errors
		}
		return con;
	}

	static void createDb(Connection con) {

		String dropDatabase = "DROP DATABASE ItDepartment"; // start with a new
															// database
		try {
			con.prepareStatement(dropDatabase).execute();
			System.out.println("Dropped database ItDepartment...");
		} catch (SQLException e) {
		} // nothing to do if database does not exist

		try {
			con.prepareStatement("CREATE DATABASE ItDepartment").execute();
			con.prepareStatement("USE ItDepartment").execute();
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Created databse ItDepartment...");

	}

	private static void closeConnection(Connection con) {
		System.out.println("Closed connection...");
		try {
			if (con != null)
				con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
