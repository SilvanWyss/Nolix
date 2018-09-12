//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.endPoint.EndPoint;
import ch.nolix.core.endPoint.NetServer;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.client.Application;

//class
public final class BrowserEntryServer {
	
	//attributes
	private final NetServer netServer;
	private final Application<?> targetApplication;	
	
	//constructor
	public BrowserEntryServer(final int HTTPPort) {
		this(HTTPPort, new SampleApplication());
	}
	
	//constructor
	public BrowserEntryServer(
		final int HTTPPort,
		final Application<?> targetApplication
	) {
		
		Validator
		.suppose(targetApplication)
		.thatIsNamed("target application")
		.isInstance();
		
		netServer = new NetServer(HTTPPort, ep -> takeEndPoint(ep));
		this.targetApplication = targetApplication;
	}
	
	//method
	private void takeEndPoint(final EndPoint endPoint) {
		
		//TODO
		endPoint.send(
			"HTTP/1.1 200 OK\r\n"
			+ "Content-Type: text/html; charset=UTF-8\r\n"
			+ "\r\n"
			+ "<p>Please wait...</p>"
		);
	}
}
