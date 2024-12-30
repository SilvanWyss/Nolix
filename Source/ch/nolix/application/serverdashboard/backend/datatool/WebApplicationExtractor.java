package ch.nolix.application.serverdashboard.backend.datatool;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.application.main.AbstractServer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

public final class WebApplicationExtractor {

  public IContainer<Application<WebClient<Object>, Object>> getStoredWebApplicationsOfServer(
    final AbstractServer<?> server) {

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

  private boolean isWebApplication(final Application<?, ?> application) {
    return application != null
    && application.getClientClass() == WebClient.class;
  }
}
