package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class HelloWorldGUITutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("Hello World GUI Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxToLoopBackAddress();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
			
			//Creates Label.
			final var label = new Label("Hello World");
			
			//Configures the look of the Label.
			label.applyOnBaseLook(bl -> bl.setTextSize(50));
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(label);
		}
	}
	
	private HelloWorldGUITutorial() {}
}
