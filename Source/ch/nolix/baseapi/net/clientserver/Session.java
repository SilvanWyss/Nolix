/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.base.net.clientserver.AbstractSession;
import ch.nolix.baseapi.generalstate.staterequest.AlivenessRequestable;
import ch.nolix.baseapi.objectcomposition.applicationcomponent.ClientComponent;
import ch.nolix.baseapi.programcontrol.refresh.Refreshable;

/**
 * @author Silvan Wyss
 * @param <C> the type of the parent {@link BackendClient} of a {@link Session}
 * @param <S> the type of the application service of the parent
 *            {@link Application} of the parent {@link BackendClient} of a
 *            {@link Session}
 */
public interface Session<C extends BackendClient<S>, S> extends AlivenessRequestable, ClientComponent<C>, Refreshable {
  /**
   * @return the name of the parent {@link Application} of the parent
   *         {@link BackendClient} of the current {@link Session}
   */
  String getApplicationName();

  /**
   * @return the client class of the current {@link Session}
   */
  Class<?> getClientClass();

  /**
   * @return the application service of the parent {@link Application} of the
   *         parent {@link BackendClient} of the current {@link Session}
   */
  S getStoredApplicationService();

  /**
   * @return true if the current {@link Session} has a frame {@link Session},
   *         false otherwise
   */
  boolean hasFrameSession();

  /**
   * Initializes the current {@link AbstractSession} fully.
   */
  void internalFullInitialize();

  /**
   * @return the result of the current {@link Session}
   * @throws RuntimeException if the current {@link Session} does not have a
   *                          result
   */
  Object internalGetStoredResult();

  /**
   * Removes the parent client from the current {@link Session} if the current
   * {@link Session} has a parent client.
   */
  void internalRemoveParentClient();

  /**
   * Sets the parent {@link BackendClient} of the current {@link Session}.
   * 
   * @param parentClient
   * @throws RuntimeException if the given parentClient is null
   * @throws RuntimeException if the current {@link Session} belongs already to a
   *                          {@link BackendClient}.
   */
  void internalSetParentClient(C parentClient);

  /**
   * Sets the result of the current {@link Session}.
   * 
   * @param result
   * @throws RuntimeException if the given result is null
   */
  void internalSetResult(Object result);

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
