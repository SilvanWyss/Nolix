/*
 * file:	AlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	40
 */

//package declaration
package ch.nolix.common.duplexController;

//own imports
import ch.nolix.common.util.Validator;
import ch.nolix.common.zetaEndPoint.AlphaEndPoint;
import ch.nolix.common.zetaEndPoint.IAlphaEndPointTaker;

//package-visible class
final class AlphaEndPointTaker implements IAlphaEndPointTaker {

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
	public void takeAlphaEndPoint(AlphaEndPoint alphaEndPoint) {
		netDuplexControllerListener.takeAlphaEndPoint(alphaEndPoint);
	}
}
