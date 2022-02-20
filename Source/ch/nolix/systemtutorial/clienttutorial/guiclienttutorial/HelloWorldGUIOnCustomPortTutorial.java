package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public final class HelloWorldGUIOnCustomPortTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a NetServers.
		final var netServer = new Server(port);
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Hello World GUI on custom port tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient(port);
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress(port);
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			//Creates Label.
			final var label = new Label().setText("Hello World");
			
			//Configures the look of the Label.
			label.getRefLook().setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(label);
		}
	}
	
	private HelloWorldGUIOnCustomPortTutorial() {}
}
