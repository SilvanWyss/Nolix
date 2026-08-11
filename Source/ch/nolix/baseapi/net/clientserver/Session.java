/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.generalstate.staterequest.AlivenessRequestable;
import ch.nolix.baseapi.objectcomposition.applicationcomponent.ClientComponent;
import ch.nolix.baseapi.programcontrol.refresh.Refreshable;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link BackendClient} of a {@link Session}
 * @param <S> the type of the application service of the parent
 *            {@link Application} of the parent {@link BackendClient} of a
 *            {@link Session}
 */
public interface Session<C extends BackendClient<S>, S> extends AlivenessRequestable, ClientComponent<C>, Refreshable {
  /**
   * @return the name of the parent {@link Application} of the parent
   *         {@link BackendClient} of the current {@link Session}.
   */
  String getApplicationName();

  /**
   * @return the application service of the parent {@link Application} of the
   *         parent {@link BackendClient} of the current {@link Session}.
   */
  S getStoredApplicationService();

  /**
   * @return true if the current {@link Session} has a underlying {@link Session},
   *         false otherwise
   */
  boolean hasUnderlyingSession();

  /**
   * Pops the current {@link Session} from its parent {@link BackendClient}.
   */
  void pop();

  /**
   * Pops the current {@link Session} from its parent {@link BackendClient} with
   * the given result.
   * 
   * @param result
   * @throws RuntimeException if the given result is null
   */
  void popWithResult(Object result);
}
