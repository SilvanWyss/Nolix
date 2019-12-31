//package declaration
package ch.nolix.system.client;

//own import
import ch.nolix.common.validator.Validator;

//class
final class NetServerHTTPMessage {
	
	//attributes
	private final String serverIP;
	private final int serverPort;
	
	//constructor
	public NetServerHTTPMessage(final String serverIP, final int serverPort) {
		
		Validator.suppose(serverIP).thatIsNamed("server IP").isNotBlank();
		Validator.suppose(serverPort).thatIsNamed("server port").isBetween(0, 65535);
		
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}
	
	//method
	public String getServerIP() {
		return serverIP;
	}
	
	//method
	public String getServerIpInQuotes() {
		return "'" + getServerIP() + "'";
	}
	
	//method
	public int getServerPort() {
		return serverPort;
	}
	
	//method
	@Override
	public String toString() {
		return
		"HTTP/1.1 200 OK\r\n"
		+ "Content-Type: text/html; charset=UTF-8\r\n"
		+ "\r\n"
		+ "<!DOCTYPE html>"
		+ "<html>"
		+ "<head>"
		+ "<script src=\"http://www.nolix.ch/Launcher/require.js\"></script>"
		+ "<script src=\"http://www.nolix.ch/Launcher/nolix.js\"></script>"
		+ getMainScript()
		+ "<title>Nolix</title>"
		+ "</head>"
		+ "<body></body>"
		+ "</html>\r\n";
	}
	
	//method
	private String getMainScript() {
		return
		"<script>"
		+ "require(['System/FrontBrowserGUIClient/FrontBrowserGUIClient'], function (FrontBrowserGUIClient_) {"
		+ "alert('point 1');"
		+ "var client = FrontBrowserGUIClient_.FrontBrowserGUIClient.withIpAndNumberAndWindow("
		+ getServerIpInQuotes() + ", "
		+ getServerPort() + ", "
		+ "window);"
		+ "alert('point 2');"
		+ "});"
		+ "</script>";
	}
}
