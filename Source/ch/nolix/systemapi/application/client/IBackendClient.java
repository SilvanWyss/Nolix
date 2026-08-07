/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.application.client;

import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of the parent application of a
 *            {@link IBackendClient}.
 */
public interface IBackendClient<S> extends Client {
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
