/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.application.client;

import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.staterequest.ConnectionSideRequestable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface IClient extends ConnectionSideRequestable, GroupCloseable, SecurityModeHolder {
  /**
   * @return the URL instance name of the target application of the current
   *         {@link IClient}
   * @throws RuntimeException if the current {@link IClient} does not know the URL
   *                          instance name of its target application.
   */
  String getUrlInstanceNameOfTargetApplication();

  /**
   * @return true if the current {@link IClient} has requested the connection,
   *         false otherwise
   */
  boolean hasRequestedConnection();

  /**
   * @return true if the current {@link IClient} has the URL instance name of its
   *         target application, false otherwise
   */
  boolean hasUrlInstanceNameOfTargetApplication();
}
