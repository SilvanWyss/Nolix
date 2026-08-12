/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.executoranddataproviderserver;

import ch.nolix.base.net.baseendpoint.AbstractBaseEndPoint;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.executoranddataproviderserver.EndPoint;
import ch.nolix.baseapi.net.executoranddataproviderserver.ExecutorAndDataProvider;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractEndPoint extends AbstractBaseEndPoint implements EndPoint {
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private ExecutorAndDataProvider receiverController;

  AbstractEndPoint() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasExecutorAndDataProvider() {
    return (receiverController != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void runCommands(ChainedNode... commands) {
    for (final var c : commands) {
      runCommand(c);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setExecutorAndDataProvider(final ExecutorAndDataProvider receiverController) {
    // Asserts that the given receiverController is not null.
    Validator.assertThat(receiverController).thatIsNamed("receiver controller").isNotNull();

    // Sets the receiver controller of the current EndPoint.
    this.receiverController = receiverController;
  }

  /**
   * @throws RuntimeException if the current {@link AbstractEndPoint} is closed
   */
  protected void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the receiver controller of the current {@link AbstractEndPoint}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractEndPoint} does
   *                                               not have a receiver controller.
   */
  ExecutorAndDataProvider getStoredReceiverController() {
    if (hasExecutorAndDataProvider()) {
      return receiverController;
    }

    FlowController
      .forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS)
      .waitUntil(this::hasExecutorAndDataProvider);

    if (!hasExecutorAndDataProvider()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.RECEIVER);
    }

    return receiverController;
  }
}
