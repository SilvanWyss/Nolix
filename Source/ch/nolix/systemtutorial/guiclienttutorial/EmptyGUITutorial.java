package ch.nolix.systemtutorial.guiclienttutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.guiclient.BackGUIClientSession;
import ch.nolix.system.guiclient.FrontGUIClient;

public final class EmptyGUITutorial {
	
	public static void main(String[] args) {
				
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Empty GUI Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			//Sets the background color of the GUI of the current MainSession.
			getRefGUI().setBackgroundColor(Color.LAVENDER);
		}
	}
	
	private EmptyGUITutorial() {}
}
