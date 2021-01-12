package ch.nolix.systemtutorial.consoleclienttutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.consoleclient.BackConsoleClientSession;
import ch.nolix.system.consoleclient.FrontConsoleClient;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLook;

public final class WebConsoleClientTutorial {

	public static void main(final String[] arguments) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("WebConsoleClient Tutorial", MainSession.class);
		
		//Creates a FrontConsoleClient that will connect to the NetServer.
		new FrontConsoleClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackConsoleClientSession {
		
		@Override
		protected void initializeBackConsoleClientSession() {
			
			this.setLook(new BlackRedConsoleClientLook());
			
			writeLineToConsole("Hello.", "Press q to quit the program.");
			while (readCharacterFromConsole() != 'q');			
			pop();
		}
	}
	
	private WebConsoleClientTutorial() {}
}
