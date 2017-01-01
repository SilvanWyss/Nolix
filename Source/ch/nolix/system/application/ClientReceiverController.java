/*
 * file:	ReceiverController.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	50
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;

//package-visible class
/**
 * A client receiver controller is a receiver controller of the duplex controller of a client.
 */
class ClientReceiverController implements ILevel2Controller {

	//attribute
	private final Client<?> client;
	
	//constructor
	/**
	 * Creates new receiver controller with the given client.
	 * 
	 * @param client
	 */
	public ClientReceiverController(Client<?> client) {
		
		Validator.throwExceptionIfValueIsNull("client", client);
		
		this.client = client;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if the given request is not valid
	 */
	public final Object getRawData(Statement request) {
		return client.getData(request);
	}
	
	//method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if the given command is not valid
	 */
	public final void run(Statement command) {
		client.run(command);
	}
}
