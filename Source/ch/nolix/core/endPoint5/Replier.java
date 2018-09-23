//package declaration
package ch.nolix.core.endPoint5;

import ch.nolix.core.communicationAPI.IReplier;
import ch.nolix.primitive.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
final class Replier implements IReplier {

	//attribute
	private final NetEndPointController netEndPointController;
	
	//constructor
	/**
	 * Creates a new receiver that belongs to the given net duplex controller.
	 * 
	 * @param netEndPointController
	 * @throws NullArgumentException if the given net duplex controller is not an instance.
	 */
	public Replier(final NetEndPointController netEndPointController) {
		
		//Checks if the given net duplex controller is an instance.
		Validator.throwExceptionIfValueIsNull("net controller", netEndPointController);
		
		//Sets the net duplex controller of this replier.
		this.netEndPointController = netEndPointController;
	}
	
	//method
	/**
	 * @return the reply to the given message from this replier.
	 */
	public String getReply(final String message) {
		return netEndPointController.receiveAndGetReply(message);
	}
}
