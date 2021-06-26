package ch.nolix.commontutorial.endpointtutorial;

//own imports
import ch.nolix.common.constant.IPv4Catalogue;
import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.net.endpoint.Server;

public final class BrowserFrontEndTutorial {
	
	public static void main(String[] args) {
		
		//Creates net server.
		new Server();
		
		//Starts browser that will connect to the net server.
		ShellProvider.startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	private BrowserFrontEndTutorial() {}
}
