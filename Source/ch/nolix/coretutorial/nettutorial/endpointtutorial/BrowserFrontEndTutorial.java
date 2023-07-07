package ch.nolix.coretutorial.nettutorial.endpointtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.net.endpoint.Server;

public final class BrowserFrontEndTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		new Server();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
	}
	
	private BrowserFrontEndTutorial() {}
}
