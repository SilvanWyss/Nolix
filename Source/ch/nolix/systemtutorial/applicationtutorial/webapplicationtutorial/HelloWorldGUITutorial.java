package ch.nolix.systemtutorial.applicationtutorial.webapplicationtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class HelloWorldGUITutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort(true);
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Hello World GUI tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendWebClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Creates Label.
			final var label = new Text().setText("Hello World");
			
			//Configures the style of the Label.
			label.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootControl(label);
		}
	}
	
	private HelloWorldGUITutorial() {}
}
