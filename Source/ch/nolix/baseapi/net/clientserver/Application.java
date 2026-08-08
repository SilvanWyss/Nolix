/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.manager.applicationmanager.ClientManager;
import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of a {@link Application}.
 * @param <C> the type of the {@link IBackendClient}s of a {@link Application}.
 */
public interface Application<C extends IBackendClient<S>, S> extends ClientManager<C> {
  /**
   * @return a target representation of the current {@link Application}.
   */
  IApplicationInstanceTarget asTarget();

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
   * @return the class of the {@link IBackendClient}s of the current
   *         {@link Application}.
   */
  Class<C> getClientClass();

  /**
   * @return the appendix that forms the instance name of the current
   *         {@link Application} when appended to the the application name of the
   *         current {@link Application}.
   */
  String getInstanceAppendix();

  /**
   * @return the instance name of the current {IApplication}.
   */
  String getInstanceName();

  /**
   * @return the application service of the current {@link Application}.
   */
  S getStoredApplicationService();

  /**
   * @return the instance name of the current {@link Application} for URLs.
   */
  String getUrlInstanceName();

  /**
   * @return true if the current {@link Application} has a {@link IBackendClient}
   *         connected, false otherwise
   */
  boolean hasClientConnected();

  /**
   * @return true if the current {@link Application} has an instance appendix,
   *         false otherwise
   */
  boolean hasInstanceAppendix();
}
