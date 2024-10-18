package ch.nolix.tech.serverdashboard;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.techapi.serverdashboardapi.IServerDashboardContext;
import ch.nolix.techapi.serverdashboardapi.IWebApplicationSheet;

public final class ServerDashboardContext implements IServerDashboardContext {

  private static final WebApplicationExtractor WEB_APPLICATION_EXTRACTOR = new WebApplicationExtractor();

  private final BaseServer<?> server;

  private ServerDashboardContext(final BaseServer<?> server) {

    GlobalValidator.assertThat(server).thatIsNamed(LowerCaseVariableCatalogue.SERVER).isNotNull();

    this.server = server;
  }

  public static ServerDashboardContext forServer(final BaseServer<?> server) {
    return new ServerDashboardContext(server);
  }

  @Override
  public IContainer<IWebApplicationSheet> getWebApplicationSheets() {
    return getStoredWebApplications().to(WebApplicationSheet::forWebApplication);
  }

  private IContainer<Application<WebClient<Object>, Object>> getStoredWebApplications() {
    return WEB_APPLICATION_EXTRACTOR.getStoredWebApplicationsOfServer(server);
  }
}
