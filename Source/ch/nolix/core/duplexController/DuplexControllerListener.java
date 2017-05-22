/*
 * file:	NetDuplexControllerListener
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	10
 */

//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.NetServer;
import ch.nolix.core.interfaces.Abortable;
import ch.nolix.core.util.Validator;

//class
/**
 * A net duplex controller listener listens to net duplex controllers on a specific port.
 */
public final class DuplexControllerListener implements Abortable {

	//attributes
	private final IDuplexControllerTaker duplexControllerTaker;
	private final NetServer alphaEndPointListener;
	
	public DuplexControllerListener(final int port, final IDuplexControllerTaker duplexControllerTaker) {
		
		//Checks the given duplex controller taker.
		Validator.throwExceptionIfValueIsNull("duplex controller taker", duplexControllerTaker);
		
		this.duplexControllerTaker = duplexControllerTaker;
		this.alphaEndPointListener = new NetServer(port);
		alphaEndPointListener.addEndPointTaker(new AlphaEndPointTaker(this));
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
		duplexControllerTaker.takeDuplexController(new NetDuplexController(alphaEndPoint));
	}
}
