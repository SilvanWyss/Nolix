/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of the parent application of a
 *            {@link BackendClient}.
 */
public interface BackendClient<S> extends Client {
  /**
   * @return the application of the current {@link BackendClient} as target.
   */
  IApplicationTarget getApplicationAsTarget();

  /**
   * @return the size of the session stack of the current {@link BackendClient}
   */
  int getSessionStackSize();

  /**
   * @return the application service of the parent application of the current
   *         {@link BackendClient}.
   */
  S getStoredApplicationService();

  /**
   * Pops the current {@link Session} of the current {@link BackendClient} from
   * the current {@link BackendClient}. Closes the current {@link BackendClient}
   * if the current {@link Session} of the current {@link BackendClient} was the
   * last {@link Session} of the current {@link BackendClient}.
   * 
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link BackendClient} is not the top
   *                           {@link Session} of the current
   *                           {@link BackendClient}.
   */
  void internalPopCurrentSession();
}
