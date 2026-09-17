/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface Client extends GroupCloseable, SecurityModeHolder {
  /**
   * @return the name of the target application of the current {@link Client} for
   *         URLs
   * @throws RuntimeException if the name of the target application of the current
   *                          {@link Client} is unknown
   */
  String getTargetApplicationUrlName();

  /**
   * @return true if the current {@link Client} has requested the connection,
   *         false otherwise
   */
  boolean hasRequestedConnection();

  /**
   * @return true if the current {@link Client} is a back-end client, false
   *         otherwise
   */
  boolean isBackendClient();

  /**
   * @return true if the current {@link Client} is a front-end client, false
   *         otherwise
   */
  boolean isFrontendClient();

  /**
   * @return true if the current {@link Client} has the URL instance name of its
   *         target application, false otherwise
   */
  boolean hasUrlInstanceNameOfTargetApplication();
}
