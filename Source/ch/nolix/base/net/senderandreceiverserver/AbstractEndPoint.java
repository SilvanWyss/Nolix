/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import java.util.function.Consumer;

import ch.nolix.base.net.baseendpoint.AbstractBaseEndPoint;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.senderandreceiverserver.EndPoint;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractEndPoint extends AbstractBaseEndPoint implements EndPoint {
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  private String customTargetSlot;

  private Consumer<String> receiver;

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getCustomTargetSlot() {
    assertHasCustomTargetSlot();

    return customTargetSlot;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasCustomTargetSlot() {
    return (customTargetSlot != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasReceiver() {
    return (receiver != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setReceiver(final Consumer<String> receiver) {
    // Asserts that the given receiver is not null.
    Validator.assertThat(receiver).thatIsNamed(LowerCaseVariableNameCatalog.RECEIVER).isNotNull();

    // Asserts that the current EndPoint is open.
    assertIsOpen();

    // Sets the receiver of the current EndPoint.
    this.receiver = receiver;
  }

  /**
   * @throws RuntimeException if the current {@link AbstractEndPoint} is closed
   */
  protected final void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the receiver of the current {@link AbstractEndPoint}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractEndPoint} does
   *                                               not have a receiver.
   */
  protected final Consumer<String> getStoredReceiver() {
    if (hasReceiver()) {
      return receiver;
    }

    FlowController.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasReceiver);

    assertHasReceiver();

    return receiver;
  }

  /**
   * Sets the custom target slot of the current {@link AbstractEndPoint}.
   * 
   * @param customTargetSlot
   * @throws RuntimeException if the given customTargetSlot is null
   * @throws RuntimeException if the given customTargetSlot is blank
   * @throws RuntimeException if the current {@link AbstractEndPoint} is closed
   */
  protected final void setCustomTargetSlot(final String customTargetSlot) {
    // Asserts that the given customTargetSlot is not blank.
    Validator.assertThat(customTargetSlot).thatIsNamed("custom target slot").isNotBlank();

    // Asserts that the current EndPoint is open.
    assertIsOpen();

    // Sets the customTargetSlot of the current EndPoint.
    this.customTargetSlot = customTargetSlot;
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractEndPoint} does
   *                                               not have a custom target slot.
   */
  private void assertHasCustomTargetSlot() {
    if (!hasCustomTargetSlot()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.TARGET);
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractEndPoint} does
   *                                               not have a receiver.
   */
  private void assertHasReceiver() {
    if (!hasReceiver()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.RECEIVER);
    }
  }
}
