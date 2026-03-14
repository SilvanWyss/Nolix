/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.level3server;

import ch.nolix.baseapi.net.level3server.IEndPoint;
import ch.nolix.baseapi.net.level3server.ISlot;

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

    backendEndPoint.setReceivingDataProviderController(latestCreatedReceivingDataProviderController);
  }
}
