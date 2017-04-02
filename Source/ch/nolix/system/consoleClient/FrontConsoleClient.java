//package declaration
package ch.nolix.system.consoleClient;

//own import
import ch.nolix.common.application.Client;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.GUI.GUI;

//class
/**
* A console client is a client that provides a console.
* 
* @author Silvan Wyss
* @month 2017-03
* @lines 40
*/
public final class FrontConsoleClient extends Client<FrontConsoleClient> {

	//attribute
	private GUI<?> gui;
	
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
	public FrontConsoleClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication);
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this front console client.
	 */
	protected void internal_finishSessionInitialization() {}
	
	private void setGUI(final GUI<?> gui) {
		
		ZetaValidator.supposeThat(gui).thatIsInstanceOf(GUI.class).isNotNull();
		
		this.gui = gui;
		
		
	}
}
