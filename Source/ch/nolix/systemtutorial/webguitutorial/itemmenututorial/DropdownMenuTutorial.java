package ch.nolix.systemtutorial.webguitutorial.itemmenututorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;

public final class DropdownMenuTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("DropdownMenu tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a DropdownMenu.
			final var dropdownMenu =
			new DropdownMenu()
			.addItemWithText(
				"red",
				"blue",
				"green",
				"yellow",
				"orange",
				"purple"
			);
			
			//Adds the DropdownMenu to the GUI of the current MainSession.
			getOriGUI().pushLayerWithRootControl(dropdownMenu);
		}
	}
	
	private DropdownMenuTutorial() {}
}
