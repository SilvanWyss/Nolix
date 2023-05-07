//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.environment.runningjar.RunningJar;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.application.main.resource.ResourcePathCatalogue;

//class
record WebSocketServerHTMLPage(String serverDomain, int serverPort) {
	
	//constant
	private static final String REQUIRE_JS_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.REQUIRE_JS);
	
	//constant
	private static final String NOLIX_SCRIPT = RunningJar.getResource(ResourcePathCatalogue.NOLIX_JS);
	
	//constructor
	public WebSocketServerHTMLPage(final String serverDomain, final int serverPort) {
		
		GlobalValidator.assertThat(serverDomain).thatIsNamed("server domain").isNotBlank();
		GlobalValidator.assertThat(serverPort).thatIsNamed("server port").isPort();
		
		this.serverDomain = serverDomain;
		this.serverPort = serverPort;
	}
	
	//method
	@Override
	public String toString() {
		return
		"<!DOCTYPE html>\n"
		+ "<html>\n"
		+ "<head>\n"
		
		//The 'data:,' link avoids that a browser requests a favorite icon.
		+ "<link id=\"icon\" rel=\"icon\" href=\"data:,\">\n"
		
		+ "<script>\n"
		+ REQUIRE_JS_SCRIPT
		+ "</script>\n"
		+ "<script>\n"
		+ NOLIX_SCRIPT
		+ "</script>\n"
		+ getStartScript()
		+ "<title\n>"
		+ "Nolix\n"
		+ "</title>\n"
		+ "</head>\n"
		+ "<body>\n"
		+ "</body>\n"
		+ "</html>\n";
	}
	
	//method
	public String getServerDomain() {
		return serverDomain;
	}
	
	//method
	public int getServerPort() {
		return serverPort;
	}
	
	//method
	private String getStartScript() {
		return
		"<script>\n"
		+ "require(['System/Application/WebApplication/FrontendWebClient'], function (FrontendWebClient_) {"
		+ "var client = FrontendWebClient_.FrontendWebClient.toIpAndPortAndApplicationFromURLOnSecureWebSocket("
		+ getServerDomain()
		+ ", "
		+ getServerPort()
		+ ");"
		+ "});\n"
		+ "</script>\n";
	}
}
