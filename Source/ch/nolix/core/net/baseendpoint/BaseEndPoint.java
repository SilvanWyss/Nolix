package ch.nolix.core.net.baseendpoint;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.netapi.baseendpointapi.IBaseEndPoint;
import ch.nolix.coreapi.netapi.baseendpointapi.TargetSlotDefinition;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;

public abstract class BaseEndPoint implements IBaseEndPoint {

  private final CloseController closeController = CloseController.forElement(this);

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public final TargetSlotDefinition getTargetSlotDefinition() {

    if (hasCustomTargetSlot()) {
      return TargetSlotDefinition.CUSTOM;
    }

    return TargetSlotDefinition.DEFAULT;
  }

  @Override
  public final boolean hasDefaultTargetSlot() {
    return (getTargetSlotDefinition() == TargetSlotDefinition.DEFAULT);
  }

  @Override
  public final boolean isBackendEndPoint() {
    return (getPeerType() == PeerType.BACKEND);
  }

  @Override
  public final boolean isFrontendEndPoint() {
    return (getPeerType() == PeerType.FRONTEND);
  }

  @Override
  public final boolean isLocalEndPoint() {
    return (getConnectionType() == ConnectionType.LOCAL);
  }

  @Override
  public final boolean isSocketEndPoint() {
    return (getConnectionType() == ConnectionType.SOCKET);
  }

  @Override
  public final boolean isWebSocketEndPoint() {
    return (getConnectionType() == ConnectionType.WEB_SOCKET);
  }
}
