package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.util.ShellProvider;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;

/**
 * Of the {@link NetServerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-05
 */
public final class NetServerTutorial {
	
	/**
	 * 1. Creates a {@link NetServer}.
	 * 2. Creates a {@link FrontGUIClient} that will connect to the {@link NetServer}.
	 * 3. Starts a browser that will connect to the {@link NetServer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Defines a port.
		final int port = 50000;
		
		//Creates a NetServer, that will listen to net Clients on the default port,
		//and that has the created main application.
		final var netServer = new NetServer(port, new Application<>("Application", BackGUIClient.class, MainSession.class));
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		final var frontGUIClient = new FrontGUIClient(port);
		
		//Starts a browser that will connect to the NetServer.
		ShellProvider.startFirefox(port);
		
		Sequencer.waitForSeconds(5);
		
		netServer.close();
		frontGUIClient.close();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
			getRefGUI().setTitle("NetServer Tutorial");
			getRefGUI().addLayerOnTop(new Label("Hello World!"));
		}
	}
	
	/**
	 * Avoids that an instance of the {@link NetServerTutorial} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private NetServerTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
