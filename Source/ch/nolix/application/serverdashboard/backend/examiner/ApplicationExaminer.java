package ch.nolix.application.serverdashboard.backend.examiner;

import ch.nolix.applicationapi.serverdashboardapi.backendapi.examinerapi.IApplicationExaminer;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.webapplication.WebClient;
import ch.nolix.systemapi.applicationapi.mainapi.IApplication;

/**
 * @author Silvan Wyss
 * @version 2024-12-30
 */
public final class ApplicationExaminer implements IApplicationExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isWebApplication(final IApplication<?> application) {
    return //
    application != null
    && application instanceof Application<?, ?> localApplication
    && localApplication.getClientClass() == WebClient.class;
  }
}
