/*
 * file:	AlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	40
 */

//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 
 */
final class EndPointTaker implements IEndPointTaker {

	//attribute
	private final IDuplexControllerTaker duplexControllerTaker;
	
	//constructor
	public EndPointTaker(final IDuplexControllerTaker netDuplexControllerListener) {
		
		//Checks the given net duplex controller listener.
		Validator.throwExceptionIfValueIsNull("net duplex controller listener", netDuplexControllerListener);
		
		this.duplexControllerTaker = netDuplexControllerListener;
	}
	
	//method
	/**
	 * Lets this alpha end point taker take the given alpha end point.
	 * 
	 * @param alphaEndPoint
	 */
	public void takeEndPoint(EndPoint alphaEndPoint) {
		duplexControllerTaker.takeNetEndPoint((NetEndPoint)alphaEndPoint);
	}

	@Override
	public String getName() {
		return duplexControllerTaker.getName();
	}
}
