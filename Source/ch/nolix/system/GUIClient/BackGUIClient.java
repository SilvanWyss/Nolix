//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.GUI;
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
	static final String RESET_GUI_HEADER = "ResetGUI";
	static final String ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI_HEADER = "AddOrChangeInteractionAttributesOfWidgetsOfGUI";
	static final String RESET_COUNTERPART_GUI_HEADER = "ResetCounterpartGUI";

	public BackGUIClient(final DuplexController duplexController) {
			
		//Calls constructor of the base class.
		internal_setDuplexController(duplexController);
	}
	
	//TODO
	public GUI<?> getRefGUI() {
		
		final var session = (BackGUISession<?>)internal_getRefCurrentSession();
		
		return session.getRefGUI();
	}
	
	//method
	/**
	 * Invokes a run method of this dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void runLocally(final String runMethodCommand) {
		
		final BackGUISession<BackGUIClient> session = (BackGUISession<BackGUIClient>)internal_getRefCurrentSession();
		
		internal_invokeSessionUserRunMethod(new StandardSpecification(runMethodCommand));
		internal_runOnCounterpart(RESET_GUI_HEADER + "(" + session.getRefGUI().getAttributes() + ")");
	}
	
	//method
	/**
	 * Resets this back GUI client.
	 * 
	 * @return this back GUI client.
	 */
	public BackGUIClient reset() {
		
		final var session = (BackGUISession<?>)internal_getRefCurrentSession();
		
		session.getRefGUI().reset();
		internal_runOnCounterpart(RESET_GUI_HEADER);
		
		return this;
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this dialog client.
	 */
	protected void internal_finishSessionInitialization() {
		final var session = (BackGUISession<?>)internal_getRefCurrentSession();
		internal_runOnCounterpart(RESET_GUI_HEADER + "(" + session.getRefGUI().getAttributes() + ")");
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
			case ADD_OR_CHANGE_INTERACTION_ATTRIBUTES_OF_WIDGETS_OF_GUI_HEADER:
				addOrChangeInteractionAttributesToWidgetsOfGUI(						
					command.getRefAttributes().to(a -> a.getRefAttributes())
				);
				break;
			case RESET_GUI_HEADER:
				resetDialog(command.getRefAttributes());
				break;
			case RESET_COUNTERPART_GUI_HEADER:
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
		getRefGUI().addOrChangeInteractionAttributesOfWidgetsRecursively(interactionAttributesOfWidgetsOfGUI);
	}
	
	//method
	/**
	 * Resets the dialog of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetDialog(final Iterable<? extends Specification> attributes) {
		
		final BackGUISession<?> session = (BackGUISession<?>)internal_getRefCurrentSession();
		
		session.getRefGUI().reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final Iterable<StandardSpecification> attributes) {
		
		final var session = (BackGUISession<?>)internal_getRefCurrentSession();
		
		internal_runOnCounterpart(BackGUIClient.RESET_GUI_HEADER + "(" + session.getRefGUI().getAttributes() + ")");
	}
}
