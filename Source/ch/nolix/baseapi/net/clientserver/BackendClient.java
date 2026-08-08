/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of the parent application of a
 *            {@link BackendClient}.
 */
public interface BackendClient<S> extends Client {
  /**
   * @return the application of the current {@link BackendClient} as target.
   */
  IApplicationInstanceTarget getApplicationAsTarget();

  /**
   * @return the application service of the parent application of the current
   *         {@link BackendClient}.
   */
  S getStoredApplicationService();
}
