//package declaration
package ch.nolix.system.GUIClient;

//own imports
import java.lang.reflect.Method;

import ch.nolix.core.container.List;
import ch.nolix.core.controller.Controller;
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.system.client.Client;
import ch.nolix.system.client.Session;

//class
/**
 * A dialog client is a client that provides a dialog.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
 */
public final class GUIClient extends Client<GUIClient> {
	
	//commands
	static final String RESET_DIALOG_COMMAND = "ResetDialog";
	static final String RESET_OTHER_SIDE_DIALOG_COMMAND = "ResetOtherSideDialog";
	
	//attribute
	private final GUI<?> dialog;

	public GUIClient(final Controller controller) {
			
		//Calls constructor of the base class.
		super(controller);
		
		this.dialog = new InvisibleGUI();
	}
	
	//constructor
	/**
	 * Creates new dialog client that:
	 * -Will connect to the given target application.
	 * -Has the given dialog.
	 * 
	 * @param dialog
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws NullArgumentException if the given dialog is null.
	 */
	/*
	public GUIClient(final Application<GUIClient> targetApplication, final GUI<?> dialog) {
		
		//Calls constructor of the base class.
		//super(targetApplication, c -> c.setDialog(dialog));
		
		super(targetApplication);
		
		this.dialog = dialog;
	}
	
	//constructor
	/**
	 * Creates new dialog client with the given duplex controller and the given initial session.
	 * 
	 * @param controller
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	/*
	public GUIClient(final Controller controller, final Session<GUIClient> initialSession) {
		
		//Calls constructor of the base class.
		super(controller, c -> c.setDialog(new InvisibleGUI()), initialSession);
	}
	
	//method
	/**
	 * @return the dialog of this dialog client
	 */
	public GUI<?> getRefGUI() {
		return dialog;
	}
	
	//method
	/**
	 * Invokes a run method of this dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void runLocally(final String runMethodCommand) {
		internal_invokeRunMethod(new Specification(runMethodCommand));
		internal_getRefDuplexController().run(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Sets the session of this dialog client.
	 * 
	 * @param session
	 * @throws NullArgumentException if the given session is null.
	 * @throws RuntimeException if the given session has already a client.
	 */
	public void setSession(final Session<GUIClient> session) {
		internal_setSessionAndInitializeSession(session);
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this dialog client.
	 */
	protected void internal_finishSessionInitialization() {
		internal_getRefDuplexController().run(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case RESET_DIALOG_COMMAND:
				resetDialog(command.getRefAttributes());
				break;
			case RESET_OTHER_SIDE_DIALOG_COMMAND:
				resetOtherSideDialog(command.getRefAttributes());
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Resets the dialog of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetDialog(final List<Specification> attributes) {
		dialog.reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final List<Specification> attributes) {
		internal_getRefDuplexController().run(GUIClient.RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}

	//method
	/**
	 * Sets the dialog of this dialog client.
	 * 
	 * @param dialog
	 * @throws NullArgumentException if the given dialog is null.
	 */
	/*
	private void setDialog(final GUI<?> dialog) {
		
		//Checks if the given dialog is not null.
		Validator.supposeThat(dialog).thatIsInstanceOf(GUI.class).isNotNull();
		
		//Sets the dialog of this dialog client.
		this.dialog = dialog;
	}
	*/
}
