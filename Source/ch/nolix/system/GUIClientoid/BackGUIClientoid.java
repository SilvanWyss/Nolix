//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 390
 * @param <BGUIC> The type of a {@link BackGUIClientoid}.
 */
public abstract class BackGUIClientoid<BGUIC extends BackGUIClientoid<BGUIC>> extends Client<BGUIC> {
	
	//attribute
	private FrontGUIClientoidGUIType counterpartGUIType;
	
	//method
	/**
	 * @return the type of the GUI of the counterpart current {@link BackGUIClientoid}.
	 */
	public final FrontGUIClientoidGUIType getCounterpartGUIType() {
		
		fetchCounterpartGUITypeIfNeeded();
		
		return counterpartGUIType;
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given command locally.
	 * 
	 * @param command
	 * @return the current {@link BackGUIClientoid}.
	 */
	public  BGUIC runLocally(final String command) {
		
		internal_invokeSessionUserRunMethod(DocumentNode.createFromString(command));
		updateGUIOnCounterpart();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Shows the given errorMessage on the counterpart of the current {@link BackGUIClientoid}.
	 * 
	 * @param errorMessage
	 * the current {@link BackGUIClientoid}
	 * @throws NullArgumentException if the given error message is null.
	 */
	public final BGUIC showErrorMessageOnCounterpart(final String errorMessage) {
		
		internal_runOnCounterpart(
			Protocol.SHOW_ERROR_MESSAGE_HEADER
			+ "("
			+ DocumentNodeoid.createReproducingString(errorMessage)
			+ ")"
		);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Finishes the initialization of the session of the current {@link BackGUIClientoid}.
	 */
	@Override
	protected final void internal_finishSessionInitialization() {
		updateGUIOnCounterpart();
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.COUNTERPART_HEADER:
				runCommandFromCounterpart(command.getRefNextStatement());
				break;
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextStatement());
				break;
			case Protocol.NOTE_LEFT_MOUSE_BUTTON_CLICK_HEADER:
				getRefGUI().noteLeftMouseButtonClick();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER:
				getRefGUI().noteLeftMouseButtonPress();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER:
				getRefGUI().noteLeftMouseButtonRelease();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_RIGHT_MOUSE_BUTTON_CLICK_HEADER:
				getRefGUI().noteRightMouseButtonClick();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER:
				getRefGUI().noteRightMouseButtonPress();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER:
				getRefGUI().noteRightMouseButtonRelease();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_MOUSE_MOVE_HEADER:
				getRefGUI().noteMouseMove(command.getRefAttributeAt(1).toInt(), command.getRefAttributeAt(2).toInt());
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_RESIZE_HEADER:
				getRefGUI().noteResize(command.getRefAttributeAt(1).toInt(), command.getRefAttributeAt(2).toInt());
				updateGUIOnCounterpart();
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Adds or changes the given widgetsInteractionAttributes to the {@link Widget}s
	 * of the GUI of the current {@link BackGUIClientoid}.
	 * 
	 * @param widgetsInteractionAttributes
	 * @throws InvalidArgumentException if the given widgetsInteractionAttributes are not valid.
	 */
	private void addOrChangeGUIWidgetsAttributes(
		final IContainer<IContainer<DocumentNode>> widgetsInteractionAttributes
	) {
		getRefGUI().addOrChangeInteractionAttributesOfWidgets(widgetsInteractionAttributes);
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} fetch the GUI type from the counterpart of the current {@link BackGUIClientoid}
	 * if the current {@link BackGUIClientoid} does not know it.
	 */
	private void fetchCounterpartGUITypeIfNeeded() {
		if (!knowsCounterpartGUIType()) {
			counterpartGUIType
			= FrontGUIClientoidGUIType.valueOf(
				internal_getDataFromCounterpart(Protocol.GUI_TYPE_HEADER).getHeader()
			);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link Session} of the current {@link BackGUIClientoid}.
	 */
	private InvisibleLayerGUI getRefGUI() {
		
		final var session = (BackGUIClientSession)internal_getRefCurrentSession();
		
		return session.getRefGUI();
	}

	//method
	/**
	 * @return true if the current {@link BackGUIClientoidoid}
	 * knows the {@link GUI} type of the current {@link BackGUIClientoid}.
	 */
	private boolean knowsCounterpartGUIType() {
		return (counterpartGUIType != null);
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BackGUIClientoid} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends DocumentNodeoid> void resetGUI(final IContainer<S> attributes) {
		getRefGUI().reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetGUIOnCounterpart(final Iterable<DocumentNode> attributes) {
		internal_runOnCounterpart(
			Protocol.GUI_HEADER
			+ "."
			+ Protocol.RESET_HEADER
			+ "("
			+ attributes
			+ ")"
		);
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given commandFromCounterpart.
	 * 
	 * @param commandFromCounterpart
	 * @throws InvalidArgumentException if the given commandFromCounterpart is not valid.
	 */
	private void runCommandFromCounterpart(final Statement commandFromCounterpart) {
		
		//Enumerates the header of the given counterpartCommand.
		switch (commandFromCounterpart.getHeader()) {
			case Protocol.GUI_HEADER:
				runGUICommandFromCounterpart(commandFromCounterpart.getRefNextStatement());
				resetGUIOnCounterpart(commandFromCounterpart.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("counterpart command", commandFromCounterpart, "is not valid");
		}
	}
	
	//method
	private void runGUICommandOnCounterpart(String GUICommandOnCounterpart) {
		internal_runOnCounterpart(Protocol.GUI_HEADER + "." + GUICommandOnCounterpart);
	}

	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given GUICommandFromCounterpart.
	 * 
	 * @param GUICommandFromCounterpart
	 * @throws InvalidArgumentException if the given GUICommandFromCounterpart is not valid.
	 */
	private void runGUICommandFromCounterpart(final Statement GUICommandFromCounterpart) {
		switch (GUICommandFromCounterpart.getHeader()) {
			case Protocol.RESET_HEADER:
				getRefGUI().reset(GUICommandFromCounterpart.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("counterpart GUI command", GUICommandFromCounterpart, "is not valid");
		}	
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given GUICommand.
	 * 
	 * @param GUICommand
	 * @throws InvalidArgumentException if the given GUICommand is not valid.
	 */
	private void runGUICommand(final Statement GUICommand) {
		
		//Enumerates the header of the given GUICommand.
		switch (GUICommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			case Protocol.ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER:
				addOrChangeGUIWidgetsAttributes(						
					GUICommand.getRefAttributes().to(a -> a.getRefAttributes())
				);
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}
	
	//method
	private void setGUITitleOnCounterpart(final String title) {
		runGUICommandOnCounterpart(
			Protocol.SET_TITLE_HEADER
			+ "("
			+ DocumentNode.createReproducingString(title)
			+ ")"
		);
	}
	
	//method
	/**
	 * Sets the given paint commands to the counterpart of the current {@link BackGUIClientoid}.
	 * 
	 * @param paintCommands
	 */
	private void setGUIPaintCommandsOnCounterpart(final IContainer<Statement> paintCommands) {
		//TODO
		if (paintCommands.containsAny()) {
			runGUICommandOnCounterpart(Protocol.SET_PAINT_COMMANDS_HEADER + "(" + paintCommands.to(pc -> DocumentNode.createReproducingString(pc.toString())) + ")");
		}
	}
	
	//method
	/**
	 * Resets the GUI on the counterpart of the current {@link BackGUIClientoid}. 
	 */
	private void updateGUIOnCounterpart() {
		
		//Enumerates the front end type of the current back GUI client.
		switch (getCounterpartGUIType()) {
			case LayerGUI:
				resetGUIOnCounterpart(getRefGUI().getAttributes());
				break;
			case CanvasGUI:
				setGUITitleOnCounterpart(getRefGUI().getTitle());
				setGUIPaintCommandsOnCounterpart(getRefGUI().getPaintCommands());
				break;
		}
	}
}
