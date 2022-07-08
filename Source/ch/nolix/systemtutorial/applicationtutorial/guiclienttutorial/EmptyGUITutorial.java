package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class EmptyGUITutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
				
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Empty GUI tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			//Does nothing.
		}
	}
	
	private EmptyGUITutorial() {}
}
