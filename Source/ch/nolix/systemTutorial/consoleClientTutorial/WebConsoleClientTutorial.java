package ch.nolix.systemTutorial.consoleClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.consoleClient.BackConsoleClientSession;
import ch.nolix.system.consoleClient.FrontConsoleClient;
import ch.nolix.template.consoleClientLook.BlackRedConsoleClientLook;

public final class WebConsoleClientTutorial {

	public static void main(final String[] arguments) {
		
		//Creates a NetServer with an Application for BackConsoleClients.
		final var netServer = new NetServer("WebConsoleClientTutorial", MainSession.class);
		
		//Creates a FrontConsoleClient that will connect to the NetServer.
		new FrontConsoleClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackConsoleClientSession {
		
		@Override
		protected void initializeStage3() {
			
			this.setLook(new BlackRedConsoleClientLook());
			
			writeLineToConsole("Hello.", "Press q to quit the program.");
			while (readCharacterFromConsole() != 'q') {}
			pop();
		}
	}
	
	private WebConsoleClientTutorial() {}
}
