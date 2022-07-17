package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class HelloWorldGUIOnCustomPortTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a Server.
		final var server = Server.forPort(port);
		
		//Adds a default Application to the Server.
		server.addDefaultApplication(
			"Hello World GUI on custom port tutorial",
			MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient(port);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress(port);
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Creates Label.
			final var label = new Label().setText("Hello World");
			
			//Configures the look of the Label.
			label.getRefActiveLook().setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(label);
		}
	}
	
	private HelloWorldGUIOnCustomPortTutorial() {}
}
