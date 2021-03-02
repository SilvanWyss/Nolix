//package declaration
package ch.nolix.common.net.endpoint3;

import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.constant.IPv6Catalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.controllerapi.IDataProviderController;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.exception.GeneralException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.logger.Logger;

//class
/**
 * A {@link NetEndPoint} can interact with another {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @ate 2016-01-01
 * @lines 290
 */
public class NetEndPoint extends EndPoint {
	
	//attribute
	private final ch.nolix.common.net.endpoint2.NetEndPoint internalNetEndPoint;
		
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the default target on the given port on the local machine.
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
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port, final String target) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}

	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the default target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint2.NetEndPoint(ip, port));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint}
	 * that will connect to the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls other constructor.
		this(new ch.nolix.common.net.endpoint2.NetEndPoint(ip, port, target));
	}
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given netEndPoint.
	 * 
	 * @param internalNetEndPoint
	 * @throws ArgumentIsNullException if the given netEndPoint is null.
	 */
	NetEndPoint(final ch.nolix.common.net.endpoint2.NetEndPoint internalNetEndPoint) {
		
		//Asserts that the given netEndPoint is not null.
		Validator.assertThat(internalNetEndPoint).isOfType(ch.nolix.common.net.endpoint2.NetEndPoint.class);
		
		//Sets the internalNetEndPoint of the current NetEndPoint.
		this.internalNetEndPoint = internalNetEndPoint;
		
		//Sets the replier to the internalNetEndPoint of the current NetEndPoint.
		internalNetEndPoint.setReplier(this::receiveAndGetReply);
		
		//Creates a close dependency from the current NetEndPoint to its internalNetEndPoint.
		createCloseDependencyTo(internalNetEndPoint);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void createCloseDependencyTo(final ICloseableElement element) {
		
		//This implementation just ensures that it cannot be overwritten.
		super.createCloseDependencyTo(element);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from this {@link NetEndPoint}.
	 * @throws InvalidArgumentException if this {@link NetEndPoint} is aborted.
	 */
	@Override
	public Node getData(final ChainedNode request) {
		
		//Creates message.
		final String message = Protocol.DATA_REQUEST_HEADER + '(' + request.toString() + ')';
		
		//Sends message and gets reply.
		final var reply = Node.fromString(internalNetEndPoint.getReplyTo(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DATA_HEADER:
				return reply.getRefOneAttribute();
			case Protocol.ERROR_HEADER:
				throw new GeneralException(reply.getOneAttributeHeader());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REPLY, reply, "is not valid");
		}
	}
	
	//method
	/**
	 * @return the target of the current {@link NetEndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if this {@link NetEndPoint} does not have a target.
	 */
	@Override
	public String getTarget() {
		return internalNetEndPoint.getTarget();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRequestedConnection() {
		return internalNetEndPoint.hasRequestedConnection();
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasTarget() {
		return internalNetEndPoint.hasTarget();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isNetEndPoint() {
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWebEndPoint() {
		return internalNetEndPoint.isWebEndPoint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(final ChainedNode command) {
		run(LinkedList.withElements(command));
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} run the given commands.
	 * 
	 * @param commands
	 * @throws ClosedArgumentException if the current {@link NetEndPoint} is closed.
	 */
	@Override
	public void run(final Iterable<ChainedNode> commands) {
			
		//Asserts that this {@link NetEndPoint} is open.
		assertIsOpen();
		
		//Creates message.
		//A ReadContainer is created for the commands because a ReadContainer has the required toString implementation.
		final var message = Protocol.COMMANDS_HEADER + '(' + ReadContainer.forIterable(commands) + ')';
		
		//Sends the message and gets reply.
		final var replyMessage = internalNetEndPoint.getReplyTo(message);
		if (replyMessage == null) {
			return;
		}
		final var reply = Node.fromString(replyMessage);
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DONE_HEADER:
				break;
			case Protocol.ERROR_HEADER:
				throw new GeneralException(reply.getOneAttributeHeader());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REPLY, reply, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} receive the given message.
	 * This method does not throw any exception and returns a reply in any case
	 * because the protocol determines that error messages must be sent back.
	 * The reply must not collide with representations of a {@link Node}.
	 * 
	 * @param message
	 * @return the reply to the given message from the current {@link NetEndPoint}.
	 */
	private final String receiveAndGetReply(final String message) {
		try {
			return receiveAndGetReply(ChainedNode.fromString(message));
		} catch (final Exception exception) {
			
			Logger.logError(exception);
			
			if (exception.getMessage() == null) {
				return Protocol.ERROR_HEADER;
			}
			return (Protocol.ERROR_HEADER + '(' + BaseNode.getEscapeStringFor(exception.getMessage()) + ')');
		}
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint}  receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link NetEndPoint} does not have a receiver.
	 */
	private final String receiveAndGetReply(final ChainedNode message) {
		
		//Gets the receiver controller of the current NetEndPoint.
		final IDataProviderController receiverController = getRefReceiverController();
		
		//Enumerates the header of the given message.
		switch (message.getHeader()) {
			case Protocol.COMMANDS_HEADER:
				
				for (final var a : message.getAttributes()) {
					receiverController.run(a);
				}
				
				return Protocol.DONE_HEADER;
			case Protocol.DATA_REQUEST_HEADER:
				return (Protocol.DATA_HEADER + '(' + receiverController.getData(message.getOneAttribute()) + ')');
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.MESSAGE, message, "is not valid");
		}
	}
}
