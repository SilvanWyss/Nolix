//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.environment.runningjar.RunningJar;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.system.client.base.resource.ResourcePathCatalogue;

//class
final class NetServerHTTPMessage {
	
	//constants
	private static final String REQUIRE_JS_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.REQUIRE_JS);
	private static final String NOLIX_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.NOLIX_JS);
	
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
		
		//The 'data:,' link avoids that a browser requests a favorite icon.
		+ "<link id=\"icon\" rel=\"icon\" href=\"data:,\">\n"
		
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
		+ "require(['System/FrontCanvasGUIClient/FrontCanvasGUIClient'], function (FrontCanvasGUIClient_) {"
		+ "var client = FrontCanvasGUIClient_.FrontCanvasGUIClient.withIpAndNumberAndWindow("
		+ getServerIpInQuotes() + ", "
		+ getServerPort() + ", "
		+ "window);"
		+ "});\n"
		+ "</script>\n";
	}
}
