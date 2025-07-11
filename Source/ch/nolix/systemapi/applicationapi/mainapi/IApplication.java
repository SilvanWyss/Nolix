package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 * @param <S> is the type of the application service of a {@link IApplication}.
 * @param <C> is the type of the {@link IBackendClient}s of a
 *            {@link IApplication}.
 */
public interface IApplication<C extends IBackendClient<S>, S> {

  /**
   * @return a target representation of the current {@link IApplication}.
   */
  IApplicationInstanceTarget asTarget();

  /**
   * @return true if the current {@link IApplication} belongs to a server, false
   *         otherwise.
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
   *         connected, false otherwise.
   */
  boolean hasClientConnected();

  /**
   * @return true if the current {@link IApplication} has an instance appendix,
   *         false otherwise.
   */
  boolean hasInstanceAppendix();
}
