package ch.nolix.businesstutorial.serverdashboardtutorial;

import ch.nolix.business.serverdashboardapplication.ServerDashboardApplication;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebApplicationContext;
import ch.nolix.system.gui.iconresource.IconCatalogue;
import ch.nolix.systemtutorial.webguitutorial.controltutorial.ImageControlTutorial;
import ch.nolix.systemtutorial.webguitutorial.itemmenututorial.DropdownMenuTutorial;
import ch.nolix.systemtutorial.webguitutorial.maintutorial.HelloWorldGUITutorial;

public final class ServerDashboardTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Creates a ServerDashboardApplication for the Server.
		final var serverDashboardApplication = ServerDashboardApplication.forServer(server);
		
		//Adds the ServerDashboardApplication as default Application to the Server.
		server.addDefaultApplication(serverDashboardApplication);
		
		//Adds further Applications to the Server.
		server.addApplication(
			"HelloWorld",
			HelloWorldGUITutorial.MainSession.class,
			new WebApplicationContext().setApplicationLogo(IconCatalogue.NOLIX_ICON)
		);
		server.addApplication(
			"ImageControl tutorial",
			ImageControlTutorial.MainSession.class,
			new VoidObject()
		);
		server.addApplication(
			"DropdownMenu tutorial",
			DropdownMenuTutorial.MainSession.class,
			new VoidObject()
		);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
	}
}
