//package declaration
package ch.nolix.core.net.endpoint3;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv6Catalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.baseendpointapi.TargetSlotDefinition;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * A {@link NetEndPoint} can interact with another {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public class NetEndPoint extends EndPoint {
	
	//attribute
	private final ch.nolix.coreapi.netapi.endpoint2api.IEndPoint internalNetEndPoint;
		
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
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the default target on the {@link Server#DEFAULT_PORT} on the machine with the given ip.
	 * 
	 * @param ip
	 */
	public NetEndPoint(final String ip) {
		
		//Calls other constructor.
		this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip));
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
		this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip, port));
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
		this(new ch.nolix.core.net.endpoint2.NetEndPoint(ip, port, target));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} with the given netEndPoint.
	 * 
	 * @param internalNetEndPoint
	 * @throws ArgumentIsNullException if the given netEndPoint is null.
	 */
	NetEndPoint(final ch.nolix.coreapi.netapi.endpoint2api.IEndPoint internalNetEndPoint) {
		
		//Asserts that the given netEndPoint is not null.
		GlobalValidator.assertThat(internalNetEndPoint).isOfType(ch.nolix.core.net.endpoint2.NetEndPoint.class);
		
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
	public final void createCloseDependencyTo(final GroupCloseable element) {
		
		//This implementation just ensures that it cannot be overwritten.
		super.createCloseDependencyTo(element);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ConnectionType getConnectionType() {
		return internalNetEndPoint.getConnectionType();
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from this {@link NetEndPoint}.
	 * @throws InvalidArgumentException if this {@link NetEndPoint} is aborted.
	 */
	@Override
	public INode<?> getDataForRequest(final IChainedNode request) {
		
		//Creates message.
		final String message = NetEndPointProtocol.DATA_REQUEST_HEADER + '(' + request.toString() + ')';
		
		//Sends message and gets reply.
		final var reply = Node.fromString(internalNetEndPoint.getReplyForRequest(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case NetEndPointProtocol.DATA_HEADER:
				return reply.getRefSingleChildNode();
			case NetEndPointProtocol.ERROR_HEADER:
				throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REPLY, reply);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<INode<?>> getDataForRequests(final IChainedNode request, final IChainedNode... requests) {
		//TODO: Implement.
		return null;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
		//TODO: Implement.
		return null;
	}
	
	//method
	/**
	 * @return the target of the current {@link NetEndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if this {@link NetEndPoint} does not have a target.
	 */
	@Override
	public String getCustomTargetSlot() {
		return internalNetEndPoint.getCustomTargetSlot();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PeerType getPeerType() {
		return internalNetEndPoint.getPeerType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TargetSlotDefinition getTargetSlotDefinition() {
		return internalNetEndPoint.getTargetSlotDefinition();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runCommand(final IChainedNode command) {
		runCommands(LinkedList.withElements(command));
	}
	
	//method
	/**
	 * Lets the current {@link NetEndPoint} run the given commands.
	 * 
	 * @param commands
	 * @throws ClosedArgumentException if the current {@link NetEndPoint} is closed.
	 */
	@Override
	public void runCommands(final Iterable<? extends IChainedNode> commands) {
			
		//Asserts that this {@link NetEndPoint} is open.
		assertIsOpen();
		
		//Creates message.
		//A ReadContainer is created for the commands because a ReadContainer has the required toString implementation.
		final var message = NetEndPointProtocol.COMMANDS_HEADER + '(' + ReadContainer.forIterable(commands) + ')';
		
		//Sends the message and gets reply.
		final var replyMessage = internalNetEndPoint.getReplyForRequest(message);
		if (replyMessage == null) {
			return;
		}
		final var reply = Node.fromString(replyMessage);
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case NetEndPointProtocol.DONE_HEADER:
				break;
			case NetEndPointProtocol.ERROR_HEADER:
				throw GeneralException.withErrorMessage(reply.getSingleChildNodeHeader());
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.REPLY, reply);
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
		} catch (final Throwable error) {
			
			GlobalLogger.logError(error);
			
			if (error.getMessage() == null) {
				return NetEndPointProtocol.ERROR_HEADER;
			}
			return (NetEndPointProtocol.ERROR_HEADER + '(' + BaseNode.getEscapeStringFor(error.getMessage()) + ')');
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
		final var receiverController = getRefReceiverController();
		
		//Enumerates the header of the given message.
		switch (message.getHeader()) {
			case NetEndPointProtocol.COMMANDS_HEADER:
				
				for (final var a : message.getChildNodes()) {
					receiverController.runCommand(a);
				}
				
				return NetEndPointProtocol.DONE_HEADER;
			case NetEndPointProtocol.DATA_REQUEST_HEADER:
				return (NetEndPointProtocol.DATA_HEADER + '(' + receiverController.getDataForRequest(message.getSingleChildNode()) + ')');
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.MESSAGE, message);
		}
	}
}
