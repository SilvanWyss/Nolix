package ch.nolix.applicationapi.serverdashboardapi.backendapi.toolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;
import ch.nolix.systemapi.applicationapi.mainapi.IServer;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IWebApplicationExtractor {

  /**
   * @param server
   * @return the web applications of the given server.
   * @throws RuntimeException if the given server is null.
   */
  IContainer<? extends IApplication<Object>> getStoredWebApplicationsOfServer(IServer server);
}
