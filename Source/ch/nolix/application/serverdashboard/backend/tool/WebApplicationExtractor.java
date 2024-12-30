package ch.nolix.application.serverdashboard.backend.tool;

import ch.nolix.application.serverdashboard.backend.examiner.ApplicationExaminer;
import ch.nolix.applicationapi.serverdashboardapi.backendapi.examinerapi.IApplicationExaminer;
import ch.nolix.applicationapi.serverdashboardapi.backendapi.toolapi.IWebApplicationExtractor;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.application.main.AbstractServer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

/**
 * @author Silvan Wyss
 * @version 2023-11-19
 */
public final class WebApplicationExtractor implements IWebApplicationExtractor {

  private static final IApplicationExaminer APPLICATION_EXAMINER = new ApplicationExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<Application<WebClient<Object>, Object>> getStoredWebApplicationsOfServer(
    final AbstractServer<?> server) {

    if (server == null) {
      return ImmutableList.createEmpty();
    }

    return getStoredWebApplicationsOfServerWhenServerIsNotNull(server);
  }

  /**
   * @param server
   * @return the web {@link Application}s of the given server for the case that
   *         the given server is not null.
   */
  private IContainer<Application<WebClient<Object>, Object>> //
  getStoredWebApplicationsOfServerWhenServerIsNotNull( //NOSONAR: This method is an instance method.
    final AbstractServer<?> server) {

    final ILinkedList<Application<WebClient<Object>, Object>> webApplications = LinkedList.createEmpty();

    for (final var a : server.getStoredApplications()) {
      if (APPLICATION_EXAMINER.isWebApplication(a)) {

        @SuppressWarnings("unchecked")
        final var webApplication = (Application<WebClient<Object>, Object>) a;

        webApplications.addAtEnd(webApplication);
      }
    }

    return webApplications;
  }
}
