//package declaration
package ch.nolix.common.endPoint5;

import ch.nolix.common.communicationAPI.IReplier;
import ch.nolix.common.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
final class Replier implements IReplier {

	//attribute
	private final NetEndPoint netEndPoint;
	
	//constructor
	/**
	 * Creates a new receiver that belongs to the given net duplex controller.
	 * 
	 * @param netEndPoint
	 * @throws ArgumentIsNullException if the given net duplex controller is null.
	 */
	public Replier(final NetEndPoint netEndPoint) {
		
		//Checks if the given net duplex controller is not null.
		Validator.throwExceptionIfValueIsNull("net controller", netEndPoint);
		
		//Sets the net duplex controller of this replier.
		this.netEndPoint = netEndPoint;
	}
	
	//method
	/**
	 * @return the reply to the given message from this replier.
	 */
	@Override
	public String getReply(final String message) {
		return netEndPoint.receiveAndGetReply(message);
	}
}
