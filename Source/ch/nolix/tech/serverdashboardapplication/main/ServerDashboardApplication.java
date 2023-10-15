//package declaration
package ch.nolix.tech.serverdashboardapplication.main;

import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.tech.serverdashboardapplication.view.ServerDashboardSession;
import ch.nolix.tech.serverdashboardlogic.ServerDashboardContext;
import ch.nolix.techapi.serverdashboardlogicapi.IServerDashboardContext;

//class
public final class ServerDashboardApplication
    extends Application<WebClient<IServerDashboardContext>, IServerDashboardContext> {

  // constant
  public static final String APPLICATION_NAME = "Server Dashboard";

  // static method
  public static ServerDashboardApplication forServer(final BaseServer<?> server) {
    return new ServerDashboardApplication(ServerDashboardContext.forServer(server));
  }

  // constructor
  private ServerDashboardApplication(final IServerDashboardContext serverDashboardContext) {
    super(serverDashboardContext);
  }

  // method
  @Override
  public String getApplicationName() {
    return APPLICATION_NAME;
  }

  // method
  @Override
  protected Class<?> getInitialSessionClass() {
    return ServerDashboardSession.class;
  }
}
