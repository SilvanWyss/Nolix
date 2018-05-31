//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.controllerInterfaces.IController;
import ch.nolix.core.specification.Statement;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
* @author Silvan Wyss
* @month 2016-11
* @lines 40
*/
final class FrontGUIClientController implements IController {
	
	//attribute
	private final FrontGUIClient frontDialogClient;
	
	/**
	 * Creates a new front dialog client controller that belongs to the given front dialog client.
	 * 
	 * @param dialogFrontEndClient
	 * @throws NullArgumentException if the given front dialog client is null.
	 */
	public FrontGUIClientController(final FrontGUIClient frontDialogClient) {
		
		//Checks if the given front dialog client is not null.
		Validator.suppose(frontDialogClient).thatIsOfType(FrontGUIClient.class).isNotNull();
		
		//Sets the front dialog client of this front dialog client controller.
		this.frontDialogClient = frontDialogClient;
	}

	//TODO: Implement widget call mechanism.
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
