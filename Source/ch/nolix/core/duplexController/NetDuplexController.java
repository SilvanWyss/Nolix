//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.ILevel2Controller;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 */
public class NetDuplexController extends DuplexController {
		
	//attribute
	private final NetEndPoint netEndPoint;
		
	//constructor
	/**
	 * Creates new net duplex controller
	 * that will connect to the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetDuplexController(final String ip, final int port) {
		
		//Calls other constructor.
		this(new NetEndPoint(ip, port));
	}
	
	//package-visible constructor
	/**
	 * Creates new net duplex controller with the given net end point.
	 * 
	 * @param netEndPoint
	 * @throws NullArgumentException if the given net end point is null.
	 */
	NetDuplexController(final NetEndPoint netEndPoint) {
		
		//Checks if the given net end point is not null.
		Validator.supposeThat(netEndPoint).thatIsInstanceOf(NetEndPoint.class).isNotNull();
		
		//Sets the net end point of this net duplex controller.
		this.netEndPoint = netEndPoint;
		
		//Creates the replier of the net end point.
		netEndPoint.setReplier(new Replier(this));
		
		//Creates an abort dependency from this net duplex controller to its net end point.
		createAbortDependency(netEndPoint);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests from this net duplex controller.
	 * @throws InvalidStateException if this net duplex controller is aborted.
	 */
	public Specification getData(final Statement request) {
		
		//Creates message.
		final String message = Protocol.DATA_REQUEST + '(' + request.toString() + ')';
		
		//Sends message and gets reply.
		final Specification reply = new Specification(netEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DATA:
				return reply.getRefOneAttribute();
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeToString());
			default:
				throw new RuntimeException("Error occured.");
		}
	}
	
	@Override
	public String getTarget() {
		return netEndPoint.getTarget();
	}

	//method
	/**
	 * @return true if this net duplex controller has requested the connection.
	 */
	public boolean hasRequestedConnection() {
		return netEndPoint.hasRequestedConnection();
	}

	//method
	/**
	 * @return true if this net duplex controller has a target.
	 */
	public boolean hasTarget() {
		return netEndPoint.hasTarget();
	}
	
	//method
	/**
	 * @return true if this net duplex controller is a net duplex controller.
	 */
	public boolean isNetDuplexController() {
		return true;
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given command.
	 * 
	 * @param command
	 */
	public void run(final Statement command) {
		run(new List<String>(command.toString()));
	}
	
	//method
	/**
	 * Lets this net duplex contorller run the given commands.
	 * 
	 * @param commands
	 * @throws InvalidStateException if this net duplex contorller is aborted.
	 */
	protected void run(final List<String> commands) {
			
		//Checks if this net duplex controller is not aborted.
		throwExceptionIfAborted();
		
		//Creates message.
		final String message = Protocol.COMMANDS + '(' + commands.toString() + ')';
				
		//Sends the message and gets reply.
		final Specification reply = new Specification(netEndPoint.sendAndGetReply(message));
		
		//Enumerates the header of the reply.
		switch (reply.getHeader()) {
			case Protocol.DONE:
				break;
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeToString());
			default:
				throw new RuntimeException("Error occured by running the commands '" + commands + "'." );
		}
	}
		
	//package-visible method
	/**
	 * Lets this net duplex controller receive the given message.
	 * This method does not throw any exception and returns a reply in any case
	 * because the protocol determines that error messages must be sent back.
	 * 
	 * @return the reply to the given message from this net duplex controller.
	 */
	final String receiveAndGetReply(final String message) {
		try {
			return receiveAndGetReply(new Specification(message));
		}
		catch (final Exception exception) {
			return (Protocol.ERROR + '(' + exception.getMessage() + ')');
		}
	}
	
	//method
	/**
	 * Lets this net duplex contorller receive the given message.
	 * 
	 * @param message
	 * @return the reply to the given message from this net duplex controller.
	 * @throws UnexistringAttributeException if this net duplex contorller has no receiver.
	 */
	private final String receiveAndGetReply(final Specification message) {
		
		final ILevel2Controller receiverController = getRefReceiverController();
		
		//Enumerates the header of the given message.
		switch (message.getHeader()) {
			case Protocol.COMMANDS:			
				message.getRefAttributes().forEach(a -> receiverController.run(a.toString()));	
				return Protocol.DONE;
			case Protocol.DATA_REQUEST:
				return (
					Protocol.DATA
					+ '('
					+ receiverController.getData(message.getOneAttributeToString()).toString()
					+ ')'
				);
			default:
				throw new InvalidArgumentException(
					new ArgumentName("message"),
					new Argument(message)
				);
		}
	}
}
