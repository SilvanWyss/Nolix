package ch.nolix.systemapi.application.client;

import ch.nolix.coreapi.net.netattribute.ISecuriyModeHolder;
import ch.nolix.coreapi.net.staterequest.ConnectionSideRequestable;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;

/**
 * @author Silvan Wyss
 * @version 2025-07-11
 */
public interface IClient extends ConnectionSideRequestable, GroupCloseable, ISecuriyModeHolder {

  /**
   * @return the URL instance name of the target application of the current
   *         {@link IClient}.
   * @throws RuntimeException if the current {@link IClient} does not know the URL
   *                          instance name of its target application.
   */
  String getUrlInstanceNameOfTargetApplication();

  /**
   * @return true if the current {@link IClient} has requested the connection,
   *         false otherwise.
   */
  boolean hasRequestedConnection();

  /**
   * @return true if the current {@link IClient} has the URL instance name of its
   *         target application, false otherwise.
   */
  boolean hasUrlInstanceNameOfTargetApplication();
}
