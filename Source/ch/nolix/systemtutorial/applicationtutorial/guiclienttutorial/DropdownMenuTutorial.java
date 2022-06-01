package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.widget.DropdownMenu;

public final class DropdownMenuTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = Server.forDefaultPort();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("DropdownMenu tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontCanvasGUIClient that will connect to the NetServer.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Creates a DropdownMenu.
			final var dropdownMenu =
			new DropdownMenu()
			.addItem(
				"Gottfried Wilhelm Leibniz",
				"Immanuel Kant",
				"Johann Gottlieb Fichte",
				"Georg Wilehlm Friedrich Hegel",
				"Arthur Schopenhauer",
				"Johann Gottfried Herder",
				"Karl Marx",
				"Friedrich Nietzsche",
				"Ludwig Wittgenstein",
				"Theodor W. Adorno"
			);
			
			//Adds the DropdownMenu to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(dropdownMenu);
		}
	}
	
	private DropdownMenuTutorial() {}
}
