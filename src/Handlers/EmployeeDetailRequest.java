package Handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Server.EmployeeDAO;
import Server.JSONConfirmationMessage;
import Server.JSONErrorMessage;

/**
 * EmployeeDetailRequest.java
 * -----------------------------
 * Handler for HTTP requests when the user requests information about
 * a specified Employee record or requests to delete a specified
 * Employee record from the server.
 * 
 * @author martin
 */

public class EmployeeDetailRequest implements HttpHandler {
	EmployeeDAO dao;
	
	public EmployeeDetailRequest (EmployeeDAO _dao){
		this.dao = _dao;
	}
	
	public void handle(HttpExchange t) throws IOException {
				
		switch (t.getRequestMethod()){
			case "GET": returnEmployeeRecord(t); break;
			case "DELETE": deleteEmployeeRecord(t); break;
			
			default: returnErrorCannotFind(t); break;	
		}
		
	}
	
	public void returnEmployeeRecord(HttpExchange t) throws IOException{
		String response = "";
		int statusCode = 200;
		
		try{
			int Employee_ID = -1;
			if(t.getRequestURI().toString().replace("/employees/", "") != ""){
				
				Employee_ID = Integer.parseInt(t.getRequestURI().toString().replace("/employees/", ""));
				
				// Check if record exists
				if(dao.selectEmployeeByID(Employee_ID).getName() != null){

					response = (String) new Gson().toJson(this.dao.selectEmployeeByID(Employee_ID));
					statusCode = 200;
					
				} else {
					returnErrorCannotFind(t);
				}
				
			} else {
				returnErrorCannotFind(t);
			}
		} catch (NumberFormatException e) {
			returnErrorCannotFind(t);
		}
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","application/json");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}
	
	public void deleteEmployeeRecord(HttpExchange t) throws IOException{
		String response = "";
		int statusCode = 200;
		
		try{
			int Employee_ID = -1;
			if(t.getRequestURI().toString().replace("/employees/", "") != ""){
				
				Employee_ID = Integer.parseInt(t.getRequestURI().toString().replace("/employees/", ""));
				
				// Check if record exists
				if(dao.selectEmployeeByID(Employee_ID).getName() != null){
					
					this.dao.deleteEmployeeById(Employee_ID);
					
					if(this.dao.selectEmployeeByID(Employee_ID).getName() == null){
						response = (String) new Gson().toJson(new JSONConfirmationMessage("Employee Record Deleted!"));
						statusCode = 200;
					}

				} else {
					returnErrorCannotFind(t);
				}
				
			} else {
				returnErrorCannotFind(t);
			}
		} catch (NumberFormatException e) {
			returnErrorCannotFind(t);
		}
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","application/json");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		
	}
	
	public void returnErrorCannotFind(HttpExchange t) throws IOException{
		String response = new Gson().toJson(new JSONErrorMessage("Cannot find the desired Employee record!"));
		int statusCode = 404;
		
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","application/json");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
