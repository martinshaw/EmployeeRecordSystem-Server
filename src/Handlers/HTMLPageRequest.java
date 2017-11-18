package Handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Server.BuildHTMLPageResponse;
import Server.Employee;
import Server.EmployeeDAO;
import Server.JSONConfirmationMessage;
import Server.JSONErrorMessage;

/**
 * HTMLPageRequest.java
 * -----------------------------
 * Handler for HTTP requests when the user requests the HTML form or it's
 * processing script, which allows for inserting a new Employee record into
 * the Database.
 * 
 * @author martin
 */

public class HTMLPageRequest implements HttpHandler {
	EmployeeDAO dao;
	
	public HTMLPageRequest (EmployeeDAO _dao){
		this.dao = _dao;
	}
	

	public void handle(HttpExchange t) throws IOException {
				
		switch (t.getRequestMethod()){
			case "GET": returnHTMLAddEmployeesPage(t); break; 	// Get All Employees Data
			case "POST": processHTMLAddEmployeesPage(t); break;
			
			default: returnError(t); break;
		}
		
	}
	
	public void returnHTMLAddEmployeesPage(HttpExchange t) throws IOException{		
		String response = (String) new BuildHTMLPageResponse("add_employee.html").getContents();
		int statusCode = 200;
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","text/html");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	@SuppressWarnings("unchecked")
	public void processHTMLAddEmployeesPage(HttpExchange t) throws IOException{
		String response = (String) new Gson().toJson(new JSONErrorMessage("Could not create the employee record!"));
		int statusCode = 404;
		Map<String, String> params = (Map<String, String>) t.getAttribute("parameters");
		
		
		
		Employee e = new Employee();
		
		if(params.get("name") != null && params.get("name") != "") { e.setName(params.get("name")); }			
		if(params.get("gender") != null && params.get("gender") != "") { e.setGender((char) params.get("gender").charAt(0)); }
		if(params.get("natInscNo") != null && params.get("natInscNo") != "") { e.setNatInscNo(params.get("natInscNo")); }
		if(params.get("dob") != null && params.get("dob") != "") { e.setDob(params.get("dob")); }
		if(params.get("address") != null && params.get("address") != "") { e.setAddress(params.get("address")); }
		if(params.get("postcode") != null && params.get("postcode") != "") { e.setPostcode(params.get("postcode")); }
		if(params.get("salary") != null && params.get("salary") != "") { e.setSalary(params.get("salary")); }
		if(params.get("startDate") != null && params.get("startDate") != "") { e.setStartDate(params.get("startDate")); }
		if(params.get("title") != null && params.get("title") != "") { e.setTitle(params.get("title")); }
		if(params.get("email") != null && params.get("email") != "") { e.setEmail(params.get("email")); }
		
		dao.insertEmployee(e);

		response = (String) new BuildHTMLPageResponse("process_employee.html").getContents();
		statusCode = 200;
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","text/html");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
	
	public void returnError(HttpExchange t) throws IOException{
		String response = new Gson().toJson(new JSONErrorMessage("Cannot respond with list of Employee records!"));
		int statusCode = 404;
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","application/json");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
