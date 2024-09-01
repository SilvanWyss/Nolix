//package declaration
package ch.nolix.core.net.endpoint3;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3protocol.MessageHeaderCatalogue;
import ch.nolix.coreapi.netapi.netconstantapi.IPv6Catalogue;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class NetEndPoint extends EndPoint {

  //constant
  private static final IStringTool STRING_TOOL = new StringTool();

  //attribute
  private final ch.nolix.coreapi.netapi.endpoint2api.IEndPoint internalEndPoint;

  //constructor
  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the local machine.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public NetEndPoint(final int port) {

    //Calls other constructor.
    this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} that will connect to the given targetSlot
   * on the given port on the local machine.
   * 
   * @param port
   * @param targetSlot
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given targetSlot is null.
   * @throws InvalidArgumentException      if the given targetSlot is blank.
   */
  public NetEndPoint(final int port, final String targetSlot) {

    //Calls other constructor.
    this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, targetSlot);
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the default port on the machine with the given ip.
   * 
   * @param ip
   */
  public NetEndPoint(final String ip) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip));
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public NetEndPoint(final String ip, final int port) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip, port));
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} that will connect to the given target slot
   * on the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @param targetSlot
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given targetSlot is null.
   * @throws InvalidArgumentException      if the given targetSlot is blank.
   */
  public NetEndPoint(final String ip, final int port, final String targetSlot) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip, port, targetSlot));
  }

  //constructor
  /**
   * Creates a new {@link NetEndPoint} with the given internalEndPoint.
   * 
   * @param internalEndPoint
   * @throws ArgumentIsNullException if the given internalEndPoint is null.
   */
  NetEndPoint(final ch.nolix.coreapi.netapi.endpoint2api.IEndPoint internalEndPoint) {

    //Asserts that the given internalEndPoint is not null.
    GlobalValidator.assertThat(internalEndPoint).thatIsNamed("internal end point").isNotNull();

    //Sets the internalNetEndPoint of the current NetEndPoint.
    this.internalEndPoint = internalEndPoint;

    //Sets the replier of the internalEndPoint of the current NetEndPoint.
    internalEndPoint.setReplier(this::receiveAndGetReply);

    //Creates a close dependency between the current NetEndPoint and the
    //internalEndPoint of the current NetEndPoint.
    createCloseDependencyTo(internalEndPoint);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return internalEndPoint.getConnectionType();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String getCustomTargetSlot() {
    return internalEndPoint.getCustomTargetSlot();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {

    final var requests = ImmutableList.withElement(request);

    return getDataForRequests(requests).getStoredOne();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final IChainedNode request, final IChainedNode... requests) {

    //Concatenates the given requests.
    final var concatenatedRequests = ImmutableList.withElement(request, requests);

    //Calls other method.
    return getDataForRequests(concatenatedRequests);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {

    //Creates message.
    final var message = MessageHeaderCatalogue.MULTI_DATA_REQUEST_HEADER + '(' + requests.toString() + ')';

    //Sends message and receives reply.
    final var reply = Node.fromString(internalEndPoint.getReplyForRequest(message));

    //Enumerates the header of the reply.
    return switch (reply.getHeader()) {
      case MessageHeaderCatalogue.MULTI_DATA_HEADER ->
        reply.getStoredChildNodes();
      case MessageHeaderCatalogue.ERROR_HEADER ->
        throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.REPLY, reply);
    };
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return internalEndPoint.getPeerType();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return internalEndPoint.getSecurityMode();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return internalEndPoint.hasCustomTargetSlot();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final IChainedNode command) {
    runCommands(LinkedList.withElement(command));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {

    //Creates message.
    final var message = MessageHeaderCatalogue.COMMANDS_HEADER + '(' + ContainerView.forIterable(commands) + ')';

    final var replyAsString = internalEndPoint.getReplyForRequest(message);

    if (replyAsString == null) {
      //When one of the given commands is a redirect command, the counterpart will
      //redirect and leave null.
    } else {

      //Sends the message and received reply.
      final var reply = Node.fromString(replyAsString);

      //Enumerates the header of the reply.
      switch (reply.getHeader()) {
        case MessageHeaderCatalogue.DONE_HEADER:
          break;
        case MessageHeaderCatalogue.ERROR_HEADER:
          throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
        default:
          throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.REPLY, reply);
      }
    }
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} receive the given message. This method
   * does not throw any exception and returns a reply in any case because the
   * protocol determines that error messages must be sent back. The reply must not
   * collide with representations of a {@link Node}.
   * 
   * @param message
   * @return the reply to the given message from the current {@link NetEndPoint}.
   */
  private String receiveAndGetReply(final String message) {
    try {
      return receiveAndGetReply(ChainedNode.fromString(message));
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.

      GlobalLogger.logError(error);

      if (error.getMessage() == null) {
        return MessageHeaderCatalogue.ERROR_HEADER;
      }

      return (MessageHeaderCatalogue.ERROR_HEADER + '(' + BaseNode.getEscapeStringFor(error.getMessage()) + ')');
    }
  }

  //method
  /**
   * Lets the current {@link NetEndPoint} receive the given message.
   * 
   * @param message
   * @return the reply to the given message.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link NetEndPoint} does not
   *                                               have a receiver.
   */
  private String receiveAndGetReply(final ChainedNode message) {

    //Gets the receiver controller of the current NetEndPoint.
    final var receiverController = getStoredReceiverController();

    //Enumerates the header of the given message.
    switch (message.getHeader()) {
      case MessageHeaderCatalogue.COMMANDS_HEADER:

        for (final var a : message.getChildNodes()) {
          receiverController.runCommand(a);
        }

        return MessageHeaderCatalogue.DONE_HEADER;
      case MessageHeaderCatalogue.MULTI_DATA_REQUEST_HEADER:
        return //
        MessageHeaderCatalogue.MULTI_DATA_HEADER
        + STRING_TOOL.getInParentheses(receiverController.getDataForRequests(message.getChildNodes()).toString());
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.MESSAGE, message);
    }
  }
}
