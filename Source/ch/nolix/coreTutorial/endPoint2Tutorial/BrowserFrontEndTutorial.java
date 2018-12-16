//package declaration
package ch.nolix.coreTutorial.endPoint2Tutorial;

//own imports
import ch.nolix.core.constants.IPv4Catalogue;
import ch.nolix.core.endPoint2.NetServer;
import ch.nolix.core.util.ShellProvider;

//tutorial class
public final class BrowserFrontEndTutorial {
	
	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates net server.
		new NetServer();
		
		//Starts browser that will connect to the net server.
		ShellProvider.startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//private constructor
	private BrowserFrontEndTutorial() {}
}
