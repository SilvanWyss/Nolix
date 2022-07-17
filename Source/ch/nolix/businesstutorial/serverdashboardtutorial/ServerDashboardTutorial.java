package ch.nolix.businesstutorial.serverdashboardtutorial;

//own imports
import ch.nolix.business.serverdashboard.ServerDashboard;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.guiapplication.GUIApplicationContext;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.main.GUIIconCatalogue;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class ServerDashboardTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(ServerDashboard.forServer(server));
		
		server.addApplication(
			"HelloWorld",
			HelloWorldSession.class,
			new GUIApplicationContext().setApplicationLogo(GUIIconCatalogue.NOLIX_ICON)
		);
		
		server.addApplication(
			"Time",
			ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial.LabelTutorial.MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		server.addApplication(
			"CursorPosition",
			ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial.CursorPositionTutorial.MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		server.addApplication(
			"Uploader",
			ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial.UploaderTutorial.MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class HelloWorldSession extends BackendGUIClientSession<GUIApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Creates Label.
			final var label = new Label().setText("Hello World");
			
			//Configures the look of the Label.
			label.onLook(l -> l.setTextSizeForState(ControlState.BASE, 50));
			
			//Adds the Label to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(label);
		}
	}
}
