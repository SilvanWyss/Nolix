/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.endpoint3;

import ch.nolix.base.commontypetool.stringtool.StringTool;
import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.document.node.AbstractNode;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.net.endpoint3protocol.MessageHeaderCatalog;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class NetEndPoint extends AbstractEndPoint {
  private final ch.nolix.baseapi.net.endpoint2.IEndPoint internalEndPoint;

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the local machine.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535].
   */
  private NetEndPoint(final int port) {
    this(ch.nolix.base.net.endpoint2.NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * given port on the local machine.
   * 
   * @param port
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535].
   * @throws RuntimeException if the given slot is null or blank.
   */
  private NetEndPoint(final int port, final String slot) {
    this(ch.nolix.base.net.endpoint2.NetEndPoint.toLocalMachineAndGivenPortAndGivenSlot(port, slot));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * HTTP port on the given host.
   * 
   * @param host
   */
  private NetEndPoint(final String host) {
    this(ch.nolix.base.net.endpoint2.NetEndPoint.toGivenHostAndHttpPortAndDefaultSlot(host));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the given host.
   * 
   * @param host
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535].
   */
  private NetEndPoint(final String host, final int port) {
    this(ch.nolix.base.net.endpoint2.NetEndPoint.toGivenHostAndGivenPortAndDefaultSlot(host, port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given target slot
   * on the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @param targetSlot
   * @throws RuntimeException if the given port is not in [0, 65535].
   * @throws RuntimeException if the given targetSlot is null.
   * @throws RuntimeException if the given targetSlot is blank.
   */
  public NetEndPoint(final String ip, final int port, final String targetSlot) {
    //Calls other constructor.
    this(ch.nolix.base.net.endpoint2.NetEndPoint.toGivenHostAndGivenPortAndGivenSlot(ip, port, targetSlot));
  }

  /**
   * Creates a new {@link NetEndPoint} with the given internalEndPoint.
   * 
   * @param internalEndPoint
   * @throws RuntimeException if the given internalEndPoint is null.
   */
  NetEndPoint(final ch.nolix.baseapi.net.endpoint2.IEndPoint internalEndPoint) {
    //Asserts that the given internalEndPoint is not null.
    Validator.assertThat(internalEndPoint).thatIsNamed("internal end point").isNotNull();

    //Sets the internalNetEndPoint of the current NetEndPoint.
    this.internalEndPoint = internalEndPoint;

    //Sets the replier of the internalEndPoint of the current NetEndPoint.
    internalEndPoint.setReplier(this::receiveAndGetReply);

    //Creates a close dependency between the current NetEndPoint and the
    //internalEndPoint of the current NetEndPoint.
    createCloseDependencyTo(internalEndPoint);
  }

  /**
   * @param port
   * 
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the local machine.
   * @throws RuntimeException if the given port is not in [0, 65535].
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndDefaultSlot(final int port) {
    return new NetEndPoint(port);
  }

  /**
   * @param port
   * @param slot
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         given port on the local machine.
   * @throws RuntimeException if the given port is not in [0, 65535].
   * @throws RuntimeException if the given slot is null or blank.
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndGivenSlot(final int port, final String slot) {
    return new NetEndPoint(port, slot);
  }

  /**
   * @param host
   * @param port
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the given host.
   * @throws RuntimeException if the given port is not in [0, 65535].
   */
  public static NetEndPoint toGivenHostAndGivenPortAndDefaultSlot(final String host, final int port) {
    return new NetEndPoint(host, port);
  }

  /**
   * @param host
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         HTTP port on the given host.
   */
  public static NetEndPoint toGivenHostAndHttpPortAndDefaultSlot(final String host) {
    return new NetEndPoint(host);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return internalEndPoint.getConnectionType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getCustomTargetSlot() {
    return internalEndPoint.getCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {
    final var requests = ImmutableList.withElements(request);

    return getDataForRequests(requests).getStoredOne();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final IChainedNode... requests) {
    //Concatenates the given requests.
    final var concatenatedRequests = ImmutableList.withElements(requests);

    //Calls other method.
    return getDataForRequests(concatenatedRequests);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    //Creates message.
    final var message = MessageHeaderCatalog.MULTI_DATA_REQUEST_HEADER + '(' + requests.toString() + ')';

    //Sends message and receives reply.
    final var reply = Node.fromString(internalEndPoint.getReplyForRequest(message));

    //Enumerates the header of the reply.
    return switch (reply.getHeader()) {
      case MessageHeaderCatalog.MULTI_DATA_HEADER ->
        reply.getStoredChildNodes();
      case MessageHeaderCatalog.ERROR_HEADER ->
        throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(reply, LowerCaseVariableCatalog.REPLY);
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return internalEndPoint.getPeerType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return internalEndPoint.getSecurityMode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return internalEndPoint.hasCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final IChainedNode command) {
    runCommands(LinkedList.withElement(command));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {
    //Creates message.
    final var message = MessageHeaderCatalog.COMMANDS_HEADER + '(' + ContainerView.forIterable(commands) + ')';

    final var replyAsString = internalEndPoint.getReplyForRequest(message);

    if (replyAsString == null) {
      //When one of the given commands is a redirect command, the counterpart will
      //redirect and leave null.
    } else {
      //Sends the message and received reply.
      final var reply = Node.fromString(replyAsString);

      //Enumerates the header of the reply.
      switch (reply.getHeader()) {
        case MessageHeaderCatalog.DONE_HEADER:
          break;
        case MessageHeaderCatalog.ERROR_HEADER:
          throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
        default:
          throw InvalidArgumentException.forArgumentAndArgumentName(reply, LowerCaseVariableCatalog.REPLY);
      }
    }
  }

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
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.

      Logger.logError(error);

      if (error.getMessage() == null) {
        return MessageHeaderCatalog.ERROR_HEADER;
      }

      return (MessageHeaderCatalog.ERROR_HEADER + '(' + AbstractNode.getEscapeStringFor(error.getMessage()) + ')');
    }
  }

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
      case MessageHeaderCatalog.COMMANDS_HEADER:

        for (final var a : message.getChildNodes()) {
          receiverController.runCommand(a);
        }

        return MessageHeaderCatalog.DONE_HEADER;
      case MessageHeaderCatalog.MULTI_DATA_REQUEST_HEADER:
        return //
        MessageHeaderCatalog.MULTI_DATA_HEADER
        + StringTool.getInParentheses(receiverController.getDataForRequests(message.getChildNodes()).toString());
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(message, LowerCaseVariableCatalog.MESSAGE);
    }
  }
}
