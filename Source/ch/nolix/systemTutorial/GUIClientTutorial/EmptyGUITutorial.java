package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.color.Color;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class EmptyGUITutorial {
	
	public static void main(String[] args) {
				
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("Empty GUI Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxToLoopBackAddress();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
			
			//Sets the background color of the GUI of the current MainSession.
			getRefGUI().setBackgroundColor(Color.LAVENDER);
		}
	}
	
	private EmptyGUITutorial() {}
}
