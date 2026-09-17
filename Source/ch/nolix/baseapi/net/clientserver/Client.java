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
   * @throws RuntimeException if the current {@link Client} does not have a name
   *                          of a target application for URLs
   */
  String getTargetApplicationUrlName();

  /**
   * @return true if the current {@link Client} has a name of a target application
   *         for URLs, false otherwise
   */
  boolean hasTargetApplicationUrlName();

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
}
