/*
 * file:	EndPoint.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	220
 */

//package declaration
package ch.nolix.common.endPoint;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//own imports





import ch.nolix.common.basic.AbortableElement;
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.IReceiver;
import ch.nolix.common.util.Validator;

//class
public final class EndPoint extends AbortableElement {
	
	//attributes
	private final Socket socket;
	private final PrintWriter printWriter;
	
	//optional attributes
	private IReceiver receiver;	
	
	//constructor
	/**
	 * Creates new end point with the given socket.
	 * 
	 * @param socket
	 * @throws Exception if the given socket is null
	 */
	public EndPoint(Socket socket) {
		
		Validator.throwExceptionIfValueIsNull("socket", socket);
		
		this.socket = socket;
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and starts listener of this end point.
		new Listener(this);
	}
	
	//constructor
	/**
	 * Creates new end point that will connect to the given port on the computer with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws Exception if the bigger port is smaller than 0 or bigger than 65535
	 */
	public EndPoint(String ip, int port) {
		
		Validator.throwExceptionIfValueIsNotInRange(
			"port",
			PortManager.MIN_PORT,
			PortManager.MAX_PORT,
			port
		);
		
		try {
			socket = new Socket(ip, port);
			printWriter = new PrintWriter(socket.getOutputStream());
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and start listener of this end point.
		new Listener(this);
	}
	
	//method
	/**
	 * @return true if this end point has a receiver
	 */
	public final boolean hasReceiver() {
		return (receiver != null);
	}
	
	//method
	/**
	 * Sends the given message.
	 * 
	 * @param message
	 * @throws Exception if an error occurs
	 */
	public final void send(String message) {
		printWriter.println(message);
		printWriter.flush();
	}
	
	//method
	/**
	 * Sets the receiver of this end point.
	 * 
	 * @param receiver
	 * @throws Exception if the given receiver is null
	 */
	public final void setReceiver(IReceiver receiver) {
		
		Validator.throwExceptionIfValueIsNull("receiver", receiver);
		
		this.receiver = receiver;
	}
	
	//method
	/**
	 * @return the socket of this end point
	 * @throws Exception if this end point is stopped
	 */
	final Socket getRefSocket() {
		
		throwExceptionIfStopped();
		
		return socket;
	}	
	
	//method
	/**
	 * @return the receiver of this end point
	 * @throws Exception if:
	 *  -this end point is stopped
	 *  -this end point has no receiver
	 */
	final IReceiver getRefReceiver() {
		
		throwExceptionIfStopped();
		
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, "receiver");
		}
		
		return receiver;
	}
}
