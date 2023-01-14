//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.business.serverdashboardaccess.ServerDashboardAccess;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClient;

//class
public final class ServerDashboard
extends Application<BackendWebClient<IServerDashboardAccess>, IServerDashboardAccess> {
	
	//static method
	public static ServerDashboard forServer(final Server server) {
		return new ServerDashboard(ServerDashboardAccess.toServer(server));
	}
	
	//constant
	public static final String APPLICATION_NAME = "Server Dashboard";
	
	//constructor
	private ServerDashboard(final IServerDashboardAccess serverDashboardAccess) {
		super(APPLICATION_NAME, serverDashboardAccess);
	}
	
	//method
	@Override
	public String getApplicationName() {
		return APPLICATION_NAME;
	}
	
	//method
	@Override
	protected Class<?> getRefInitialSessionClass() {
		return ServerDashboardSession.class;
	}
}
