package ch.nolix.core.net.endpoint2;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
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

    Validator.assertThat(message).thatIsNamed(LowerCaseVariableCatalog.MESSAGE).isNotNull();

    latestReceivedMessage = message;

    return REPLY;
  }
}
