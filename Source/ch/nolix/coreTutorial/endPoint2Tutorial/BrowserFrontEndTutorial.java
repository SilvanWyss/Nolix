//package declaration
package ch.nolix.coreTutorial.endPoint2Tutorial;

import ch.nolix.common.constants.IPv4Catalogue;
import ch.nolix.common.endPoint2.NetServer;
import ch.nolix.common.localComputer.ShellProvider;

//tutorial class
public final class BrowserFrontEndTutorial {
	
	//main method
	public static void main(String[] args) {
		
		//Creates net server.
		new NetServer();
		
		//Starts browser that will connect to the net server.
		ShellProvider.startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//private constructor
	private BrowserFrontEndTutorial() {}
}
