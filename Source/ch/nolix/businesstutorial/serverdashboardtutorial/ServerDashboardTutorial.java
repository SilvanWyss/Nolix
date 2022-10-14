package ch.nolix.businesstutorial.serverdashboardtutorial;

import ch.nolix.business.serverdashboard.ServerDashboard;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.system.application.guiapplication.GUIApplicationContext;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.gui.main.GUIIconCatalogue;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemtutorial.applicationtutorial.webapplicationtutorial.ImageControlTutorial;

public final class ServerDashboardTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort(true);
		
		server.addDefaultApplication(ServerDashboard.forServer(server));
		
		server.addApplication(
			"HelloWorld",
			HelloWorldSession.class,
			new GUIApplicationContext().setApplicationLogo(GUIIconCatalogue.NOLIX_ICON)
		);
		
		server.addApplication(
			"ImageControl tutorial",
			ImageControlTutorial.MainSession.class,
			VoidApplicationContext.INSTANCE
		);
				
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
	}
	
	private static final class HelloWorldSession extends BackendWebClientSession<GUIApplicationContext> {
		
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
}
