//package declaration
package ch.nolix.business.serverdashboardapplication;

import ch.nolix.business.serverdashboardcontext.ApplicationContext;
import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationContext;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClient;

//class
public final class ServerDashboardApplication
extends Application<BackendWebClient<IApplicationContext>, IApplicationContext> {
	
	//static method
	public static ServerDashboardApplication forServer(final Server server) {
		return new ServerDashboardApplication(ApplicationContext.toServer(server));
	}
	
	//constant
	public static final String APPLICATION_NAME = "Server Dashboard";
	
	//constructor
	private ServerDashboardApplication(final IApplicationContext applicationContext) {
		super(applicationContext);
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
