/*
 * file:	DuplexControllerTaker.java
 * author:	Silvan Wyss
 * month:	2016-09
 * lines:	10
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.duplexController.IDuplexControllerTaker;
import ch.nolix.common.util.Validator;

//package-visible class
final class ZetaServerDuplexControllerTaker implements IDuplexControllerTaker {
	
	//attribute
	private final ZetaServer server;
	
	//constructor
	public ZetaServerDuplexControllerTaker(final ZetaServer server) {
		
		Validator.throwExceptionIfValueIsNull("server", server);
		
		this.server = server;
	}

	//method
	public void takeDuplexController(DuplexController duplexController) {
		server.takeDuplexController(duplexController);	
	}
}
