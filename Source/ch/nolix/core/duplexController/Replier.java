/*
 * file:	Receiver.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	30
 */

//package declaration
package ch.nolix.core.duplexController;

//own import
import ch.nolix.core.communicationInterfaces.IReplier;
import ch.nolix.core.validator.Validator;

//package-visible class
final class Replier implements IReplier {

	//attribute
	private final NetDuplexController netDuplexController;
	
	//constructor
	/**
	 * Creates new receiver that belongs to the given net controller.
	 * 
	 * @param netDuplexController
	 */
	public Replier(NetDuplexController netDuplexController) {
		
		Validator.throwExceptionIfValueIsNull("net controller", netDuplexController);
		
		this.netDuplexController = netDuplexController;
	}
	
	public String getReply(String message) {
		return netDuplexController.receiveAndGetReply(message);
	}
}
