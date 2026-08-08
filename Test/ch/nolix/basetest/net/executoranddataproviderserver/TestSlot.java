/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.executoranddataproviderserver;

import ch.nolix.baseapi.net.executoranddataproviderserver.IEndPoint;
import ch.nolix.baseapi.net.executoranddataproviderserver.ISlot;

/**
 * @author Silvan Wyss
 */
public final class TestSlot implements ISlot {
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
  public void takeBackendEndPoint(final IEndPoint backendEndPoint) {
    latestCreatedReceivingDataProviderController = new TestReceivingDataProviderController();

    backendEndPoint.setExecutorAndDataProvider(latestCreatedReceivingDataProviderController);
  }
}
