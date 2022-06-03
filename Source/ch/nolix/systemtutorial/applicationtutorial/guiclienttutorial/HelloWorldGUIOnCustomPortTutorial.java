package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;

public final class HelloWorldGUIOnCustomPortTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a NetServers.
		final var netServer = Server.forPort(port);
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication(
			"Hello World GUI on custom port tutorial",
			MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontendGUIClient(port);
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress(port);
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Creates Label.
			final var label = new Label().setText("Hello World");
			
			//Configures the look of the Label.
			label.getRefActiveLook().setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(label);
		}
	}
	
	private HelloWorldGUIOnCustomPortTutorial() {}
}
