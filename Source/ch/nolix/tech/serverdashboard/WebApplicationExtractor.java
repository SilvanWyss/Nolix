//package declaration
package ch.nolix.tech.serverdashboard;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;
import ch.nolix.system.application.webapplication.WebClient;

//class
public final class WebApplicationExtractor {

  //method
  public IContainer<Application<WebClient<Object>, Object>> getStoredWebApplicationsOfServer(
    final BaseServer<?> server) {

    final ILinkedList<Application<WebClient<Object>, Object>> webApplications = LinkedList.createEmpty();

    for (final var a : server.getStoredApplications()) {
      if (isWebApplication(a)) {

        @SuppressWarnings("unchecked")
        final var webApplication = (Application<WebClient<Object>, Object>) a;

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
