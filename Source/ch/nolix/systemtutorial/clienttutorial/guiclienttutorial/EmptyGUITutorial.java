package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.system.client.base.NetServer;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontCanvasGUIClient;

public final class EmptyGUITutorial {
	
	public static void main(String[] args) {
				
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Empty GUI tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontCanvasGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {}
	}
	
	private EmptyGUITutorial() {}
}
