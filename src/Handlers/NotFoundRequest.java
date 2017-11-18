package Handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Server.JSONErrorMessage;

/**
 * NotFoundRequest.java
 * -----------------------------
 * Handler for redirected HTTP requests when an appropriate
 * URL mask is not matched with the requested URL.
 * 
 * @author martin
 */

public class NotFoundRequest implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {
	
		String response = new Gson().toJson(new JSONErrorMessage("Invalid REST API Route!"));
		int statusCode = 404;
		Headers h = t.getResponseHeaders();
		h.set("Content-Type","application/json");
		t.sendResponseHeaders(statusCode, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();

	}

}
