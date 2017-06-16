//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.validator.Validator;

//class
/**
 * A net server is a server that listens to net duplex controllers on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 10
 */
public final class NetServer extends Server {

	//attributes
	
	private final ch.nolix.core.endPoint3.NetServer alphaEndPointListener;
	
	public NetServer(final int port, final IDuplexControllerTaker duplexControllerTaker) {
		
		//Checks the given duplex controller taker.
		Validator.throwExceptionIfValueIsNull("duplex controller taker", duplexControllerTaker);
		
		this.duplexControllerTaker = duplexControllerTaker;
		this.alphaEndPointListener = new ch.nolix.core.endPoint3.NetServer(port);
		alphaEndPointListener.addEndPointTaker(new EndPointTaker(this));
	}

	//package-visible method
	/**
	 * Lets this net duplex controller listener take the given alpha end point
	 * 
	 * @param alphaEndPoint
	 * @throws Exception if the given alpha end point is null
	 */
	void takeNetEndPoint(final NetEndPoint alphaEndPoint) {
		duplexControllerTaker.takeDuplexController(new NetDuplexController(alphaEndPoint));
	}
}
