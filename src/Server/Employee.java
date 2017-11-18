package Server;

/**
 * Employee.java
 * -----------------------------
 * Very basic class which further extends the Person.java class
 * in describing how data of Employees should be stored and
 * accessed.
 * 
 * @author martin
 *
 */

public class Employee extends Person {

	int id;
	String salary; 
	String startDate;
	String title;
	String email;
	
	public Employee() {
		super();
	}
	
	public Employee(String _name, char _gender, String _natInscNo, String _dob, String _address, String _postcode, int _id, String _salary, String _startDate, String _title, String _email) {
		super(_name, _gender, _natInscNo, _dob, _address, _postcode);
		this.id = _id;
		this.salary = _salary;
		this.startDate = _startDate;
		this.title = _title;
		this.email = _email;
	}

	// Getters
	public int getId(){ return this.id; }
	public String getSalary(){ return this.salary; }
	public String getStartDate(){ return this.startDate; }
	public String getTitle(){ return this.title; }
	public String getEmail(){ return this.email; }

	// Setters
	public void setId(int _id){ this.id = _id; }
	public void setSalary(String _salary){ this.salary = _salary; }
	public void setStartDate(String _startDate){ this.startDate = _startDate; }
	public void setTitle(String _title){ this.title = _title; }
	public void setEmail(String _email){ this.email = _email; }
	
}
