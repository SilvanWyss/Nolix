package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.application.guiclient.BackendGUIClientSession;
import ch.nolix.system.application.guiclient.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class EmptyGUITutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
				
		//Creates a NetServer.
		final var netServer = Server.forDefaultPort();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Empty GUI tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {}
	}
	
	private EmptyGUITutorial() {}
}
