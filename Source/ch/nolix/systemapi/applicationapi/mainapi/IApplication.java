package ch.nolix.systemapi.applicationapi.mainapi;

import ch.nolix.coreapi.netapi.targetapi.IApplicationInstanceTarget;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 * @param <S>
 */
public interface IApplication<S> {

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
   * @return true if the current {@link IApplication} has an instance appendix,
   *         false otherwise.
   */
  boolean hasInstanceAppendix();
}
