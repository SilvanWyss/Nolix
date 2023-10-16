//package declaration
package ch.nolix.core.net.endpoint;

import java.util.function.Consumer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.baseendpoint.BaseEndPoint;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.netapi.baseendpointapi.TargetSlotDefinition;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;

//class
/**
 * A {@link EndPoint} can send messages to an other {@link EndPoint} of the same
 * type. A {@link EndPoint} is closable.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public abstract class EndPoint extends BaseEndPoint implements IEndPoint {

  //constant
  private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;

  //optional attribute
  private String target;

  //optional attribute
  private Consumer<String> receiver;

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * @return the target of the current {@link EndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a target.
   */
  @Override
  public final String getCustomTargetSlot() {

    //Asserts that the current EndPoint has a target.
    if (this.target == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.TARGET);
    }

    return target;
  }

  //method
  @Override
  public final TargetSlotDefinition getTargetSlotDefinition() {

    if (target == null) {
      return TargetSlotDefinition.DEFAULT;
    }

    return TargetSlotDefinition.CUSTOM;
  }

  //method
  /**
   * @return true if the current {@link EndPoint} has a receiver.
   */
  @Override
  public final boolean hasReceiver() {
    return (receiver != null);
  }

  //method
  /**
   * Sets the receiver of the current {@link EndPoint}.
   * 
   * @param receiver
   * @throws ArgumentIsNullException if the given receiver is null.
   * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
   */
  @Override
  public final void setReceiver(final Consumer<String> receiver) {

    //Asserts that the given receiver is not null.
    GlobalValidator.assertThat(receiver).thatIsNamed(LowerCaseCatalogue.RECEIVER).isNotNull();

    //Asserts that the current EndPoint is open.
    assertIsOpen();

    //Sets the receiver of the current EndPoint.
    this.receiver = receiver;
  }

  //method
  /**
   * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
   */
  protected final void assertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  //method
  /**
   * @return the receiver of the current {@link EndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a receiver.
   */
  protected final Consumer<String> getStoredReceiver() {

    if (hasReceiver()) {
      return receiver;
    }

    GlobalSequencer.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasReceiver);

    assertHasReceiver();

    return receiver;
  }

  //method
  /**
   * Sets the target of the current {@link EndPoint}.
   * 
   * @param target
   * @throws ArgumentIsNullException  if the given target is null.
   * @throws InvalidArgumentException if the given target is blank.
   * @throws ClosedArgumentException  if the current {@link EndPoint} is closed.
   */
  protected final void setTarget(final String target) {

    //Asserts that the given target is not null or blank.
    GlobalValidator.assertThat(target).thatIsNamed(LowerCaseCatalogue.TARGET).isNotBlank();

    //Asserts that the current net EndPoint is open.
    assertIsOpen();

    //Sets the target of the current EndPoint.
    this.target = target;
  }

  //method
  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint}
   *                                               does not have a receiver.
   */
  private void assertHasReceiver() {
    if (!hasReceiver()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.RECEIVER);
    }
  }
}
