package ch.nolix.core.net.baseendpoint;

import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.net.baseendpoint.IBaseEndPoint;
import ch.nolix.coreapi.net.baseendpoint.TargetSlotDefinition;
import ch.nolix.coreapi.net.netproperty.ConnectionType;
import ch.nolix.coreapi.net.netproperty.PeerType;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;

public abstract class AbstractBaseEndPoint implements IBaseEndPoint {
  private final ICloseController closeController = CloseController.forElement(this);

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final TargetSlotDefinition getTargetSlotDefinition() {
    if (hasCustomTargetSlot()) {
      return TargetSlotDefinition.CUSTOM;
    }

    return TargetSlotDefinition.DEFAULT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasDefaultTargetSlot() {
    return (getTargetSlotDefinition() == TargetSlotDefinition.DEFAULT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isBackendEndPoint() {
    return (getPeerType() == PeerType.BACKEND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFrontendEndPoint() {
    return (getPeerType() == PeerType.FRONTEND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isLocalEndPoint() {
    return (getConnectionType() == ConnectionType.LOCAL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isSocketEndPoint() {
    return (getConnectionType() == ConnectionType.SOCKET);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isWebSocketEndPoint() {
    return (getConnectionType() == ConnectionType.WEB_SOCKET);
  }
}
