//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.communicationInterfaces.IReplier;
import ch.nolix.core.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
final class Replier implements IReplier {

	//attribute
	private final NetDuplexController netDuplexController;
	
	//constructor
	/**
	 * Creates new receiver that belongs to the given net duplex controller.
	 * 
	 * @param netDuplexController
	 * @throws NullArgumentException if the given net duplex controller is null.
	 */
	public Replier(final NetDuplexController netDuplexController) {
		
		//Checks if the given net duplex controller is not null.
		Validator.throwExceptionIfValueIsNull("net controller", netDuplexController);
		
		//Sets the net duplex controller of this replier.
		this.netDuplexController = netDuplexController;
	}
	
	//method
	/**
	 * @return the reply to the given message from this replier.
	 */
	public String getReply(final String message) {
		return netDuplexController.receiveAndGetReply(message);
	}
}
