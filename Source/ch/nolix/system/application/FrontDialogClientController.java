//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;

//package-visible class
/**
* @author Silvan Wyss
* @month 2016-11
* @lines 40
*/
final class FrontDialogClientController implements ILevel1Controller {
	
	//attribute
	private final FrontDialogClient frontDialogClient;
	
	/**
	 * Creates new front dialog client controller that belongs to the given front dialog client.
	 * 
	 * @param dialogFrontEndClient
	 * @throws NullArgumentException if the given front dialog client is null.
	 */
	public FrontDialogClientController(final FrontDialogClient frontDialogClient) {
		
		//Checks if the given front dialog client is not null.
		ZetaValidator.supposeThat(frontDialogClient).thatIsInstanceOf(FrontDialogClient.class).isNotNull();
		
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
