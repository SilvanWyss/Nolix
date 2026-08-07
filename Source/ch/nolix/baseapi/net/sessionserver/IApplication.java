/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.sessionserver;

import ch.nolix.baseapi.manager.applicationmanager.ClientManager;
import ch.nolix.baseapi.net.target.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @param <S> the type of the application service of a {@link IApplication}.
 * @param <C> the type of the {@link IBackendClient}s of a {@link IApplication}.
 */
public interface IApplication<C extends IBackendClient<S>, S> extends ClientManager<C> {
  /**
   * @return a target representation of the current {@link IApplication}.
   */
  IApplicationInstanceTarget asTarget();

  /**
   * @return true if the current {@link IApplication} belongs to a server, false
   *         otherwise
   */
  boolean belongsToServer();

  /**
   * @return the application name of the current {IApplication}.
   */
  String getApplicationName();

  /**
   * @return the class of the {@link IBackendClient}s of the current
   *         {@link IApplication}.
   */
  Class<C> getClientClass();

  /**
   * @return the appendix that forms the instance name of the current
   *         {@link IApplication} when appended to the the application name of the
   *         current {@link IApplication}.
   */
  String getInstanceAppendix();

  /**
   * @return the instance name of the current {IApplication}.
   */
  String getInstanceName();

  /**
   * @return the application service of the current {@link IApplication}.
   */
  S getStoredApplicationService();

  /**
   * @return the instance name of the current {@link IApplication} for URLs.
   */
  String getUrlInstanceName();

  /**
   * @return true if the current {@link IApplication} has a {@link IBackendClient}
   *         connected, false otherwise
   */
  boolean hasClientConnected();

  /**
   * @return true if the current {@link IApplication} has an instance appendix,
   *         false otherwise
   */
  boolean hasInstanceAppendix();
}
