//package declaration
package ch.nolix.business.serverdashboardapplication;

import ch.nolix.business.serverdashboardapplication.view.ServerDashboardSession;
import ch.nolix.business.serverdashboardlogic.ServerDashboardContext;
import ch.nolix.businessapi.serverdashboardlogicapi.IServerDashboardContext;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class ServerDashboardApplication
extends Application<WebClient<IServerDashboardContext>, IServerDashboardContext> {
	
	//static method
	public static ServerDashboardApplication forServer(final BaseServer server) {
		return new ServerDashboardApplication(ServerDashboardContext.forServer(server));
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
