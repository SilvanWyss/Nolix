/*
 * file:	DuplexControllerTaker.java
 * author:	Silvan Wyss
 * month:	2016-10
 * lines:	10
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.IDuplexControllerTaker;
import ch.nolix.common.util.Validator;

//package-visible class
final class ServerDuplexControllerTaker implements IDuplexControllerTaker {
	
	//attribute
	private final Server server;
	
	//constructor
	public ServerDuplexControllerTaker(final Server server) {
		
		Validator.throwExceptionIfValueIsNull("server", server);
		
		this.server = server;
	}

	//method
	public void takeDuplexController(DuplexController duplexController) {
		server.takeDuplexController(duplexController);	
	}
}
