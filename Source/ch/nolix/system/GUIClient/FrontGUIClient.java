//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.application.Application;
import ch.nolix.core.application.Client;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.ZetaValidator;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.Frame;

//class
/**
 * A front dialog client is a client that is controlled by a dialog client.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 150
 */
public final class FrontGUIClient extends Client<FrontGUIClient> {
	
	//attribute
	private GUI<?> dialog;
	
	//constructor
	/**
	 * Creates new front dialog client that conntects to the given target application on the given port on the machien with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 */
	public FrontGUIClient(final Application<GUIClient> targetApplication) {
		
		//Calls constructor of the base class.
		super(
			targetApplication,
			c -> {
				c.setDialog(
					new Frame()
					.setController(new FrontGUIClientController(c))
				);
			}
		);
	}
	
	//constructor
	/**
	 * Creates new front dialog client that conntects to the given target application on the given port on the machien with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public FrontGUIClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(
			ip,
			port,
			targetApplication,
			c -> {
				c.setDialog(
					new Frame()
					.setController(new FrontGUIClientController(c))
				);
			}
		);
	}
	
	//method
	/**
	 * Runs a run method on the other side of this front dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void run(final Statement runMethodCommand) {
		internal_getRefDuplexController().appendCommand(GUIClient.RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
		internal_getRefDuplexController().appendCommand(INVOKE_RUN_METHOD_COMMAND + "(" + runMethodCommand + ")");
		internal_getRefDuplexController().appendCommand(GUIClient.RESET_OTHER_SIDE_DIALOG_COMMAND);
		internal_getRefDuplexController().runAppendedCommands();
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this front dialog client.
	 */
	protected void internal_finishSessionInitialization() {}
	
	//method
	/**
	 * Lets this front dialog client run the given command.
	 * 
	 * @param command
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case GUIClient.RESET_DIALOG_COMMAND:
				resetDialog(command.getRefAttributes());
				break;
			case GUIClient.RESET_OTHER_SIDE_DIALOG_COMMAND:
				resetOtherSideDialog(command.getRefAttributes());
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	
	//method
	/**
	 * Resets the dialog of this front dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetDialog(final List<Specification> attributes) {

		dialog.reset(attributes);
		dialog.updateFromConfiguration();
		dialog.noteMouseMove();
		
		//dialog.setMousePosition(x, y);
		dialog.noteMouseMove();
		//dialog.updateFromInteraction();
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this front dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final List<Specification> attributes) {
		internal_getRefDuplexController().run(GUIClient.RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Sets the dialog of this front dialog client.
	 * 
	 * @param dialog
	 * @throws NullArgumentException if the given dialog is null.
	 */
	private void setDialog(final GUI<?> dialog) {
		
		//Checks if the given dialog is not null.
		ZetaValidator.supposeThat(dialog).thatIsInstanceOf(GUI.class).isNotNull();
			
		//Sets the dialog of this front dialog client.
		this.dialog = dialog;
	}
}
