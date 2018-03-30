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
public final class GUIFrontClient extends Client<GUIFrontClient> {
	
	//attribute
	private GUI<?> dialog;
	
	public GUIFrontClient(Application<GUIBackClient> target) {
		dialog = new Frame();
		dialog.setController(new GUIFrontClientController(this));
		internal_connect(target);
	}
	
	//method
	/**
	 * Resets this GUI front client.
	 */
	public void reset() {}
	
	//method
	/**
	 * Runs a run method on the other side of this front dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void run(final Statement runMethodCommand) {
		
		internal_runOnCounterpart(
				
			GUIBackClient.ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI
			+ '('
			+ dialog.getInteractionAttributesOfWidgets().to(ia -> '(' + ia.toString() + ')')
			+ ')',
			
			INVOKE_RUN_METHOD_COMMAND
			+ '('
			+ runMethodCommand
			+ ')',
			
			GUIBackClient.RESET_OTHER_SIDE_DIALOG_COMMAND
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
			case GUIBackClient.RESET_DIALOG_COMMAND:
				resetDialog(command.getRefAttributes());
				break;
			case GUIBackClient.RESET_OTHER_SIDE_DIALOG_COMMAND:
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
		internal_runOnCounterpart(GUIBackClient.RESET_DIALOG_COMMAND + "(" + getGUI().getAttributes() + ")");
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
