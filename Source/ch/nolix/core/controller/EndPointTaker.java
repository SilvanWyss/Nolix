/*
 * file:	AlphaEndPointTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	40
 */

//package declaration
package ch.nolix.core.controller;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.validator.Validator;

//package-visible class
final class EndPointTaker implements IEndPointTaker {

	//attribute
	private final NetServer netDuplexControllerListener;
	
	//constructor
	public EndPointTaker(final NetServer netDuplexControllerListener) {
		
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
	public void takeEndPoint(EndPoint alphaEndPoint) {
		netDuplexControllerListener.takeAlphaEndPoint(alphaEndPoint);
	}

	@Override
	public String getName() {
		return "Test";
	}
}
