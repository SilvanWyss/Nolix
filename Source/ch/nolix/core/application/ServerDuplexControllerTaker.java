//package declaration
package ch.nolix.core.application;

//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.duplexController.IDuplexControllerTaker;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A server duplex controller taker is the duplex controller taker of the duplex controller listener of a server.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
final class ServerDuplexControllerTaker implements IDuplexControllerTaker {
	
	//attribute
	private final Server server;
	
	//constructor
	/**
	 * Creates new server duplex controller taker with the given server.
	 * 
	 * @param server
	 * @throws NullArgumentException if the given server is null.
	 */
	public ServerDuplexControllerTaker(final Server server) {
		
		//Checks if the given server is not null.
		Validator.supposeThat(server).thatIsInstanceOf(Server.class).isNotNull();
		
		//Sets the server of this server duplex controller taker.
		this.server = server;
	}

	//method
	/**
	 * Lets this server duplex controller taker take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	public void takeDuplexController(final DuplexController duplexController) {
		
		//Extracts the name of the target application.
		final String targetApplicaitonName = duplexController.getData(Client.TARGET_APPLICATION_REQUEST).toString();
		
		//Extracts the target application.
		final Application<?> targetApplication = server.getRefApplicationByName(targetApplicaitonName);
		
		//Creates client that belongs to the target application.
		targetApplication.createClient(duplexController);
	}
}
