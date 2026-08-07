/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.baseendpoint;

import ch.nolix.baseapi.net.netattribute.ConnectionTypeHolder;
import ch.nolix.baseapi.net.netattribute.SecurityModeHolder;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * @author Silvan Wyss
 */
public interface BaseEndPoint extends ConnectionTypeHolder, GroupCloseable, SecurityModeHolder {
  String getCustomTargetSlot();

  PeerType getPeerType();

  TargetSlotDefinition getTargetSlotDefinition();

  boolean hasCustomTargetSlot();

  boolean hasDefaultTargetSlot();

  boolean isBackendEndPoint();

  boolean isFrontendEndPoint();

  boolean isLocalEndPoint();

  boolean isSocketEndPoint();

  boolean isWebSocketEndPoint();
}
