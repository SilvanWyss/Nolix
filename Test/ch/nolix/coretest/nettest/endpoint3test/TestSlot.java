//package declaration
package ch.nolix.coretest.nettest.endpoint3test;

//own imports
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;

//class
public final class TestSlot implements ISlot {

  //optional attribute
  private TestReceivingDataProviderController latestCreatedReceivingDataProviderController;

  //method
  public TestReceivingDataProviderController getLatestCreatedReceivingDataProviderController() {
    return latestCreatedReceivingDataProviderController;
  }

  //method
  @Override
  public String getName() {
    return "test_slot";
  }

  //method
  @Override
  public void takeBackendEndPoint(final IEndPoint backendEndPoint) {

    latestCreatedReceivingDataProviderController = new TestReceivingDataProviderController();

    backendEndPoint.setReceivingDataProviderController(latestCreatedReceivingDataProviderController);
  }
}
