package ch.nolix.businesstutorial.serverdashboardtutorial;

import ch.nolix.business.serverdashboard.ServerDashboard;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.system.application.guiapplication.GUIApplicationContext;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.gui.icon.IconCatalogue;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemtutorial.webguitutorial.controltutorial.ImageControlTutorial;

public final class ServerDashboardTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(ServerDashboard.forServer(server));
		
		server.addApplication(
			"HelloWorld",
			HelloWorldSession.class,
			new GUIApplicationContext().setApplicationLogo(IconCatalogue.NOLIX_ICON)
		);
		
		server.addApplication(
			"ImageControl tutorial",
			ImageControlTutorial.MainSession.class,
			VoidApplicationContext.INSTANCE
		);
				
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
	}
	
	private static final class HelloWorldSession extends BackendWebClientSession<Object> {
		
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
