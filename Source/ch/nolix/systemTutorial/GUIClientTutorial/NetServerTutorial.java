package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.widgets.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;

/**
 * A {@link NetServerTutorial} is a tutorial for {@link NetServer}s.
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
		//and that has the given main application.
		new NetServer(port, new Application<>("Application", BackGUIClient.class, MainSession.class));
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient(port);
		
		//Starts a browser that will connect to the NetServer.
		ShellProvider.startFirefox(port);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
			internal_getRefGUI()
			.setTitle("NetServer Tutorial")
			.addLayerOnTop(new Label("Hello World!").applyOnBaseLook(bl -> bl.setTextSize(50)));
		}
	}
	
	/**
	 * Avoids that an instance of the {@link NetServerTutorial} can be created.
	 */
	private NetServerTutorial() {}
}
