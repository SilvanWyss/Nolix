//package declaration
package ch.nolix.business.serverdashboardapplication;

//own imports
import ch.nolix.business.serverdashboardaccess.ServerDashboardAccess;
import ch.nolix.businessapi.serverdashboardaccessapi.IServerDashboardAccess;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClient;

//class
public final class ServerDashboardApplication
extends Application<BackendWebClient<IServerDashboardAccess>, IServerDashboardAccess> {
	
	//static method
	public static ServerDashboardApplication forServer(final Server server) {
		return new ServerDashboardApplication(ServerDashboardAccess.toServer(server));
	}
	
	//constant
	public static final String APPLICATION_NAME = "Server Dashboard";
	
	//constructor
	private ServerDashboardApplication(final IServerDashboardAccess serverDashboardAccess) {
		super(serverDashboardAccess);
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
