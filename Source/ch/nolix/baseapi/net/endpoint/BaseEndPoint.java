/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.endpoint;

import ch.nolix.baseapi.net.netattribute.ConnectionTypeHolder;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface BaseEndPoint extends ConnectionTypeHolder, GroupCloseable, SecurityModeHolder {
  /**
   * @return the custom target slot of the current {@link BaseEndPoint}
   * @throws RuntimeException if the current {@link BaseEndPoint} does not have a
   *                          custom target slot
   */
  String getCustomTargetSlot();

  /**
   * @return the {@link PeerType} of the current {@link BaseEndPoint}
   */
  PeerType getPeerType();

  /**
   * @return the {@link TargetSlotDefinitionType} of the current
   *         {@link BaseEndPoint}
   */
  TargetSlotDefinitionType getTargetSlotDefinitionType();

  /**
   * @return true if the current {@link BaseEndPoint} has a custom target slot,
   *         false otherwise
   */
  boolean hasCustomTargetSlot();

  /**
   * @return true if the current {@link BaseEndPoint} has the default target slot,
   *         false otherwise
   */
  boolean hasDefaultTargetSlot();

  /**
   * @return true if the current {@link BaseEndPoint} is a backend end point,
   *         false otherwise
   */
  boolean isBackendEndPoint();

  /**
   * @return true if the current {@link BaseEndPoint} is a frontend end point,
   *         false otherwise
   */
  boolean isFrontendEndPoint();

  /**
   * @return true if the current {@link BaseEndPoint} is a local end point, false
   *         otherwise
   */
  boolean isLocalEndPoint();

  /**
   * @return true if the current {@link BaseEndPoint} is a socket end point, false
   *         otherwise
   */
  boolean isSocketEndPoint();

  /**
   * @return true if the current {@link BaseEndPoint} is a web socket end point,
   *         false otherwise
   */
  boolean isWebSocketEndPoint();
}
