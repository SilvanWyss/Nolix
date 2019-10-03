//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 280
 * @param <BGUIC> The type of a {@link BaseBackGUIClient}.
 */
public abstract class BaseBackGUIClient<BGUIC extends BaseBackGUIClient<BGUIC>> extends Client<BGUIC> {
	
	//attribute
	private BaseFrontGUIClientGUIType counterpartGUIType;
	
	//method
	/**
	 * @return the type of the GUI of the counterpart current {@link BaseBackGUIClient}.
	 */
	public final BaseFrontGUIClientGUIType getCounterpartGUIType() {
		
		fetchCounterpartGUITypeIfNeeded();
		
		return counterpartGUIType;
	}
	
	//method
	/**
	 * Shows the given errorMessage on the counterpart of the current {@link BaseBackGUIClient}.
	 * 
	 * @param errorMessage
	 * the current {@link BaseBackGUIClient}
	 * @throws ArgumentIsNullException if the given error message is null.
	 */
	public final BGUIC showErrorMessageOnCounterpart(final String errorMessage) {
		
		internal_runOnCounterpart(
			Protocol.SHOW_ERROR_MESSAGE_HEADER
			+ "("
			+ BaseNode.createReproducingString(errorMessage)
			+ ")"
		);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	protected void internal_run(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextNode());
				break;
			case Protocol.NOTE_KEY_TYPING_HEADER:
				getRefGUI().noteKeyTyping(Key.fromSpecification(command.getRefOneAttribute()));
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_KEY_PRESS_HEADER:
				getRefGUI().noteKeyPress(Key.fromSpecification(command.getRefOneAttribute()));
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_KEY_RELEASE_HEADER:
				getRefGUI().noteKeyRelease(Key.fromSpecification(command.getRefOneAttribute()));
				updateGUIOnCounterpart();
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
			case Protocol.NOTE_MOUSE_WHEEL_CLICK_HEADER:
				getRefGUI().noteMouseWheelClick();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_MOUSE_WHEEL_PRESS_HEADER:
				getRefGUI().noteMouseWheelPress();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_MOUSE_WHEEL_RELEASE_HEADER:
				getRefGUI().noteMouseWheelRelease();
				updateGUIOnCounterpart();
				break;
			case Protocol.NOTE_MOUSE_WHEEL_ROTATION_STEP_HEADER:
				getRefGUI().noteMouseWheelRotationStep(DirectionOfRotation.valueOf(command.getOneAttributeAsString()));
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
	 * of the GUI of the current {@link BaseBackGUIClient}.
	 * 
	 * @param widgetsInteractionAttributes
	 * @throws InvalidArgumentException if the given widgetsInteractionAttributes are not valid.
	 */
	private void addOrChangeGUIWidgetsAttributes(
		final IContainer<IContainer<Node>> widgetsInteractionAttributes
	) {
		getRefGUI().addOrChangeInteractionAttributesOfWidgets(widgetsInteractionAttributes);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} fetch the GUI type from the counterpart of the current {@link BaseBackGUIClient}
	 * if the current {@link BaseBackGUIClient} does not know it.
	 */
	private void fetchCounterpartGUITypeIfNeeded() {
		if (!knowsCounterpartGUIType()) {
			counterpartGUIType
			= BaseFrontGUIClientGUIType.valueOf(
				internal_getDataFromCounterpart(Protocol.GUI_TYPE_HEADER).getHeader()
			);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link Session} of the current {@link BaseBackGUIClient}.
	 */
	private InvisibleLayerGUI getRefGUI() {
		
		final var session = (BackGUIClientSession)internal_getRefCurrentSession();
		
		return session.getRefGUI();
	}

	//method
	/**
	 * @return true if the current {@link BackGUIClientoidoid}
	 * knows the {@link GUI} type of the current {@link BaseBackGUIClient}.
	 */
	private boolean knowsCounterpartGUIType() {
		return (counterpartGUIType != null);
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BaseBackGUIClient} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends BaseNode> void resetGUI(final IContainer<S> attributes) {
		getRefGUI().reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetGUIOnCounterpart(final Iterable<Node> attributes) {
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
	private void runGUICommandOnCounterpart(String GUICommandOnCounterpart) {
		internal_runOnCounterpart(Protocol.GUI_HEADER + "." + GUICommandOnCounterpart);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given GUICommand.
	 * 
	 * @param GUICommand
	 * @throws InvalidArgumentException if the given GUICommand is not valid.
	 */
	private void runGUICommand(final ChainedNode GUICommand) {
		
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
			+ Node.createReproducingString(title)
			+ ")"
		);
	}
	
	//method
	/**
	 * Sets the given paint commands to the counterpart of the current {@link BaseBackGUIClient}.
	 * 
	 * @param paintCommands
	 */
	private void setGUIPaintCommandsOnCounterpart(final IContainer<ChainedNode> paintCommands) {
		//TODO
		if (paintCommands.containsAny()) {
			runGUICommandOnCounterpart(Protocol.SET_PAINT_COMMANDS_HEADER + "(" + paintCommands.to(pc -> Node.createReproducingString(pc.toString())) + ")");
		}
	}
	
	//method
	/**
	 * Resets the GUI on the counterpart of the current {@link BaseBackGUIClient}. 
	 */
	void updateGUIOnCounterpart() {
		
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
