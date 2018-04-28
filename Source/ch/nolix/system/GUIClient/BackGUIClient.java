//package declaration
package ch.nolix.system.GUIClient;

import ch.nolix.core.container.IContainer;
//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.system.client.Client;

//class
/**
 * A dialog client is a client that provides a dialog.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 190
 */
public final class BackGUIClient extends Client<BackGUIClient> {
	
	//commands
	static final String RESET_DIALOG_COMMAND = "ResetDialog";
	static final String ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI = "AddOrChangeInteractionAttributesOfWidgetsOfGUI";
	static final String RESET_OTHER_SIDE_DIALOG_COMMAND = "ResetOtherSideDialog";
	
	//attribute
	private final GUI<?> dialog;

	public BackGUIClient(final DuplexController duplexController) {
			
		//Calls constructor of the base class.
		internal_connectWith(duplexController);
		
		this.dialog = new InvisibleGUI();
	}
	
	//constructor
	/**
	 * Creates a new dialog client that:
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
	 * Creates a new dialog client with the given duplex controller and the given initial session.
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
		internal_invokeRunMethod(new StandardSpecification(runMethodCommand));
		internal_runOnCounterpart(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Resets this back GUI client.
	 * 
	 * @return this back GUI client.
	 */
	public BackGUIClient reset() {
		
		dialog.reset();
		internal_runOnCounterpart(RESET_DIALOG_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this dialog client.
	 */
	protected void internal_finishSessionInitialization() {
		internal_runOnCounterpart(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
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
		case ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI:
				addOrChangeInteractionAttributesToWidgetsOfGUI(
					command.getRefAttributes().to(a -> a.getRefAttributes())
				);
				break;
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
	 * Adds or changes the given interaction attributes to the widgets of this {@link BackGUIClient}.
	 * 
	 * @param interactionAttributesOfWidgetsOfGUI
	 * @throws InvalidArgumentException if the given interaction attributes are not valid.
	 */
	private <S extends Specification> void addOrChangeInteractionAttributesToWidgetsOfGUI(
		final IContainer<IContainer<S>> interactionAttributesOfWidgetsOfGUI	
	) {
		getRefGUI().addOrChangeInteractionAttributesOfWidgets(interactionAttributesOfWidgetsOfGUI);
	}
	
	//method
	/**
	 * Resets the dialog of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetDialog(final Iterable<? extends Specification> attributes) {
		dialog.reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final Iterable<StandardSpecification> attributes) {
		internal_runOnCounterpart(BackGUIClient.RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
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
