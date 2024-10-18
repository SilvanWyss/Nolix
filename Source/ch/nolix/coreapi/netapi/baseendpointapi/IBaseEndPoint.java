package ch.nolix.coreapi.netapi.baseendpointapi;

import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

public interface IBaseEndPoint extends GroupCloseable {

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
