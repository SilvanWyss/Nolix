//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.core.controller.Controller;
import ch.nolix.core.controller.IControllerTaker;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A server duplex controller taker is the duplex controller taker of the duplex controller listener of a server.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
final class ServerDuplexControllerTaker implements IControllerTaker {
	
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
	 * @param controller
	 */
	public void takeDuplexController(final Controller controller) {
		
		//Extracts the name of the target application.
		final String targetApplicaitonName = controller.getData(Client.TARGET_APPLICATION_REQUEST).toString();
		
		//Extracts the target application.
		final Application<?> targetApplication = server.getRefApplicationByName(targetApplicaitonName);
		
		//Creates client that belongs to the target application.
		targetApplication.createClient(controller);
	}
}
