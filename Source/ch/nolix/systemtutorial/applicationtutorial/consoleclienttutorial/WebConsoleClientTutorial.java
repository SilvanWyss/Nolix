package ch.nolix.systemtutorial.applicationtutorial.consoleclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.application.consoleclient.BackendConsoleClientSession;
import ch.nolix.system.application.consoleclient.FrontendConsoleClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLookCreator;

public final class WebConsoleClientTutorial {

	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		
		//Creates a NetServer.
		final var netServer = Server.forDefaultPort();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("WebConsoleClient tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontConsoleClient that will connect to the NetServer.
		new FrontendConsoleClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackendConsoleClientSession<VoidApplicationContext> {
		
		@Override
		protected void initializeBackConsoleClientSession() {
			
			this.setLook(new BlackRedConsoleClientLookCreator().createClientLook());
			
			writeLineToConsole("Hello.", "Press q to quit the program.");
			while (readCharacterFromConsole() != 'q');			
			pop();
		}
	}
	
	private WebConsoleClientTutorial() {}
}
