package Server;
import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * Controller.java
 * -----------------------------
 * Starting point of the Java Server program.
 * 
 * Creates a DB DAO object which is used throughout the project,
 * then creates a HTTP Server bound to a Socket Port of 8005,
 * then defines URL Routes and the methods to be triggered when navigated to,
 * finally apply a middleware filter which interprets GET & POST variables
 * into a list for later use.
 * 
 * @author martin
 */

public class Controller {
	
	static EmployeeDAO dao;
	static ArrayList<HttpContext> routes = new ArrayList<HttpContext>();
	
	public static void main(String[] args){
		
		// Setup SQLite JDBC Connection
		dao = new EmployeeDAO();
		dao.getConnection();
		
		
		// Setup Web REST API Server
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(8005), 0);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Port 8005 is busy!");
			System.exit(1);
		}
		
		// Define REST API Routes
		routes.add(server.createContext("/", new Handlers.EmployeesRequest(dao)));
		routes.add(server.createContext("/employees", new Handlers.EmployeesRequest(dao)));
		routes.add(server.createContext("/employees/", new Handlers.EmployeeDetailRequest(dao))); // /employee/{int id}
		
		// Define HTML View Routes
		routes.add(server.createContext("/add_employee", new Handlers.HTMLPageRequest(dao)));
		routes.add(server.createContext("/process_employee", new Handlers.HTMLPageRequest(dao)));
		
		// Apply Get/Post Parameter Parsing Filter to each of the Routes
		for(HttpContext c: routes){
			c.getFilters().add(new Filters.GetPostParameterParser());
		}
		
		// Start Server
		server.setExecutor(null); // default implementation of threading
		server.start();
		System.out.println("The server is up and running on port 8005");
		
	}
	
	

	
	
}


