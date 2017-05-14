/*
 * file:	AlphaEndPointListener.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	190
 */

//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.constants.PortManager;
import ch.nolix.core.interfaces.Abortable;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.util.Validator;

//class
/**
 * An alpha end point listener listens to alpha end points on a certain port.
 */
public class Server implements Abortable {
	
	//attributes
	private final int port;
	private final IZetaEndPointTaker alphaEndPointTaker;
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
	public Server(int port, IZetaEndPointTaker alphaEndPointTaker) {
		
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
	public Server(int port, IZetaEndPointTaker alphaEndPointTaker, boolean start) {
		
		Validator.throwExceptionIfValueIsNotInRange(
			"port",
			PortManager.MIN_PORT,
			PortManager.MAX_PORT,
			port
		);
		
		Validator.throwExceptionIfValueIsNull("alpha end point taker", alphaEndPointTaker);
		
		this.port = port;
		this.alphaEndPointTaker = alphaEndPointTaker;
		
		new ZetaEndPointListener(this).start();
	}
	
	public void addEndPointTaker(IZetaEndPointTaker endPointTaker) {
		
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
	
	public void takeZetaEndPoint(final ZetaEndPoint zetaEndPoint) {
		alphaEndPointTaker.takeAlphaEndPoint(zetaEndPoint);
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
