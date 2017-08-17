//package declaration
package ch.nolix.system.consoleClient;

//own import
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.system.client.Client;

//class
/**
* A console client is a client that provides a console.
* 
* @author Silvan Wyss
* @month 2017-03
* @lines 40
*/
public final class ConsoleFrontClient extends Client<ConsoleFrontClient> {

	//attribute
	//private GUI<?> gui;
	
	//constructor
	/**
	 * Creates new front console client that connects to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public ConsoleFrontClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		internal_connect(ip, port, targetApplication);
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this front console client.
	 */
	protected void internal_finishSessionInitialization() {}
	
	public void setGUI(final GUI<?> gui) {
		
		Validator.supposeThat(gui).thatIsInstanceOf(GUI.class).isNotNull();
		
		//this.gui = gui;	
	}
}
