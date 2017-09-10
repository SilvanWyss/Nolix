//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.duplexController.IDuplexControllerTaker;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
final class NetServerSubDuplexControllerTaker implements IDuplexControllerTaker {
	
	//name
	private static final String NAME = "InternalDuplexControllerTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates new net server sub duplex controller taker with the given net server.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given net server is null.
	 */
	public NetServerSubDuplexControllerTaker(final NetServer netServer) {
		
		//Checks if the given server is not null.
		Validator
		.supposeThat(netServer)
		.thatIsInstanceOf(NetServer.class)
		.isNotNull();
		
		//Sets the net server of this snet server sub duplex controller taker.
		this.netServer = netServer;
	}

	//method
	/**
	 * @return the name of this net server sub duplex controller taker.
	 */
	public String getName() {
		return NAME;
	}
	
	//method
	/**
	 * Lets this net server sub duplex controller taker take the given duplex controller.
	 * 
	 * @param duplexController
	 */
	public void takeDuplexController(final DuplexController duplexController) {	
		netServer.takeDuplexController(duplexController);
	}
}
