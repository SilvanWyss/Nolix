/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.senderandreplierserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.senderandreplierserver.EndPoint;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;

/**
 * @author Silvan Wyss
 */
public final class MockSlot implements Slot {
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void takeBackendEndPoint(final EndPoint endPoint) {
    endPoint.setReplier(this::getReply);
  }

  private void assertHasReceivedMessage() {
    if (!hasReceivedMessage()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has not received a message");
    }
  }

  private String getReply(final String message) {
    Validator.assertThat(message).thatIsNamed(LowerCaseVariableNameCatalog.MESSAGE).isNotNull();

    latestReceivedMessage = message;

    return REPLY;
  }
}
