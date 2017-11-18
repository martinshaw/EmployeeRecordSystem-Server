package Server;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * BuildHTMLPageResponse.java
 * -----------------------------
 * Basic helper class which reads the contents of HTML files into a formatted string,
 * returning a default error page when the requested page is not found.
 * 
 * @author martin
 */

public class BuildHTMLPageResponse {
	public String file_name = "";
	
	// Default error page contents to be returned when the
	// requested page is not found in the file system.
	public String page_contents = "<html><body><h1>Error processing HTML file!</h1></body></html>";
	
	public BuildHTMLPageResponse(String _file_name) throws IOException{
		this.file_name = _file_name;
		
		// Read bytes from file into a text string
		byte[] encoded = Files.readAllBytes(Paths.get(file_name));
		this.page_contents = new String(encoded, Charset.defaultCharset());
	}
	public String getContents (){
		return this.page_contents;	
	}
	
}
