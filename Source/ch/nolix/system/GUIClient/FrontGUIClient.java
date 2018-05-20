//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.Client;

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
	
	public FrontGUIClient(Application<BackGUIClient> target) {
		dialog = new Frame();
		dialog.setController(new FrontGUIClientController(this));
		internal_connect(target);
	}
	
	//method
	/**
	 * Resets this front GUI client.
	 * 
	 * @return this front GUI client.
	 */
	public FrontGUIClient reset() {
		return this;
	}
	
	//method
	/**
	 * Runs a run method on the other side of this front dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void run(final Statement runMethodCommand) {
		internal_runOnCounterpart(			
						
			BackGUIClient.ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI
			+ '('
			+ dialog.getInteractionAttributesOfWidgetsRecursively().to(ia -> '(' + ia.toString() + ')')
			+ ')',
			
			INVOKE_RUN_METHOD_COMMAND
			+ '('
			+ runMethodCommand
			+ ')',
			
			BackGUIClient.RESET_OTHER_SIDE_DIALOG_COMMAND
		);
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
			case BackGUIClient.RESET_DIALOG_COMMAND:
				resetDialog(command.getRefAttributes());
				break;
			case BackGUIClient.RESET_OTHER_SIDE_DIALOG_COMMAND:
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
	private void resetDialog(final Iterable<? extends Specification> attributes) {
		//dialog = new Frame();
		getGUI().reset(attributes);
		getGUI().updateFromConfiguration();
		getGUI().noteMouseMove();
		
		//dialog.setMousePosition(x, y);
		getGUI().noteMouseMove();
		//dialog.updateFromInteraction();
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this front dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final Iterable<StandardSpecification> attributes) {
		internal_runOnCounterpart(BackGUIClient.RESET_DIALOG_COMMAND + "(" + getGUI().getAttributes() + ")");
	}
	
	//method
	/**
	 * Sets the dialog of this front dialog client.
	 * 
	 * @param dialog
	 * @throws NullArgumentException if the given dialog is null.
	 */
	/*
	private void setDialog0(final GUI<?> dialog) {
		
		//Checks if the given dialog is not null.
		Validator.supposeThat(dialog).thatIsInstanceOf(GUI.class).isNotNull();
			
		//Sets the dialog of this front dialog client.
		this.dialog = dialog;
	}
	*/
	
	private GUI<?> getGUI() {
		return dialog;
	}
}
