/*
 * file:	AlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	40
 */

//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.util.Validator;

//package-visible class
final class AlphaEndPointTaker implements IEndPointTaker {

	//attribute
	private final DuplexControllerListener netDuplexControllerListener;
	
	//constructor
	public AlphaEndPointTaker(final DuplexControllerListener netDuplexControllerListener) {
		
		//Checks the given net duplex controller listener.
		Validator.throwExceptionIfValueIsNull("net duplex controller listener", netDuplexControllerListener);
		
		this.netDuplexControllerListener = netDuplexControllerListener;
	}
	
	//method
	/**
	 * Lets this alpha end point taker take the given alpha end point.
	 * 
	 * @param alphaEndPoint
	 */
	public void takeEndPoint(NetEndPoint alphaEndPoint) {
		netDuplexControllerListener.takeAlphaEndPoint(alphaEndPoint);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
