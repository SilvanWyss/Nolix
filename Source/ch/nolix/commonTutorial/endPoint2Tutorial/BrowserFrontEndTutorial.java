package ch.nolix.commonTutorial.endPoint2Tutorial;

import ch.nolix.common.constant.IPv4Catalogue;
import ch.nolix.common.endpoint2.NetServer;
import ch.nolix.common.localcomputer.ShellProvider;

public final class BrowserFrontEndTutorial {
	
	public static void main(String[] args) {
		
		//Creates net server.
		new NetServer();
		
		//Starts browser that will connect to the net server.
		ShellProvider.startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	private BrowserFrontEndTutorial() {}
}
