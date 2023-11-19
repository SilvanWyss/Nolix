//package declaration
package ch.nolix.tech.serverdashboard;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class WebApplicationExtractor {

  //method
  public IContainer<Application<WebClient<?>, ?>> getStoredWebApplicationsOfServer(final BaseServer<?> server) {

    final var webApplications = new LinkedList<Application<WebClient<?>, ?>>();

    for (final var a : server.getStoredApplications()) {
      if (isWebApplication(a)) {

        @SuppressWarnings("unchecked")
        final var webApplication = (Application<WebClient<?>, ?>) a;

        webApplications.addAtEnd(webApplication);
      }
    }

    return webApplications;
  }

  //method
  private boolean isWebApplication(final Application<?, ?> application) {
    return application != null
    && application.getClientClass() == WebClient.class;
  }
}
