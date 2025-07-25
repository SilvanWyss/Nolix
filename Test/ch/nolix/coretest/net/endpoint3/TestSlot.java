package ch.nolix.coretest.net.endpoint3;

import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;

public final class TestSlot implements ISlot {

  private TestReceivingDataProviderController latestCreatedReceivingDataProviderController;

  public TestReceivingDataProviderController getLatestCreatedReceivingDataProviderController() {
    return latestCreatedReceivingDataProviderController;
  }

  @Override
  public String getName() {
    return "test_slot";
  }

  @Override
  public void takeBackendEndPoint(final IEndPoint backendEndPoint) {

    latestCreatedReceivingDataProviderController = new TestReceivingDataProviderController();

    backendEndPoint.setReceivingDataProviderController(latestCreatedReceivingDataProviderController);
  }
}
