package ch.nolix.applicationapi.serverdashboardapi.backendapi.examinerapi;

import ch.nolix.system.application.main.Application;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public interface IApplicationExaminer {

  /**
   * @param application
   * @return true if the given application is a web {@link Application}, false
   *         otherwise.
   */
  boolean isWebApplication(Application<?, ?> application);
}
