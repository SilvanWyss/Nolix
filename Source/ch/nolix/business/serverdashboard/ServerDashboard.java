//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.business.serverdashboardaccess.ServerDashboardAccess;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.system.application.guiapplication.BackendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;

//class
public final class ServerDashboard
extends Application<BackendGUIClient<IServerDashboardAccess>, IServerDashboardAccess> {
	
	//static method
	public static ServerDashboard forServer(final Server server) {
		return new ServerDashboard(ServerDashboardAccess.toServer(server));
	}
	
	//constant
	public static final String NAME = "Server Dashboard";
	
	//constructor
	private ServerDashboard(final IServerDashboardAccess serverDashboardAccess) {
		super(NAME, ServerDashboardSession.class, serverDashboardAccess);
	}
}
