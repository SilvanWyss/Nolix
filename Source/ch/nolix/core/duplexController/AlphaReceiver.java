/*
 * file:	Receiver.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	30
 */

//package declaration
package ch.nolix.core.duplexController;

//own import
import ch.nolix.core.interfaces.IZetaReceiver;
import ch.nolix.core.util.Validator;

//package-visible class
final class AlphaReceiver implements IZetaReceiver {

	//attribute
	private final NetDuplexController netController;
	
	//constructor
	/**
	 * Creates new receiver that belongs to the given net controller.
	 * 
	 * @param netController
	 */
	public AlphaReceiver(NetDuplexController netController) {
		
		Validator.throwExceptionIfValueIsNull("net controller", netController);
		
		this.netController = netController;
	}
	
	public String receiveMessageAndGetReply(String message) {
		return netController.receiveMessageAndGetReply(message);
	}
}
