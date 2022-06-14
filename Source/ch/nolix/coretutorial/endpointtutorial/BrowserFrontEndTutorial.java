package ch.nolix.coretutorial.endpointtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.net.endpoint.Server;

public final class BrowserFrontEndTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates net server.
		new Server();
		
		//Starts browser that will connect to the net server.
		ShellProvider.startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	private BrowserFrontEndTutorial() {}
}
