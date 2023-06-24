//package declaration
package ch.nolix.business.serverdashboardapplication;

//own imports
import ch.nolix.business.serverdashboardcontext.ServerDashboardContext;
import ch.nolix.businessapi.serverdashboardcontextapi.IServerDashboardContext;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class ServerDashboardApplication
extends Application<WebClient<IServerDashboardContext>, IServerDashboardContext> {
	
	//static method
	public static ServerDashboardApplication forServer(final BaseServer server) {
		return new ServerDashboardApplication(ServerDashboardContext.toServer(server));
	}
	
	//constant
	public static final String APPLICATION_NAME = "Server Dashboard";
	
	//constructor
	private ServerDashboardApplication(final IServerDashboardContext serverDashboardContext) {
		super(serverDashboardContext);
	}
	
	//method
	@Override
	public String getApplicationName() {
		return APPLICATION_NAME;
	}
	
	//method
	@Override
	protected Class<?> getInitialSessionClass() {
		return ServerDashboardSession.class;
	}
}
