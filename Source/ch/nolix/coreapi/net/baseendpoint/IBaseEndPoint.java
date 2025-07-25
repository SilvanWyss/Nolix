package ch.nolix.coreapi.net.baseendpoint;

import ch.nolix.coreapi.net.netproperty.ConnectionType;
import ch.nolix.coreapi.net.netproperty.PeerType;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrol.resourceclosing.GroupCloseable;

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
