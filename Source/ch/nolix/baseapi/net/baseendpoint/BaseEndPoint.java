/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.baseendpoint;

import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface BaseEndPoint extends GroupCloseable {
  ConnectionType getConnectionType();

  String getCustomTargetSlot();

  PeerType getPeerType();

  SecurityMode getSecurityMode();

  TargetSlotDefinition getTargetSlotDefinition();

  boolean hasCustomTargetSlot();

  boolean hasDefaultTargetSlot();

  boolean isBackendEndPoint();

  boolean isFrontendEndPoint();

  boolean isLocalEndPoint();

  boolean isSocketEndPoint();

  boolean isWebSocketEndPoint();
}
