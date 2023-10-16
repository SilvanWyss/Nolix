//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

//class
/**
 * A {@link NetEndPoint} can send messages to an other {@link NetEndPoint} that
 * is on: -the same process on the local computer -another process on the local
 * computer -another process on another computer
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public abstract class NetEndPoint extends EndPoint {

  //constant
  private static final String RAW_MESSAGE_VARIABLE_NAME = "raw message";

  //attribute
  private boolean hasTargetInfo;

  //constructor
  /**
   * Creates a new {@link NetEndPoint} with the given connectionOrigin and
   * connectionOrigin.
   * 
   * @param connectionOrigin
   * @param targetInfoState
   * @throws ArgumentIsNullException if the given connectionOrigin is null.
   * @throws ArgumentIsNullException if the given targetInfoState is null.
   */
  NetEndPoint(final TargetInfoState targetInfoState) {

    //Asserts that the given targetInfoState is not null.
    GlobalValidator.assertThat(targetInfoState).thatIsNamed(TargetInfoState.class).isNotNull();

    if (targetInfoState == TargetInfoState.RECEIVED_TARGET_INFO) {
      confirmReceivedTargetInfo();
    }
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} with the given connectionOrigin and target.
   * 
   * @param connectionOrigin
   * @param target
   * @throws ArgumentIsNullException  if the given connectionOrigin is null.
   * @throws ArgumentIsNullException  if the given target is null.
   * @throws InvalidArgumentException if the given target is blank.
   */
  NetEndPoint(final String target) {

    //Calls constructor of the base class.
    setTarget(target);

    confirmReceivedTargetInfo();
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} send the given message.
   * 
   * @param message
   */
  @Override
  public final void sendMessage(final String message) {
    sendRawMessage(NetEndPointProtocol.MESSAGE_PREFIX + message);
  }

  //method
  /**
   * @return true if the current {@link NetEndPoint} has a target info.
   */
  protected final boolean hasTargetInfo() {
    return hasTargetInfo;
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} send the given rawMessage.
   * 
   * @param rawMessage
   */
  protected final void sendRawMessage(final char rawMessage) {

    //Calls other method.
    sendRawMessage(String.valueOf(rawMessage));
  }

  //method declaration
  /**
   * Lets the current {@link NetEndPoint} send the given rawMessage.
   * 
   * @param rawMessage
   */
  protected abstract void sendRawMessage(String rawMessage);

  //method
  /**
   * Sends the target message of the current {@link NetEndPoint} to the
   * counterpart of the current {@link NetEndPoint}.
   */
  protected final void sendTargetMessage() {
    sendRawMessage(getTargetMessage());
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} receive the given rawMessage
   * asynchronously.
   * 
   * @param rawMessage
   */
  final void receiveRawMessageInBackground(final String rawMessage) {
    GlobalSequencer.runInBackground(() -> receiveRawMessage(rawMessage));
  }

  //method
  /**
   * Confirms that the current {@link NetEndPoint} has a target info.
   * 
   * @throws InvalidArgumentException if the current {@link NetEndPoint} has
   *                                  already a target info.
   */
  private void confirmReceivedTargetInfo() {

    //Asserts that the current BaseNetEndPoint has already a target info.
    if (hasTargetInfo()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has already a target info");
    }

    hasTargetInfo = true;
  }

  //method
  /**
   * @return the target message of the current {@link NetEndPoint}.
   */
  private String getTargetMessage() {

    //Handles the case that the current BaseNetEndPoint has a target.
    if (!hasCustomTargetSlot()) {
      return String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX);
    }

    //Handles the case that the current BaseNetEndPoint does not have a target.
    return (NetEndPointProtocol.TARGET_PREFIX + getCustomTargetSlot());
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} receive the given message.
   * 
   * @param message
   * @throws ClosedArgumentException if the current {@link NetEndPoint} is closed.
   */
  private void receiveMessage(final String message) {

    //Asserts that the current NetEndPoint is open.
    assertIsOpen();

    getStoredReceiver().accept(message);
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} receive the given rawMessage.
   * 
   * @param rawMessage
   * @throws InvalidArgumentException if the given rawMessage is not valid.
   */
  void receiveRawMessage(final String rawMessage) {

    //Enumerates the first character of the given rawMessage.
    switch (rawMessage.charAt(0)) {
      case NetEndPointProtocol.MAIN_TARGET_PREFIX:

        if (!rawMessage.equals(String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX))) {
          throw InvalidArgumentException.forArgumentNameAndArgument(RAW_MESSAGE_VARIABLE_NAME, rawMessage);
        }

        confirmReceivedTargetInfo();

        break;
      case NetEndPointProtocol.TARGET_PREFIX:
        setTarget(rawMessage.substring(1));
        confirmReceivedTargetInfo();
        break;
      case NetEndPointProtocol.MESSAGE_PREFIX:
        receiveMessage(rawMessage.substring(1));
        break;
      case NetEndPointProtocol.CLOSE_PREFIX:
        GlobalValidator.assertThat(rawMessage).thatIsNamed(RAW_MESSAGE_VARIABLE_NAME).hasLength(1);
        close();
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(RAW_MESSAGE_VARIABLE_NAME, rawMessage);
    }
  }
}
