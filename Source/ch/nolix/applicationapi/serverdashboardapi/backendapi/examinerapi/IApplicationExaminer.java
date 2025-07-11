package ch.nolix.applicationapi.serverdashboardapi.backendapi.examinerapi;

import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IApplicationExaminer {

  /**
   * @param application
   * @return true if the given application is a web {@link IApplication}, false
   *         otherwise.
   */
  boolean isWebApplication(IApplication<?, ?> application);
}
