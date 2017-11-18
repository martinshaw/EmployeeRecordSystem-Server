package Server;

/**
 * JSONConfirmationMessage.java
 * -----------------------------
 * Basic class to be instantiated then casted into JSON,
 * for use to return a confirmation message or status message
 * in place of data from the server.
 * 
 * @author martin
 */

public class JSONConfirmationMessage {
	public boolean confirm = true;
	public String confirm_message = "";
	
	public JSONConfirmationMessage(String _confirm_message){
		this.confirm_message = _confirm_message;
	}
}
