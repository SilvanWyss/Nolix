/*
 * file:	NetDuplexControllerListener
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	10
 */

//package declaration
package ch.nolix.core.controller;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.interfaces.Abortable;
import ch.nolix.core.validator.Validator;

//class
/**
 * A net duplex controller listener listens to net duplex controllers on a specific port.
 */
public final class NetServer implements Abortable {

	//attributes
	private final IControllerTaker controllerTaker;
	private final ch.nolix.core.endPoint3.NetServer alphaEndPointListener;
	
	public NetServer(final int port, final IControllerTaker controllerTaker) {
		
		//Checks the given duplex controller taker.
		Validator.throwExceptionIfValueIsNull("duplex controller taker", controllerTaker);
		
		this.controllerTaker = controllerTaker;
		this.alphaEndPointListener = new ch.nolix.core.endPoint3.NetServer(port);
		alphaEndPointListener.addEndPointTaker(new EndPointTaker(this));
	}
	
	//method
	public String getAbortReason() {
		return alphaEndPointListener.getAbortReason();
	}
	
	//method
	public boolean isAborted() {
		return alphaEndPointListener.isAborted();
	}
	
	//method
	public void abort() {
		alphaEndPointListener.abort();
	}
	
	//method
	public void abort(final String stopReason) {
		alphaEndPointListener.abort(stopReason);
	}

	//package-visible method
	/**
	 * Lets this net duplex controller listener take the given alpha end point
	 * 
	 * @param alphaEndPoint
	 * @throws Exception if the given alpha end point is null
	 */
	void takeAlphaEndPoint(final EndPoint alphaEndPoint) {
		controllerTaker.takeDuplexController(new NetController(alphaEndPoint));
	}
}
