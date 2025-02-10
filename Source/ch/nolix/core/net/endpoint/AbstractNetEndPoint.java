package ch.nolix.core.net.endpoint;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.flowcontrol.GlobalFlowController;
import ch.nolix.coreapi.netapi.endpointprotocol.MessageType;
import ch.nolix.coreapi.programcontrolapi.processproperty.TargetInfoState;

/**
 * A {@link AbstractNetEndPoint} can send messages to an other
 * {@link AbstractNetEndPoint} that is on: -the same process on the local
 * computer -another process on the local computer -another process on another
 * computer
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public abstract class AbstractNetEndPoint extends EndPoint {

  private static final String RAW_MESSAGE_VARIABLE_NAME = "raw message";

  private boolean hasTargetInfo;

  /**
   * Creates a new {@link AbstractNetEndPoint} with the given targetInfoState.
   * 
   * @param targetInfoState
   * @throws ArgumentIsNullException if the given connectionOrigin is null.
   * @throws ArgumentIsNullException if the given targetInfoState is null.
   */
  AbstractNetEndPoint(final TargetInfoState targetInfoState) {

    //Asserts that the given targetInfoState is not null.
    GlobalValidator.assertThat(targetInfoState).thatIsNamed(TargetInfoState.class).isNotNull();

    if (targetInfoState == TargetInfoState.RECEIVED_TARGET_INFO) {
      confirmReceivedTargetInfo();
    }
  }

  /**
   * Creates a new {@link AbstractNetEndPoint} with the given target.
   * 
   * @param target
   * @throws ArgumentIsNullException  if the given connectionOrigin is null.
   * @throws ArgumentIsNullException  if the given target is null.
   * @throws InvalidArgumentException if the given target is blank.
   */
  AbstractNetEndPoint(final String target) {

    //Calls constructor of the base class.
    setCustomTargetSlot(target);

    confirmReceivedTargetInfo();
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} send the given message.
   * 
   * @param message
   */
  @Override
  public final void sendMessage(final String message) {
    sendRawMessage(MessageType.CONTENT_MESSAGE.getPrefix() + message);
  }

  /**
   * @return true if the current {@link AbstractNetEndPoint} has a target info.
   */
  protected final boolean hasTargetInfo() {
    return hasTargetInfo;
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} send the given rawMessage.
   * 
   * @param rawMessage
   */
  protected final void sendRawMessage(final char rawMessage) {

    //Calls other method.
    sendRawMessage(String.valueOf(rawMessage));
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} send the given rawMessage.
   * 
   * @param rawMessage
   */
  protected abstract void sendRawMessage(String rawMessage);

  /**
   * Sends the target message of the current {@link AbstractNetEndPoint} to the
   * counterpart of the current {@link AbstractNetEndPoint}.
   */
  protected final void sendTargetMessage() {
    sendRawMessage(getTargetMessage());
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} receive the given rawMessage
   * asynchronously.
   * 
   * @param rawMessage
   */
  final void receiveRawMessageInBackground(final String rawMessage) {
    GlobalFlowController.runInBackground(() -> receiveRawMessage(rawMessage));
  }

  /**
   * Confirms that the current {@link AbstractNetEndPoint} has a target info.
   * 
   * @throws InvalidArgumentException if the current {@link AbstractNetEndPoint}
   *                                  has already a target info.
   */
  private void confirmReceivedTargetInfo() {

    //Asserts that the current BaseNetEndPoint has already a target info.
    if (hasTargetInfo()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has already a target info");
    }

    hasTargetInfo = true;
  }

  /**
   * @return the target message of the current {@link AbstractNetEndPoint}.
   */
  private String getTargetMessage() {

    //Handles the case that the current BaseNetEndPoint has a target.
    if (!hasCustomTargetSlot()) {
      return MessageType.DEFAULT_TARGET_MESSAGE.getPrefix();
    }

    //Handles the case that the current BaseNetEndPoint does not have a target.
    return (MessageType.TARGET_MESSAGE.getPrefix() + getCustomTargetSlot());
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} receive the given message.
   * 
   * @param message
   * @throws ClosedArgumentException if the current {@link AbstractNetEndPoint} is
   *                                 closed.
   */
  private void receiveMessage(final String message) {

    //Asserts that the current NetEndPoint is open.
    assertIsOpen();

    getStoredReceiver().accept(message);
  }

  /**
   * Lets the current {@link AbstractNetEndPoint} receive the given rawMessage.
   * 
   * @param rawMessage
   * @throws InvalidArgumentException if the given rawMessage is not valid.
   */
  void receiveRawMessage(final String rawMessage) {

    //Determinate the message type of the given rawMessage.
    final var messageType = MessageType.forPrefix(rawMessage.substring(0, 1));

    //Enumerates the messageType.
    switch (messageType) {
      case DEFAULT_TARGET_MESSAGE:

        if (!rawMessage.equals(MessageType.DEFAULT_TARGET_MESSAGE.getPrefix())) {
          throw InvalidArgumentException.forArgumentNameAndArgument(RAW_MESSAGE_VARIABLE_NAME, rawMessage);
        }

        confirmReceivedTargetInfo();

        break;
      case TARGET_MESSAGE:
        setCustomTargetSlot(rawMessage.substring(1));
        confirmReceivedTargetInfo();
        break;
      case CONTENT_MESSAGE:
        receiveMessage(rawMessage.substring(1));
        break;
      case CLOSE_MESSAGE:
        GlobalValidator.assertThat(rawMessage).thatIsNamed(RAW_MESSAGE_VARIABLE_NAME).hasLength(1);
        close();
        break;
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(RAW_MESSAGE_VARIABLE_NAME, rawMessage);
    }
  }
}
