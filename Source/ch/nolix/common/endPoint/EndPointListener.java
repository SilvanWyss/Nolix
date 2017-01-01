/*
 * file:	EndPointListener.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	190
 */

//package declaration
package ch.nolix.common.endPoint;

//java import
import java.net.ServerSocket;

//own imports


import ch.nolix.common.constants.PortManager;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.util.Validator;

//class
/**
 * An end point listener listens to end points on a certain port.
 */
public class EndPointListener extends Thread implements Abortable {
	
	//members
	private final int port;
	private final IEndPointTaker endPointTaker;
	private boolean stopped = false;
	
	//optional members
	private String stopReason;
		
	//constructor
	/**
	 * Creates new end point listener with the given port and end point taker.
	 * The new end point will start automatically.
	 * 
	 * @param port
	 * @throws Exception if
	 *  -the given port is negative or bigger than 65535
	 *  -the given end point taker is null
	 */
	public EndPointListener(int port, IEndPointTaker endPointTaker) {
		
		//Calls other constructor.
		this(port, endPointTaker, true);
	}
	
	//constructor
	/**
	 * Creates new end point listener with the given port and end point taker.
	 * The new end point will start automatically if the given start is true.
	 * 
	 * @param port
	 * @param start
	 * @throws Exception if:
	 *  -the given port is negative or bigger than 65535
	 *  -the given end point taker is null
	 */
	public EndPointListener(int port, IEndPointTaker endPointTaker, boolean start) {
		
		Validator.throwExceptionIfValueIsNotInRange(
			"port",
			PortManager.MIN_PORT,
			PortManager.MAX_PORT,
			port
		);
		
		Validator.throwExceptionIfValueIsNull("end point taker", endPointTaker);
		
		this.port = port;
		this.endPointTaker = endPointTaker;
		
		if (start) {
			start();
		}
	}
	
	//method
	/**
	 * @return the port of this end point listener
	 */
	public final int getPort() {
		return port;
	}
	
	//method
	/**
	 * @return the stop reason of this alpha end point listener
	 * @throws Exception if:
	 *  -this alpha end point listener is not stopped
	 *  -this alpha end point listener has no stop reason
	 */
	public final String getAbortReason() {
		
		throwExceptionIfNotStopped();
		
		if (stopReason == null) {
			throw new UnexistingAttributeException(this, "stop reason");
		}
		
		return stopReason;
	}
	
	//method
	/**
	 * @return true if this end point listener is stopped
	 */
	public final boolean isAborted() {
		return stopped;
	}

	//method
	/**
	 * Runs this end point listener.
	 */
	public final void run() {
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(getPort());
			
			while (isRunning()) {
				new EndPointTaking(endPointTaker, new EndPoint(serverSocket.accept()));
			}
		}
		catch (Exception e) {
			abort(e.getMessage());
		}
		finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//method
	/**
	 * Stops this end point listener.
	 * 
	 * @throws Exception if this end point listener is stopped already
	 */
	public final void abort() {
		
		throwExceptionIfStopped();
		
		stopped = true;
	}
	
	//method
	/**
	 * Stops this end point listener because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if:
	 *  -the given stop reason is null or an empty string
	 *  -this end point listener is stopped already
	 */
	public final void abort(String stopReason) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty(stopReason, "stop reason");
		
		abort();
		this.stopReason = stopReason;
	}
	
	//method
	/**
	 * @throws Exception if this alpha end point listener is stopped
	 */
	private final void throwExceptionIfStopped() {
		if (isAborted()) {
			throw new RuntimeException("End point listener is stopped.");
		}
	}
	
	//method
	/**
	 * @throws Exception if this alpha end point listener is not stopped
	 */
	private final void throwExceptionIfNotStopped() {
		if (!isAborted()) {
			throw new RuntimeException("End point listener is not stopped.");
		}
	}
}
