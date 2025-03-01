package ch.nolix.core.net.endpoint;

import java.util.function.Consumer;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.baseendpoint.AbstractEndPoint;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-05-06
 */
public abstract class EndPoint extends AbstractEndPoint implements IEndPoint {

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

    //Asserts that the given receiver is not null.
    GlobalValidator.assertThat(receiver).thatIsNamed(LowerCaseVariableCatalog.RECEIVER).isNotNull();

    //Asserts that the current EndPoint is open.
    assertIsOpen();

    //Sets the receiver of the current EndPoint.
    this.receiver = receiver;
  }

  /**
   * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
   */
  protected final void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * @return the receiver of the current {@link EndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a receiver.
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
   * Sets the custom target slot of the current {@link EndPoint}.
   * 
   * @param customTargetSlot
   * @throws ArgumentIsNullException  if the given customTargetSlot is null.
   * @throws InvalidArgumentException if the given customTargetSlot is blank.
   * @throws ClosedArgumentException  if the current {@link EndPoint} is closed.
   */
  protected final void setCustomTargetSlot(final String customTargetSlot) {

    //Asserts that the given customTargetSlot is not blank.
    GlobalValidator.assertThat(customTargetSlot).thatIsNamed("custom target slot").isNotBlank();

    //Asserts that the current EndPoint is open.
    assertIsOpen();

    //Sets the customTargetSlot of the current EndPoint.
    this.customTargetSlot = customTargetSlot;
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a custom target
   *                                               slot.
   */
  private void assertHasCustomTargetSlot() {
    if (!hasCustomTargetSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.TARGET);
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a receiver.
   */
  private void assertHasReceiver() {
    if (!hasReceiver()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.RECEIVER);
    }
  }
}
