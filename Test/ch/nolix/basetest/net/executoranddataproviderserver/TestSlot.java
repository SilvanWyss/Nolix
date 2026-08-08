/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.executoranddataproviderserver.EndPoint;
import ch.nolix.baseapi.net.executoranddataproviderserver.Slot;

/**
 * @author Silvan Wyss
 */
public final class TestSlot implements Slot {
  private TestReceivingDataProviderController latestCreatedReceivingDataProviderController;

  public TestReceivingDataProviderController getLatestCreatedReceivingDataProviderController() {
    return latestCreatedReceivingDataProviderController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "test_slot";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void takeBackendEndPoint(final EndPoint backendEndPoint) {
    latestCreatedReceivingDataProviderController = new TestReceivingDataProviderController();

    backendEndPoint.setExecutorAndDataProvider(latestCreatedReceivingDataProviderController);
  }
}
