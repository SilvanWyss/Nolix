/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.clientserver;

import ch.nolix.base.net.clientserver.AbstractClient;
import ch.nolix.baseapi.net.executoranddataproviderserver.EndPoint;
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
   * Sets the {@link EndPoint} of the current {@link Client}.
   * 
   * @param endPoint
   * @throws RuntimeException if the given endPoint is null
   * @throws RuntimeException if the current {@link AbstractClient} is already
   *                          connected
   */
  void internalSetEndPoint(EndPoint endPoint);

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
