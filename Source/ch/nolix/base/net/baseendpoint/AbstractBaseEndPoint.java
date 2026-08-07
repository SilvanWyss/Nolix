/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.baseendpoint;

import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.net.endpoint.BaseEndPoint;
import ch.nolix.baseapi.net.endpoint.TargetSlotDefinitionType;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractBaseEndPoint implements BaseEndPoint {
  private final ICloseController closeController = CloseController.forElement(this);

  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final TargetSlotDefinitionType getTargetSlotDefinitionType() {
    if (hasCustomTargetSlot()) {
      return TargetSlotDefinitionType.CUSTOM;
    }

    return TargetSlotDefinitionType.DEFAULT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasDefaultTargetSlot() {
    return (getTargetSlotDefinitionType() == TargetSlotDefinitionType.DEFAULT);
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
