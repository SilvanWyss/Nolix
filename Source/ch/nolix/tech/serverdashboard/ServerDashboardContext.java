//package declaration
package ch.nolix.tech.serverdashboard;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

//class
public final class ServerDashboardContext implements IServerDashboardContext {

  //constant
  private static final WebApplicationExtractor WEB_APPLICATION_EXTRACTOR = new WebApplicationExtractor();

  //attribute
  private final BaseServer<?> server;

  //constructor
  private ServerDashboardContext(final BaseServer<?> server) {

    GlobalValidator.assertThat(server).thatIsNamed(LowerCaseVariableCatalogue.SERVER).isNotNull();

    this.server = server;
  }

  //static method
  public static ServerDashboardContext forServer(final BaseServer<?> server) {
    return new ServerDashboardContext(server);
  }

  //method
  @Override
  public IContainer<IWebApplicationSheet> getWebApplicationSheets() {
    return getStoredWebApplications().to(WebApplicationSheet::forWebApplication);
  }

  //method
  private IContainer<Application<WebClient<?>, ?>> getStoredWebApplications() {
    return WEB_APPLICATION_EXTRACTOR.getStoredWebApplicationsOfServer(server);
  }
}
