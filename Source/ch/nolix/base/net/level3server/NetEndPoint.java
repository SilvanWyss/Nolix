/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.level3server;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.document.node.AbstractNode;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.level3serverprotocol.MessageHeaderCatalog;
import ch.nolix.baseapi.net.netproperty.BaseConnectionType;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class NetEndPoint extends AbstractEndPoint {
  private final ch.nolix.baseapi.net.level2server.IEndPoint internalEndPoint;

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the local machine.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private NetEndPoint(final int port) {
    this(ch.nolix.base.net.level2server.NetEndPoint.toLocalMachineAndGivenPortAndDefaultSlot(port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * given port on the local machine.
   * 
   * @param port
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  private NetEndPoint(final int port, final String slot) {
    this(ch.nolix.base.net.level2server.NetEndPoint.toLocalMachineAndGivenPortAndGivenSlot(port, slot));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * HTTP port on the given host.
   * 
   * @param host
   */
  private NetEndPoint(final String host) {
    this(ch.nolix.base.net.level2server.NetEndPoint.toGivenHostAndHttpPortAndDefaultSlot(host));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the given host.
   * 
   * @param host
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private NetEndPoint(final String host, final int port) {
    this(ch.nolix.base.net.level2server.NetEndPoint.toGivenHostAndGivenPortAndDefaultSlot(host, port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * given port on the given host.
   * 
   * @param host
   * @param port
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  private NetEndPoint(final String host, final int port, final String slot) {
    this(ch.nolix.base.net.level2server.NetEndPoint.toGivenHostAndGivenPortAndGivenSlot(host, port, slot));
  }

  /**
   * Creates a new {@link NetEndPoint} with the given internalEndPoint.
   * 
   * @param internalEndPoint
   * @throws RuntimeException if the given internalEndPoint is null or not a net
   *                          end point.
   */
  private NetEndPoint(final ch.nolix.baseapi.net.level2server.IEndPoint internalEndPoint) {
    if (internalEndPoint.getConnectionType().getBaseType() != BaseConnectionType.NET) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(internalEndPoint, "is not a net end point");
    }

    this.internalEndPoint = internalEndPoint;

    internalEndPoint.setReplier(this::receiveAndGetReply);

    createCloseDependencyTo(internalEndPoint);
  }

  /**
   * @param port
   * 
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the local machine
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndDefaultSlot(final int port) {
    return new NetEndPoint(port);
  }

  /**
   * @param port
   * @param slot
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         given port on the local machine
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndGivenSlot(final int port, final String slot) {
    return new NetEndPoint(port, slot);
  }

  /**
   * @param host
   * @param port
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static NetEndPoint toGivenHostAndGivenPortAndDefaultSlot(final String host, final int port) {
    return new NetEndPoint(host, port);
  }

  /**
   * @param host
   * @param port
   * @param slot
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  public static NetEndPoint toGivenHostAndGivenPortAndGivenSlot(final String host, final int port, final String slot) {
    return new NetEndPoint(host, port, slot);
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
   * @param internalEndPoint
   * @return a new {@link NetEndPoint} with the given internalEndPoint
   * @throws RuntimeException if the given internalEndPoint is null or not a net
   *                          end point.
   */
  static NetEndPoint withInternalEndPoint(final ch.nolix.baseapi.net.level2server.IEndPoint internalEndPoint) {
    return new NetEndPoint(internalEndPoint);
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
  public Node<?> getDataForRequest(final IChainedNode request) {
    final var requests = ImmutableList.withElements(request);

    return getDataForRequests(requests).getStoredSingle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final IChainedNode... requests) {
    // Concatenates the given requests.
    final var concatenatedRequests = ImmutableList.withElements(requests);

    // Calls other method.
    return getDataForRequests(concatenatedRequests);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    // Creates message.
    final var message = MessageHeaderCatalog.MULTI_DATA_REQUEST_HEADER + '(' + requests.toString() + ')';

    // Sends message and receives reply.
    final var reply = ImmutableNode.fromString(internalEndPoint.getReplyForRequest(message));

    // Enumerates the header of the reply.
    return switch (reply.getHeader()) {
      case MessageHeaderCatalog.MULTI_DATA_HEADER ->
        reply.getStoredChildNodes();
      case MessageHeaderCatalog.ERROR_HEADER ->
        throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(reply, LowerCaseVariableNameCatalog.REPLY);
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
    // Creates message.
    final var message = MessageHeaderCatalog.COMMANDS_HEADER + '(' + ExtendedIterableView.forIterable(commands) + ')';

    final var replyAsString = internalEndPoint.getReplyForRequest(message);

    if (replyAsString == null) {
      // When one of the given commands is a redirect command, the counterpart will
      // redirect and leave null.
    } else {
      // Sends the message and received reply.
      final var reply = ImmutableNode.fromString(replyAsString);

      // Enumerates the header of the reply.
      switch (reply.getHeader()) {
        case MessageHeaderCatalog.DONE_HEADER:
          break;
        case MessageHeaderCatalog.ERROR_HEADER:
          throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
        default:
          throw InvalidArgumentException.forArgumentAndArgumentName(reply, LowerCaseVariableNameCatalog.REPLY);
      }
    }
  }

  /**
   * Lets the current {@link NetEndPoint} receive the given message. This method
   * does not throw any exception and returns a reply in any case because the
   * protocol determines that error messages must be sent back. The reply must not
   * collide with representations of a {@link ImmutableNode}.
   * 
   * @param message
   * @return the reply to the given message from the current {@link NetEndPoint}.
   */
  private String receiveAndGetReply(final String message) {
    try {
      return receiveAndGetReply(ChainedNode.fromString(message));
    } catch (final Throwable error) { // NOSONAR: All errors must be caught.

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
   * @return the reply to the given message
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link NetEndPoint} does not
   *                                               have a receiver.
   */
  private String receiveAndGetReply(final ChainedNode message) {
    // Gets the receiver controller of the current NetEndPoint.
    final var receiverController = getStoredReceiverController();

    // Enumerates the header of the given message.
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
        throw InvalidArgumentException.forArgumentAndArgumentName(message, LowerCaseVariableNameCatalog.MESSAGE);
    }
  }
}
