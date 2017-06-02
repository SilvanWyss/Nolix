/*
 * file:	Receiver.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	30
 */

//package declaration
package ch.nolix.core.controller;

//own import
import ch.nolix.core.communicationInterfaces.IReplier;
import ch.nolix.core.validator.Validator;

//package-visible class
final class Replier implements IReplier {

	//attribute
	private final NetController netController;
	
	//constructor
	/**
	 * Creates new receiver that belongs to the given net controller.
	 * 
	 * @param netController
	 */
	public Replier(NetController netController) {
		
		Validator.throwExceptionIfValueIsNull("net controller", netController);
		
		this.netController = netController;
	}
	
	public String getReply(String message) {
		return netController.receiveMessageAndGetReply(message);
	}
}
