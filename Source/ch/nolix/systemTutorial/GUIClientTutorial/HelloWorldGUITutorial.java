package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;

public final class HelloWorldGUITutorial {
	
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer(port, new Application<>("Hello World GUI Tutorial", BackGUIClient.class, MainSession.class));
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient(port);
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefox(port);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
			getRefGUI().addLayerOnTop(new Label("Hello World!").applyOnBaseLook(bl -> bl.setTextSize(50)));
		}
	}
	
	private HelloWorldGUITutorial() {}
}
