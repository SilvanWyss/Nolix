/*
 * file:	AlphaEndPointListener.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	190
 */

//package declaration
package ch.nolix.common.zetaEndPoint;

//java import
import java.net.ServerSocket;


//own imports
import ch.nolix.common.constants.PortManager;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.util.Validator;

//class
/**
 * An alpha end point listener listens to alpha end points on a certain port.
 */
public class AlphaEndPointListener extends Thread implements Abortable {
	
	//attributes
	private final int port;
	private final IAlphaEndPointTaker alphaEndPointTaker;
	private boolean stopped = false;
	
	//optional attribute
	private String stopReason;
		
	//constructor
	/**
	 * Creates new alpha end point listener with the given port and alpha end point taker.
	 * The new alpha end point listener will start automatically.
	 * 
	 * @param port
	 * @param alphaEndPointTaker
	 * @throws Exception if:
	 *  -the given port is negative or bigger than 65535
	 *  -the given alpha end point taker is null
	 */
	public AlphaEndPointListener(int port, IAlphaEndPointTaker alphaEndPointTaker) {
		
		//Calls other constructor.
		this(port, alphaEndPointTaker, true);
	}
	
	//constructor
	/**
	 * Creates new end point listener with the given port and the given alpha end point taker.
	 * The new alpha end point listener will start if the given start is true.
	 * 
	 * @param port
	 * @parma alphaEndPointTaker
	 * @param start
	 * @throws Exception if:
	 *  -the given port is negative or bigger than 65535
	 *  -the given alpha end point taker is null
	 */
	public AlphaEndPointListener(int port, IAlphaEndPointTaker alphaEndPointTaker, boolean start) {
		
		Validator.throwExceptionIfValueIsNotInRange(
			"port",
			PortManager.MIN_PORT,
			PortManager.MAX_PORT,
			port
		);
		
		Validator.throwExceptionIfValueIsNull("alpha end point taker", alphaEndPointTaker);
		
		this.port = port;
		this.alphaEndPointTaker = alphaEndPointTaker;
		
		if (start) {
			start();
		}
	}
	
	//method
	/**
	 * @return the port of this alpha end point listener
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
	 * @return true if this alpha end point listener is stopped
	 */
	public final boolean isAborted() {
		return stopped;
	}
	
	//method
	/**
	 * Runs this alpha end point listener.
	 */
	public final void run() {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(getPort());
			
			while (isRunning()) {
				AlphaEndPoint alphaEndPoint = new AlphaEndPoint(serverSocket.accept());
				Sequencer.runInBackground(() -> alphaEndPointTaker.takeAlphaEndPoint(alphaEndPoint));
			}			
		}
		catch (Exception e) {	
			abort(e.getMessage());
			return;
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
	 * Stops this alpha end point listener.
	 * 
	 * @throws Exception if this alpha end point listener is stopped already
	 */
	public final void abort() {
		
		throwExceptionIfStopped();
		
		stopped = true;
	}
	
	//method
	/**
	 * Stops this alpha end point listener because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if:
	 *  -the given stop reason is null or an empty string
	 *  -this alpha end point listener is stopped already
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
			throw new RuntimeException("Alpha end point listener is stopped.");
		}
	}
	
	//method
	/**
	 * @throws Exception if this alpha end point listener is not stopped
	 */
	private final void throwExceptionIfNotStopped() {
		if (!isAborted()) {
			throw new RuntimeException("Alpha end point listener is not stopped.");
		}
	}
}
