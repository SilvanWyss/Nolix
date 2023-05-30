package ch.nolix.systemtutorial.webguitutorial.controltutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class TextboxTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Textbox tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a Textbox.
			final var textBox = new Textbox();
						
			//Configures the style of the Textbox.
			textBox
			.getOriStyle()
			.setBorderThicknessForState(ControlState.BASE, 1);
			
			//Adds the Textbox to the GUI of the current MainSession.
			getOriGUI().pushLayerWithRootControl(textBox);
		}
	}
	
	private TextboxTutorial() {}
}
