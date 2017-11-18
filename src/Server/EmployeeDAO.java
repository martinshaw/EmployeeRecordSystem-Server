package Server;
import java.sql.*;
import java.util.ArrayList;

/**
 * EmployeeDAO.java
 * -----------------------------
 * Data Access Object containing methods for communicating
 * with & receiving data from the SQLite server.
 * 
 * @author martin
 */

public class EmployeeDAO {

	private Connection c;
	private Statement s;
	private ResultSet r;
	private String DBUrl = "jdbc:sqlite:employees.sqlite"; 
	
	
	public EmployeeDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// Define and start a connection to the SQLite database
	// to be used throughout the Application's runtime.
	public Statement getConnection(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = this.DBUrl;
			this.c = DriverManager.getConnection(dbURL);
			this.s = this.c.createStatement();
			return this.s;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return s;
	}
	
	// Method to close the above connection at the
	// end of the Application's runtime.
	public void closeConnection(){
		if (this.c != null){
			try {
				this.c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.s != null){
			try {
				this.s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (this.r != null){
			try {
				this.r.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Receive a list of all Employee information from the server,
	// pass into an ArrayList for use in the main ListView.
	public ArrayList<Employee> selectAllEmployees(){
		ArrayList<Employee> _ret = new ArrayList<Employee>();
		String query = "SELECT * FROM employees;";
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
			while (this.r.next()) {
				_ret.add(new Employee(
					this.r.getString("Name"),
					(char) this.r.getString("Gender").charAt(0),
					this.r.getString("NIN"),
					this.r.getString("DOB"),
					this.r.getString("Address"),
					this.r.getString("Postcode"),
					this.r.getInt("ID"),
					this.r.getString("Salary"),
					this.r.getString("StartDate"),
					this.r.getString("JobTitle"),
					this.r.getString("Email")
				));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return _ret;
	}

	// Receive a particular Employee's information from the server,
	// for use when requesting more details about the Employee.
	public Employee selectEmployeeByName(String _name){
		Employee _ret = new Employee();
		String query = "SELECT * FROM employees WHERE Name='"+_name+"';";
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
			while (this.r.next()) {
				_ret = new Employee(
					this.r.getString("Name"),
					(char) this.r.getString("Gender").charAt(0),
					this.r.getString("NIN"),
					this.r.getString("DOB"),
					this.r.getString("Address"),
					this.r.getString("Postcode"),
					this.r.getInt("ID"),
					this.r.getString("Salary"),
					this.r.getString("StartDate"),
					this.r.getString("JobTitle"),
					this.r.getString("Email")
				);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return _ret;
	}

	// Receive a particular Employee's information from the server,
	// for use when requesting more details about the Employee.
	public Employee selectEmployeeByID(int _id){
		Employee _ret = new Employee();
		String query = "SELECT * FROM employees WHERE ID="+_id+";";
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
			while (this.r.next()) {
				_ret = new Employee(
					this.r.getString("Name"),
					(char) this.r.getString("Gender").charAt(0),
					this.r.getString("NIN"),
					this.r.getString("DOB"),
					this.r.getString("Address"),
					this.r.getString("Postcode"),
					this.r.getInt("ID"),
					this.r.getString("Salary"),
					this.r.getString("StartDate"),
					this.r.getString("JobTitle"),
					this.r.getString("Email")
				);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return _ret;
	}

	// Method to insert Employee data into the database
	// using a populated instance of the Employee class.
	public void insertEmployee(Employee _e){
		String query = String.format("INSERT INTO employees (Name, Gender, DOB, Address, PostCode, NIN, JobTitle, StartDate, Salary, Email)"+
			"VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');",
			_e.getName(), _e.getGender(), _e.getDob(), _e.getAddress(), _e.getPostcode(), _e.getNatInscNo(),
			_e.getTitle(), _e.getStartDate(), _e.getSalary(), _e.getEmail());
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Method to insert Employee data into the database at a specified ID position
	// using a populated instance of the Employee class.
	public void insertEmployeeAtID(Employee _e, int _id){
		String query = String.format("UPDATE employees SET "+
			"Name='%s',Gender='%s',DOB='%s',Address='%s',PostCode='%s',NIN='%s',JobTitle='%s',"+
			"StartDate='%s',Salary='%s',Email='%s' WHERE ID="+_id+";",
			_e.getName(), _e.getGender(), _e.getDob(), _e.getAddress(), _e.getPostcode(), _e.getNatInscNo(),
			_e.getTitle(), _e.getStartDate(), _e.getSalary(), _e.getEmail());
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Method to delete an Employee record identified by ID number.
	public void deleteEmployeeById(int _id){
		String query = "DELETE FROM employees WHERE ID="+_id+";";
		try {
			this.s = this.c.createStatement();
			System.out.println(query);
			this.r = this.s.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
