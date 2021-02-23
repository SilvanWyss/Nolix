package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.widget.Uploader;
import ch.nolix.system.client.base.NetServer;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public class UploaderTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Uploader Tutorial", MainSession.class);
		
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
			getRefGUI().addLayerOnTop(new Uploader());
		}
	}
}
