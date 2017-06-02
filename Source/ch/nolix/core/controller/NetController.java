/*
 * file:	NetDuplexController.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	230
 */

//package declaration
package ch.nolix.core.controller;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.ILevel2Controller;
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator.Validator;

//class
public final class NetController extends Controller {
		
	//attribute
	private final EndPoint netEndPoint;
		
	//constructor
	/**
	 * Creates new net duplex controller with the given end point.
	 * 
	 * @param alphaEndPoint
	 * @throws Exception if the given end point is null
	 */
	public NetController(EndPoint alphaEndPoint) {
		
		Validator.throwExceptionIfValueIsNull("alpha end point", alphaEndPoint);
		
		this.netEndPoint = alphaEndPoint;
		
		//Creates the receiver of this net duplex controller.
		alphaEndPoint.setReplier(new Replier(this));
	}
	
	//constructor
	/**
	 * Creates new net duplex controller that will connect to the given port on the computer with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws Exception if the bigger port is smaller than 0 or bigger than 65535
	 */
	public NetController(String ip, int port) {
		
		//Calls other constructor.
		this(new NetEndPoint(ip, port, "Test"));
	}
	
	public NetController(String ip, int port, ILevel2Controller receiverController) {
		
		this(ip, port);
		
		setReceiverController(receiverController);
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if:
	 *  -this net duplex controller is stopped
	 *  -the given request is not valid
	 */
	public final Specification getRawData(Statement request) {
		
		//Creates message.
		String message = Protocol.DATA_REQUEST + '(' + request.toString() + ')';
		
		//Sends and gets reply.
		Specification reply = new Specification(netEndPoint.sendAndGetReply(message));
		
		//Handles reply
		switch (reply.getHeader()) {
			case Protocol.DATA:
				return reply.getRefOneAttribute();
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeToString());
			default:
				throw new RuntimeException("Error occured.");
		}
	}
	
	//method
	/**
	 * @return the stop reason of this net duplex controller
	 * @throws Exception if:
	 *  -this net duplex controller is not stopped
	 *  -this net duplex controller has no stop reason
	 */
	public final String getAbortReason() {
		return netEndPoint.getAbortReason();
	}

	//method
	/**
	 * @return true if this duplex controller is a net duplex controller
	 */
	public final boolean isNetDuplexController() {
		return true;
	}
	
	//method
	/**
	 * @return true if this net duplex controller is stopped
	 */
	public final boolean isAborted() {
		return netEndPoint.isAborted();
	}
	
	//method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public final void run(Statement command) {
		run(new List<String>().addAtEnd(command.toString()));
	}
	
	//method
	/**
	 * Run and clears the appended commands of this net duplex controller.
	 * This method always clears the appended commands of this net duplex controller.
	 * 
	 * @throws Exception if:
	 *  -this net duplex controller is stopped
	 *  -this net duplex controller has no target duplex controller
	 *  -one of the appended commands of this net duplex controller is not valid
	 */
	public final void runAppendedCommands() {
		try {
			
			throwExceptionIfStopped();
			
			List<String> appendedCommands = this.appendedCommands;
			this.appendedCommands = new List<String>();
			run(appendedCommands);
		}
		finally {
			appendedCommands.clear();
		}
	}
	
	//method
	/**
	 * Stops this net duplex controller.
	 * 
	 * @throws Exception if this net duplex controller is stopped already
	 */
	public final void abort() {
		netEndPoint.abort();
	}
	
	//method
	/**
	 * Stops this net duplex controller because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if:
	 *  -the given stop reason is null or an empty string
	 *  -this net duplex controller is stopped already
	 */
	public final void abort(String stopReason) {		
		netEndPoint.abort(stopReason);
	}
	
	//method
	public Specification waitToData(final String request) {
		
		//TODO: Implement base method for this method and the getRawData method.
		
		//Creates message.
		String message = Protocol.DATA_REQUEST + '(' + request.toString() + ')';
		
		//Sends and gets reply.
		//TODO
		Specification reply = new Specification(netEndPoint.sendAndGetReply(message));
		
		//Handles reply
		switch (reply.getHeader()) {
			case Protocol.DATA:
				return reply.getRefOneAttribute();
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeToString());
			default:
				throw new RuntimeException("Error occured.");
		}
	}
		
	//method
	/**
	 * Receives the given message.
	 * 
	 * @param message
	 * @throws Exception if:
	 *  -the given message is not valid
	 *  -this net duplex controller is stopped
	 */
	final String receiveMessageAndGetReply(String message) {

		try {
			return receiveMessageAndGetReply(new Specification(message));
		}
		catch (Exception e) {
			return (Protocol.ERROR + '(' + e.getMessage() + ')');
		}
	}
	
	private final String receiveMessageAndGetReply(Specification message) {
		
		ILevel2Controller receiverController = getRefReceiverController();
		
		switch (message.getHeader()) {
			case Protocol.COMMANDS:
				message.getRefAttributes().forEach(a -> receiverController.run(a.toString()));
				return Protocol.DONE;
			case Protocol.DATA_REQUEST:
				return (
					Protocol.DATA +
					'(' +
					receiverController.getData(message.getOneAttributeToString()).toString() +
					')'
				);
			default:
				throw new InvalidArgumentException(
					new ArgumentName("message"),
					new Argument(message)
				);
		}
	}
	
	//method
	/**
	 * Runs the given commands.
	 * 
	 * @param commands
	 * @throws Exception if:
	 *  -this net duplex controller is stopped
	 *  -one of the given command is not valid
	 */
	private final void run(List<String> commands) {
			
		//Creates message.
		String message = Protocol.COMMANDS + '(' + commands.toString() + ')';
				
		//Sends message and gets reply.
		Specification reply = new Specification(netEndPoint.sendAndGetReply(message));
		
		//Handles reply.
		switch (reply.getHeader()) {
			case Protocol.DONE:
				break;
			case Protocol.ERROR:
				throw new RuntimeException(reply.getOneAttributeToString());
			default:
				throw new RuntimeException("Error occured by running the commands '" + commands + "'." );
		}
	}
}
