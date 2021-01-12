package ch.nolix.systemtutorial.guiclienttutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.guiclient.BackGUIClientSession;
import ch.nolix.system.guiclient.FrontCanvasGUIClient;

public final class DropdownMenuTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("DropdownMenu Tutorial", MainSession.class);
		
		//TODO: Add expansion state of DropdownMenu to its specification.
		//Creates a FrontCanvasGUIClient that will connect to the NetServer.
		new FrontCanvasGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
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
			
			//Configures the look of the DropdownMenu.
			dropdownMenu.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBackgroundColor(Color.LAVENDER)
				.setItemPadding(5)
			);
			
			//Adds the DropdownMenu to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(dropdownMenu);
		}
	}
	
	private DropdownMenuTutorial() {}
}
