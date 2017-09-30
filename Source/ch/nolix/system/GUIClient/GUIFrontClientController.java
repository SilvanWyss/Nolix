//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel1Controller;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
* @author Silvan Wyss
* @month 2016-11
* @lines 40
*/
final class GUIFrontClientController implements ILevel1Controller {
	
	//attribute
	private final GUIFrontClient frontDialogClient;
	
	/**
	 * Creates new front dialog client controller that belongs to the given front dialog client.
	 * 
	 * @param dialogFrontEndClient
	 * @throws NullArgumentException if the given front dialog client is null.
	 */
	public GUIFrontClientController(final GUIFrontClient frontDialogClient) {
		
		//Checks if the given front dialog client is not null.
		Validator.suppose(frontDialogClient).thatIsInstanceOf(GUIFrontClient.class).isNotNull();
		
		//Sets the front dialog client of this front dialog client controller.
		this.frontDialogClient = frontDialogClient;
	}

	//method
	/**
	 * Lets this front dialog client controller run the given comman.d
	 * 
	 * @param command
	 */
	public void run(final Statement command) {
		frontDialogClient.run(command);
	}
}
