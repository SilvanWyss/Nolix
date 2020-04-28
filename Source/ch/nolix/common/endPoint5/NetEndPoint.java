//package declaration
package ch.nolix.common.endPoint5;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constant.IPv6Catalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.controllerAPI.IDataProviderController;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A net duplex controller can interact with another net duplex controller.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 270
 */
public class NetEndPoint extends EndPoint {
	
	//attribute
	private final ch.nolix.common.endPoint3.NetEndPoint internalNetEndPoint;
		
	//constructor
	/**
	 * Creates a new net duplex controller
	 * that will connect to the default target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new net duplex controller
	 * that will connect to the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port, final String target) {
		
		//Calls other constructor.
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}

	//constructor
	/**
	 * Creates a new net duplex controller
	 * that will connect to the default target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endPoint3.NetEndPoint(ip, port));
	}
	
	//constructor
	/**
	 * Creates a new net duplex controller
	 * that will connect to the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @parma target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls other constructor.
		this(new ch.nolix.common.endPoint3.NetEndPoint(ip, port, target));
	}
	
	//constructor
	/**
	 * Creates a new net duplex controller with the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws ArgumentIsNullException if the given net end point is null.
	 */
	NetEndPoint(final ch.nolix.common.endPoint3.NetEndPoint netEndPoint) {
		
		//Asserts that the given net end point is not null.
		Validator.assertThat(netEndPoint).isOfType(ch.nolix.common.endPoint3.NetEndPoint.class);
		
		//Sets the net end point of this net duplex controller.
		this.internalNetEndPoint = netEndPoint;
		
		//Creates the replier of the net end point.
		netEndPoint.setReplier(m -> receiveAndGetReply(m));
		
		//Creates an abort dependency from this net duplex controller to its net end point.
		createCloseDependency(netEndPoint);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from this net duplex controller.
	 * @throws InvalidArgumentException if this net duplex controller is aborted.
	 */
	@Override
	public Node getData(final ChainedNode request) {
		
		//Creates message.
		final String message = Protocol.DATA_REQUEST_HEADER + '(' + request.toString() + ')';
		
		//Sends message and gets reply.
		final var reply = Node.fromString(internalNetEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DATA_HEADER:
				return reply.getRefOneAttribute();
			case Protocol.ERROR_HEADER:
				throw new RuntimeException(reply.getOneAttributeAsString());
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.REPLY, reply, "is not valid");
		}
	}
	
	//method
	/**
	 * @return the target of this net duplex controller.
	 * @throws ArgumentDoesNotHaveAttributeException if this net duplex controller does not have a target.
	 */
	@Override
	public String getTarget() {
		return internalNetEndPoint.getTarget();
	}

	//method
	/**
	 * @return true if this net duplex controller has requested the connection.
	 */
	@Override
	public boolean hasRequestedConnection() {
		return internalNetEndPoint.hasRequestedConnection();
	}

	//method
	/**
	 * @return true if this net duplex controller has a target.
	 */
	@Override
	public boolean hasTarget() {
		return internalNetEndPoint.hasTarget();
	}
	
	//method
	/**
	 * @return true if this net duplex controller is a net duplex controller.
	 */
	@Override
	public boolean isNetEndPoint() {
		return true;
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given command.
	 * 
	 * @param command
	 */
	@Override
	public void run(final ChainedNode command) {
		run(new LinkedList<>(command));
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given commands.
	 * 
	 * @param commands
	 * @throws ClosedArgumentException if this net duplex contorller is closed.
	 */
	@Override
	protected void run(final LinkedList<ChainedNode> commands) {
			
		//Asserts that this net duplex controller is open.
		assertIsOpen();
		
		//Creates message.
		final var message = Protocol.COMMANDS_HEADER + '(' + commands + ')';
		
		//Sends the message and gets reply.
		final Node reply = Node.fromString(internalNetEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DONE_HEADER:
				break;
			case Protocol.ERROR_HEADER:
				if (!reply.containsAttributes()) {
					throw new RuntimeException("An error occured by running the commands '" + commands + "'." );
				}
				throw new RuntimeException(reply.getOneAttributeAsString());
			default:
				throw new RuntimeException("An error occured by running the commands '" + commands + "'." );
		}
	}
	
	//method
	/**
	 * Lets this net duplex controller receive the given message.
	 * This method does not throw any exception and returns a reply in any case
	 * because the protocol determines that error messages must be sent back.
	 * The reply must not collide with representations of a {@link Node}.
	 * 
	 * @return the reply to the given message from this net duplex controller.
	 */
	private final String receiveAndGetReply(final String message) {
		try {
			return receiveAndGetReply(ChainedNode.fromString(message));
		}
		catch (final Exception exception) {
			
			Logger.logError(exception);
			
			if (exception.getMessage() == null) {
				return Protocol.ERROR_HEADER;
			}
			return (Protocol.ERROR_HEADER + '(' + BaseNode.createReproducingString(exception.getMessage()) + ')');
		}
	}
	
	//method
	/**
	 * Lets this net duplex contorller receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message from this net duplex controller.
	 * @throws UnexistringAttributeException if this net duplex contorller does not have a receiver.
	 */
	private final String receiveAndGetReply(final ChainedNode message) {
		
		//Gets the receiver controller of this net duplex controller.
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
				throw new InvalidArgumentException(VariableNameCatalogue.MESSAGE, message, "is not valid");
		}
	}
}
