/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.sessionserver;

import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.staterequest.ConnectionSideRequestable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface Client extends ConnectionSideRequestable, GroupCloseable, SecurityModeHolder {
  /**
   * @return the URL instance name of the target application of the current
   *         {@link Client}
   * @throws RuntimeException if the current {@link Client} does not know the URL
   *                          instance name of its target application.
   */
  String getUrlInstanceNameOfTargetApplication();

  /**
   * @return true if the current {@link Client} has requested the connection,
   *         false otherwise
   */
  boolean hasRequestedConnection();

  /**
   * @return true if the current {@link Client} has the URL instance name of its
   *         target application, false otherwise
   */
  boolean hasUrlInstanceNameOfTargetApplication();
}
