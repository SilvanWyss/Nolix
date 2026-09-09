/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.net.target.IApplicationTarget;
import ch.nolix.baseapi.objectcomposition.applicationmanager.ClientManager;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of a {@link Application}.
 * @param <C> the type of the {@link BackendClient}s of a {@link Application}.
 */
public interface Application<C extends BackendClient<S>, S> extends ClientManager<C> {
  /**
   * @return a target representation of the current {@link Application}.
   */
  IApplicationTarget asTarget();

  /**
   * @return true if the current {@link Application} belongs to a server, false
   *         otherwise
   */
  boolean belongsToServer();

  /**
   * @return the application name of the current {IApplication}.
   */
  String getApplicationName();

  /**
   * @return the class of the {@link BackendClient}s of the current
   *         {@link Application}.
   */
  Class<C> getClientClass();

  /**
   * @return the application service of the current {@link Application}.
   */
  S getStoredApplicationService();

  /**
   * @return the instance name of the current {@link Application} for URLs.
   */
  String getUrlApplicationName();

  /**
   * @return true if the current {@link Application} has a {@link BackendClient}
   *         connected, false otherwise
   */
  boolean hasClientConnected();
}
