package ch.nolix.applicationapi.serverdashboardapi.backendapi.toolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.application.main.AbstractServer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IWebApplicationExtractor {

  /**
   * @param server
   * @return the web {@link Application}s of the given server.
   * @throws RuntimeException if the given server is null.
   */
  IContainer<Application<WebClient<Object>, Object>> getStoredWebApplicationsOfServer(AbstractServer<?> server);
}
