/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.net.target.IApplicationTarget;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link BackendClient}
 * @param <S> the type of the application service of the parent application of a
 *            {@link BackendClient}.
 */
public interface BackendClient<C extends BackendClient<C, S>, S> extends Client {
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

  /**
   * Pops the current {@link Session} of the current {@link BackendClient} from
   * the current {@link BackendClient} with the given result. Closes the current
   * {@link BackendClient} if the current {@link Session} of the current
   * {@link BackendClient} was the last {@link Session} of the current
   * {@link BackendClient}.
   * 
   * @param result
   * @InvalidArgumentException if the current {@link Session} of the current
   *                           {@link BackendClient} is not the top
   *                           {@link Session} of the current
   *                           {@link BackendClient}
   */
  void internalPopCurrentSessionWithResult(Object result);

  /**
   * Pushes the given session to the current {@link BackendClient}.
   * 
   * @param session
   * @throws RuntimeException if the given session is null
   */
  void internalPushSession(Session<C, S> session);
}
