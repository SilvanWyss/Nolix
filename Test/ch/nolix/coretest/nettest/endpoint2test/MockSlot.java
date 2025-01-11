package ch.nolix.coretest.nettest.endpoint2test;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpoint2api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class MockSlot implements ISlot {

  public static final String REPLY = "reply";

  private static final String NAME = "slot";

  private String latestReceivedMessage;

  @Override
  public String getName() {
    return NAME;
  }

  public String getLatestReceivedMessage() {

    assertHasReceivedMessage();

    return latestReceivedMessage;
  }

  public boolean hasReceivedMessage() {
    return (latestReceivedMessage != null);
  }

  @Override
  public void takeBackendEndPoint(final IEndPoint endPoint) {
    endPoint.setReplier(this::getReply);
  }

  private void assertHasReceivedMessage() {
    if (!hasReceivedMessage()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has not received a message");
    }
  }

  private String getReply(final String message) {

    GlobalValidator.assertThat(message).thatIsNamed(LowerCaseVariableCatalog.MESSAGE).isNotNull();

    latestReceivedMessage = message;

    return REPLY;
  }
}
