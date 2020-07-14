package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class HelloWorldGUIOnCustomPortTutorial {
	
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a NetServer with an Application for BackGUIClients.
		final var netServer = new NetServer(port, "Hello World GUI on custom port Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient(port);
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefox(port);
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
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
	
	private HelloWorldGUIOnCustomPortTutorial() {}
}
