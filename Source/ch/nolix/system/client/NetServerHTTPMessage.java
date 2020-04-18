//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.runningJar.RunningJar;
import ch.nolix.common.validator.Validator;

//class
final class NetServerHTTPMessage {
	
	//constant
	private final String REQUIRE_JS_SCRIPT =
	RunningJar.getResource("ch/nolix/system/client/resources/require_js.js");
	
	//constant
	private final String NOLIX_SCRIPT =
	RunningJar.getResource("ch/nolix/system/client/resources/Nolix.js");
	
	//attributes
	private final String serverIP;
	private final int serverPort;
	
	//constructor
	public NetServerHTTPMessage(final String serverIP, final int serverPort) {
		
		Validator.assertThat(serverIP).thatIsNamed("server IP").isNotBlank();
		Validator.assertThat(serverPort).thatIsNamed("server port").isBetween(0, 65535);
		
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
		+ "<!DOCTYPE html>\n"
		+ "<html>\n"
		+ "<head>\n"
		+ "<script>\n"
		+ REQUIRE_JS_SCRIPT
		+ "</script>\n"
		+ "<script>\n"
		+ NOLIX_SCRIPT +
		"</script>\n"
		+ getMainScript()
		+ "<title\n>"
		+ "Nolix\n"
		+ "</title>\n"
		+ "</head>\n"
		+ "<body>\n"
		+ "</body>\n"
		+ "</html>\r\n";
	}
	
	//method
	private String getMainScript() {
		return
		"<script>\n"
		+ "require(['System/FrontBrowserGUIClient/FrontBrowserGUIClient'], function (FrontBrowserGUIClient_) {"
		+ "var client = FrontBrowserGUIClient_.FrontBrowserGUIClient.withIpAndNumberAndWindow("
		+ getServerIpInQuotes() + ", "
		+ getServerPort() + ", "
		+ "window);"
		+ "});\n"
		+ "</script>\n";
	}
}
