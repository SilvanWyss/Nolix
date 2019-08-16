//package declaration
package ch.nolix.core.endPoint5;

//own imports
import ch.nolix.core.constants.IPv6Catalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.controllerAPI.IDataProviderController;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.logger.Logger;
import ch.nolix.core.node.Node;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.validator.Validator;

//class
/**
 * A net duplex controller can interact with another net duplex controller.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 250
 */
public class NetEndPoint extends EndPoint {
		
	//attribute
	private final ch.nolix.core.endPoint3.NetEndPoint internalNetEndPoint;
		
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
		this(new ch.nolix.core.endPoint3.NetEndPoint(ip, port));
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
	 * @throws NullArgumentException if the given target is null.
	 * @throws EmptyArgumentException if the given target is empty.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		//Calls other constructor.
		this(new ch.nolix.core.endPoint3.NetEndPoint(ip, port, target));
	}
	
	//package-visible constructor
	/**
	 * Creates a new net duplex controller with the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws NullArgumentException if the given net end point is null.
	 */
	NetEndPoint(final ch.nolix.core.endPoint3.NetEndPoint netEndPoint) {
		
		//Checks if the given net end point is not null.
		Validator.suppose(netEndPoint).isOfType(ch.nolix.core.endPoint3.NetEndPoint.class);
		
		//Sets the net end point of this net duplex controller.
		this.internalNetEndPoint = netEndPoint;
		
		//Creates the replier of the net end point.
		netEndPoint.setReplier(new Replier(this));
		
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
	public Node getData(final Statement request) {
		
		//Creates message.
		final String message = Protocol.DATA_REQUEST + '(' + request.toString() + ')';
		
		//Sends message and gets reply.
		final Node reply = Node.createFromString(internalNetEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DATA:
				return reply.getRefOneAttribute();
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeAsString());
			default:
				throw new RuntimeException("Error occured.");
		}
	}
	
	//method
	/**
	 * @return the target of this net duplex controller.
	 * @throws ArgumentMissesAttributeException if this net duplex controller does not have a target.
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
	public boolean isNetDuplexController() {
		return true;
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given command.
	 * 
	 * @param command
	 */
	@Override
	public void run(final Statement command) {
		run(new List<>(command));
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given commands.
	 * 
	 * @param commands
	 * @throws InvalidArgumentException if this net duplex contorller is aborted.
	 */
	@Override
	protected void run(final List<Statement> commands) {
			
		//Checks if this net duplex controller is not aborted.
		supposeIsAlive();
		
		//TODO: Make this more elegant.
		//Creates message.
		final String message = Protocol.COMMANDS + '(' + commands.to(c -> Node.createReproducingString(c.toString())).toString() + ')';
		
		//Sends the message and gets reply.
		final Node reply = Node.createFromString(internalNetEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DONE:
				break;
			case Protocol.ERROR:
				if (!reply.containsAttributes()) {
					throw new RuntimeException();
				}
				throw new RuntimeException(reply.getOneAttributeAsString());
			default:
				throw new RuntimeException("Error occured by running the commands '" + commands + "'." );
		}
	}
		
	//package-visible method
	/**
	 * Lets this net duplex controller receive the given message.
	 * This method does not throw any exception and returns a reply in any case
	 * because the protocol determines that error messages must be sent back.
	 * The reply must not collide with representations of a {@link Node}.
	 * 
	 * @return the reply to the given message from this net duplex controller.
	 */
	final String receiveAndGetReply(final String message) {
		try {
			return receiveAndGetReply(Node.createFromString(message));
		}
		catch (final Exception exception) {
			
			Logger.logError(exception);
			
			if (exception.getMessage() == null) {
				return Protocol.ERROR;
			}
			return (Protocol.ERROR + '(' + Node.createReproducingString(exception.getMessage()) + ')');
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
	private final String receiveAndGetReply(final Node message) {
		
		//Gets the receiver controller of this net duplex controller.
		final IDataProviderController receiverController = getRefReceiverController();
		
		//Enumerates the header of the given message.
		switch (message.getHeader()) {
			case Protocol.COMMANDS:	
				message.getRefAttributes().forEach(a -> receiverController.run(Node.createOriginStringFromReproducingString(a.toString())));
				return Protocol.DONE;
			case Protocol.DATA_REQUEST:
				return (
					Protocol.DATA
					+ '('
					+ receiverController.getData(message.getOneAttributeAsString()).toString()
					+ ')'
				);
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.MESSAGE, message, "is not valid");
		}
	}
}
