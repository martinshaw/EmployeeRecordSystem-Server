package Server;

/**
 * Person.java
 * -----------------------------
 * Very basic class which describes how data of
 * Persons should be stored and accessed.
 * 
 * @author martin
 *
 */

public class Person {
	String name;
	char gender;
	String natInscNo;
	String dob;
	String address;
	String postcode;
	
	public Person() {}
	
	public Person(String _name, char _gender, String _natInscNo, String _dob, String _address, String _postcode){
		this.name = _name;         
		this.gender = _gender;
		this.natInscNo = _natInscNo;
		this.dob = _dob;
		this.address = _address;
		this.postcode = _postcode;
	};

	// Getters
	public String getName(){ return this.name; };
	public char getGender(){ return this.gender; };
	public String getNatInscNo(){ return this.natInscNo; };
	public String getDob(){ return this.dob; };
	public String getAddress(){ return this.address; };
	public String getPostcode(){ return this.postcode; };

	// Setters
	public void setName(String _name){ this.name = _name; };
	public void setGender(char _gender){ this.gender = _gender; };
	public void setNatInscNo(String _natInscNo){ this.natInscNo = _natInscNo; };
	public void setDob(String _dob){ this.dob = _dob; };
	public void setAddress(String _address){ this.address = _address; };
	public void setPostcode(String _postcode){ this.postcode = _postcode; };
	
	
	
}
