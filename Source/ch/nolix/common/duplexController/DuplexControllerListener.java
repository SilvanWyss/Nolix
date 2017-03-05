/*
 * file:	NetDuplexControllerListener
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	10
 */

//package declaration
package ch.nolix.common.duplexController;

//own imports
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.util.Validator;
import ch.nolix.common.zetaEndPoint.ZetaEndPoint;
import ch.nolix.common.zetaEndPoint.Server;

//class
/**
 * A net duplex controller listener listens to net duplex controllers on a specific port.
 */
public final class DuplexControllerListener implements Abortable {

	//attributes
	private final IDuplexControllerTaker duplexControllerTaker;
	private final Server alphaEndPointListener;
	
	public DuplexControllerListener(final int port, final IDuplexControllerTaker duplexControllerTaker) {
		
		//Checks the given duplex controller taker.
		Validator.throwExceptionIfValueIsNull("duplex controller taker", duplexControllerTaker);
		
		this.duplexControllerTaker = duplexControllerTaker;
		this.alphaEndPointListener = new Server(port, new AlphaEndPointTaker(this));
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
	void takeAlphaEndPoint(final ZetaEndPoint alphaEndPoint) {
		duplexControllerTaker.takeDuplexController(new NetDuplexController(alphaEndPoint));
	}
}
