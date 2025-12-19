package ch.nolix.systemapi.application.client;

import ch.nolix.coreapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @param <S> is the type of the application service of the parent application
 *            of a {@link IBackendClient}.
 */
public interface IBackendClient<S> extends IClient {
  /**
   * @return the application of the current {@link IBackendClient} as target.
   */
  IApplicationInstanceTarget getApplicationAsTarget();

  /**
   * @return the application service of the parent application of the current
   *         {@link IBackendClient}.
   */
  S getStoredApplicationService();
}
