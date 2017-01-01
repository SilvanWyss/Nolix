/*
 * file:	AlphaEndPoint.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	290
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;



import ch.nolix.common.basic.AbortableElement;
//own imports
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.IZetaReceiver;
import ch.nolix.common.util.Validator;

//class
/**
 * An alpha and point can send messages to an other alpha end point on:
 * 	-the same process
 * 	-other process on the same computer
 *  -process on an other computer
 */
public final class AlphaEndPoint extends AbortableElement {
	
	//default value
	public final static int DEFAULT_TIMEOUT_IN_MILLISECONDS = 2000;

	//attributes
	private int nextPackageIndex = 1;
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	private final Socket socket;
	private final PrintWriter printWriter;
	
	//optional attribute
	private IZetaReceiver alphaReceiver;
	
	//multiple attribute
	private final List<AdvancedPackage> receivedPackages = new List<AdvancedPackage>();

	//constructor
	/**
	 * Creates new alpha end point with the given socket.
	 * 
	 * @param socket
	 * @throws Exception if the given socket is null
	 */
	public AlphaEndPoint(Socket socket) {
		
		//Checks the given socket.
		Validator.throwExceptionIfValueIsNull("socket", socket);
		
		this.socket = socket;
		
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		//Creates and starts listener of this alpha end point.
		new Listener(this);
	}
	
	//constructor
	/**
	 * Creates new alpha end point that will connect to the given port on the computer with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws Exception if the given port is smaller than 0 or bigger than 65535
	 */
	public AlphaEndPoint(String ip, int port) {

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
		
		//Creates and starts listener of this alpha end point.
		new Listener(this);
	}
	
	public AlphaEndPoint(int port) {
		this("::1", port);
	}
	
	//method
	/**
	 * @return the timeout of this alpha end point in milliseconds
	 */
	public final int getTimeoutInMilliseconds() {
		return timeoutInMilliseconds;
	}
	
	//method
	/**
	 * @return true if this alpha end point has a receiver
	 */
	public final boolean hasReceiver() {
		return (alphaReceiver != null);
	}

	//method
	/**
	 * Sends the given message and returns the reply.
	 * 
	 * @param message
	 * @return reply of the given message
	 * @throws Exception if:
	 *  -this alpha end point is stopped
	 *  -an error occures by trying to send the given message
	 */
	public final String sendAndGetResponse(String message) {
		
		throwExceptionIfStopped();
		
		final int index = getNextPackageIndex();
		send(new AdvancedPackage(index, MessageRole.NORMAL_MESSAGE, message));
		
		final AdvancedPackage response = waitToAndRemoveAndGetReceivedResponse(index, message);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE_MESSAGE:
				return response.getMessage();
			case ERROR_RESPONSE_MESSAGE:
				throw new RuntimeException(response.getMessage());
			default:
				throw new RuntimeException("An error occured.");
		}
	}
	
	//method
	/**
	 * Sets the alpha receiver of this alpha end point.
	 * 
	 * @param receiver
	 */
	public final void setAlphaReceiver(IZetaReceiver receiver) {
		this.alphaReceiver = receiver;
	}
	
	//method
	/**
	 * Sets the timeout of this alpha end point in milliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws Exception if:
	 *  -the given timeout is not positive
	 *  -this alpha end point is stopped
	 */
	public final void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
		
		Validator.throwExceptionIfValueIsNotPositive("timeout", timeoutInMilliseconds);
		
		throwExceptionIfStopped();

		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}
	
	//method
	/**
	 * @return the socket of this alpha end point
	 */
	final Socket getRefSocket() {
		return socket;
	}
	
	//method
	/**
	 * Receives the given package.
	 * @param message
	 */
	void receive(String package_) {
		
		final AdvancedPackage package__ = new AdvancedPackage(package_);

		//Enumerates the received package.
		switch (package__.getMessageRole()) {
			case NORMAL_MESSAGE:
				try {
					if (!hasReceiver()) {
						throw new UnexistingAttributeException(this, "alpha receiver");
					}
					
					String responseMessage = getRefReceiver().receiveMessageAndGetReply(package__.getMessage());
					send(new AdvancedPackage(package__.getIndex(), MessageRole.SUCCESS_RESPONSE_MESSAGE, responseMessage));
				}
				catch (Exception e) {
					String responseMessage = e.getMessage();
					send(new AdvancedPackage(package__.getIndex(), MessageRole.ERROR_RESPONSE_MESSAGE, responseMessage));
				}
				break;
			default:
				receivedPackages.addAtEnd(package__);
		}
	}
	
	//method
	/**
	 * @return the index of the next message of this alpha end point
	 */
	private final int getNextPackageIndex() {
		
		if (nextPackageIndex == Integer.MAX_VALUE) {
			nextPackageIndex = 0;
		}
		
		return nextPackageIndex++;
	}
	
	//method
	/**
	 * @return the receiver of this alpha end point
	 * @throws Exception if this alpha end point has no receiver
	 */
	private final IZetaReceiver getRefReceiver() {
		
		if (!hasReceiver()) {
			throw new UnexistingAttributeException(this, "receiver");
		}
		
		return alphaReceiver;
	}
	
	//method
	/**
	 * @param index
	 * @return true if this alpha end point has received a reply with the given index
	 */
	private final boolean receivedPackage(int index) {
		return receivedPackages.contains(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * Removes and returns the received reply with the given index
	 * 
	 * @param index
	 * @return the reply with the given index
	 * @throws Exception if this alpha end point has not received a reply with the given index
	 */
	private final AdvancedPackage removeAndGetReceivedResponse(int index) {
		return this.receivedPackages.getRefFirst(p -> p.hasIndex(index));
	}
	
	//method
	/**
	 * Sends the given package.
	 * 
	 * @param package_
	 */
	private void send(AdvancedPackage package_) {
		printWriter.println(package_.toString());
		printWriter.flush();
	}
	
	//method
	/**
	 * Waits to and removes and returns the reply with the given end point.
	 * 
	 * @param index
	 * @return reply with the given index
	 * @throws Exception if the timeout is reached before this alpha end point has received a reply with the given index
	 */
	private AdvancedPackage waitToAndRemoveAndGetReceivedResponse(int index, String message) {
		
		long startTimeInMilliseconds = System.currentTimeMillis();
		
		while (!receivedPackage(index)) {
			if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
				throw new RuntimeException("Alpha end point reached timeout by waiting to response to message with index " + index + ". The message was '" + message + "'");
			}
		}

		return removeAndGetReceivedResponse(index);
	}
}
