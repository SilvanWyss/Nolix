//package declaration
package ch.nolix.coretest.nettest.endpoint2test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpoint2api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class MockSlot implements ISlot {

  //constant
  public static final String REPLY = "reply";

  //constant
  private static final String NAME = "slot";

  //optional attribute
  private String latestReceivedMessage;

  //method
  @Override
  public String getName() {
    return NAME;
  }

  //method
  public String getLatestReceivedMessage() {

    assertHasReceivedMessage();

    return latestReceivedMessage;
  }

  //method
  public boolean hasReceivedMessage() {
    return (latestReceivedMessage != null);
  }

  //method
  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    endPoint.setReplier(this::getReply);
  }

  //method
  private void assertHasReceivedMessage() {
    if (!hasReceivedMessage()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has not received a message");
    }
  }

  //method
  private String getReply(final String message) {

    GlobalValidator.assertThat(message).thatIsNamed(LowerCaseVariableCatalogue.MESSAGE).isNotNull();

    latestReceivedMessage = message;

    return REPLY;
  }
}
