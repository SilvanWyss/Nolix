//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.endPoint.EndPoint;
import ch.nolix.core.endPoint.NetServer;
import ch.nolix.core.fileSystem.FileAccessor;
import ch.nolix.core.validator2.Validator;

//class
public final class BrowserEntryServer extends ClosableElement {
	
	//attributes
	private final NetServer netServer;
	private final String targetIP;
	private final int targetPort;
	private final String targetName;
	private final String HTTPText;
		
	//constructor
	public BrowserEntryServer(
		final int HTTPPort,
		final String targetIP,
		final int targetPort,
		final String targetName
	) {
		
		Validator
		.suppose(targetIP)
		.thatIsNamed("target IP")
		.isNotEmpty();
		
		Validator
		.suppose(targetPort)
		.thatIsNamed("target port")
		.isBetween(0, 65535);
		
		Validator
		.suppose(targetName)
		.thatIsNamed("target name")
		.isNotEmpty();
		
		this.targetIP = targetIP;
		this.targetPort = targetPort;
		this.targetName = targetName;
		HTTPText = createHTTPText();
		netServer = new NetServer(HTTPPort, ep -> takeEndPoint(ep));
	}
	
	//method
	@Override
	protected void noteClose() {
		netServer.close();
	}
	
	//method
	private String createBodyText() {
		return
		"<body onload='createFrontGUIClient("
		+ targetIP
		+ ", "
		+ targetPort
		+ ", "
		+ targetName
		+ ')'
		+ "</body>";
	}
	
	//method
	private String createHTMLText() {
		return
		"<html>"
		+ "<head>"
		+ "</head>"
		+ createJavaScriptText()
		+ createBodyText()
		+ "</html>";
	}
	
	//method
	private String createHTTPText() {
		return
		"HTTP/1.1 200 OK\r\n"
		+ "Content-Type: text/html; charset=UTF-8\r\n"
		+ "\r\n"
		+ createHTMLText();
	}
		
	//method
	private String createJavaScriptText() {
		return new FileAccessor("JavaScript/Specification.js").readFile();
	}
	
	//method
	private void takeEndPoint(final EndPoint endPoint) {
		endPoint.send(HTTPText);
		endPoint.close();
	}
}
