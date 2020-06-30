//package declaration
package ch.nolix.system.baseGUIClient;

//Java import
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.input.InputFactory;
import ch.nolix.system.client.Client;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 240
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
			
		internal_runOnCounterpart(new ChainedNode(Protocol.SHOW_ERROR_MESSAGE_HEADER, new Node(errorMessage)));
		
		return asConcrete();
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
				runGUICommand(command.getNextNode());
				break;
			case Protocol.NOTE_INPUT:
				
				getRefGUI().noteInput(InputFactory.INSTANCE.createElementFrom(command.getOneAttributeAsNode()));
				
				if (!isClosed()) {
					updateGUIOnCounterpart();
				}
				
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	SingleContainer<byte[]> getFileFromCounterpart() {
		
		final var data = internal_getDataFromCounterpart(new ChainedNode(Protocol.GET_FILE_HEADER));
		
		if (!data.containsOneAttribute()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(data.getRefOneAttribute().toString().getBytes(StandardCharsets.UTF_8));
	}
	
	//method
	void saveFileOnCounterpart(final byte[] content) {
		internal_runOnCounterpart(
			new ChainedNode(Protocol.SAVE_FILE_HEADER, new Node(new String(content, StandardCharsets.UTF_8)))
		);
	}
	
	//method
	/**
	 * Resets the GUI on the counterpart of the current {@link BaseBackGUIClient}.
	 * Refreshes the GUI of the current {@link BaseBackGUIClient} before.
	 */
	final void updateGUIOnCounterpart() {
		
		getRefGUI().refresh();
		
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

	//method
	/**
	 * Adds or changes the given attributes to the {@link Widget}s
	 * of the GUI of the current {@link BaseBackGUIClient}.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if the given attributes are not valid.
	 */
	private void addOrChangeGUIWidgetsAttributes(
		final IContainer<IContainer<Node>> attributes
	) {
		getRefGUI().addOrChangeAttributesOfWidgets(attributes);
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
				internal_getDataFromCounterpart(new ChainedNode(Protocol.GUI_TYPE_HEADER)).getHeader()
			);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link Session} of the current {@link BaseBackGUIClient}.
	 */
	private InvisibleLayerGUI getRefGUI() {
		
		@SuppressWarnings("rawtypes")
		final var session = (BaseBackGUIClientSession)internal_getRefCurrentSession();
		
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
			new ChainedNode(Protocol.GUI_HEADER, new LinkedList<>(), new ChainedNode(Protocol.RESET_HEADER, attributes))
		);
	}
	
	//method
	private void runGUICommandOnCounterpart(final ChainedNode pGUICommandOnCounterpart) {
		internal_runOnCounterpart(new ChainedNode(Protocol.GUI_HEADER, new LinkedList<>(), pGUICommandOnCounterpart));
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} run the given GUICommand.
	 * 
	 * @param lGUICommand
	 * @throws InvalidArgumentException if the given GUICommand is not valid.
	 */
	private void runGUICommand(final ChainedNode lGUICommand) {
		
		//Enumerates the header of the given GUICommand.
		switch (lGUICommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUI(lGUICommand.getAttributesAsNodes());
				break;
			case Protocol.ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER:
				addOrChangeGUIWidgetsAttributes(						
					lGUICommand.getAttributes().to(ChainedNode::getAttributesAsNodes)
				);
				break;
			default:
				throw new InvalidArgumentException("GUI command", lGUICommand, "is not valid");
		}
	}
	
	//method
	private void setGUITitleOnCounterpart(final String title) {
		runGUICommandOnCounterpart(
			new ChainedNode(Protocol.SET_TITLE_HEADER, new Node(title))
		);
	}
	
	//method
	/**
	 * Sets the given paint commands to the counterpart of the current {@link BaseBackGUIClient}.
	 * 
	 * @param paintCommands
	 */
	private void setGUIPaintCommandsOnCounterpart(final IContainer<ChainedNode> paintCommands) {
		if (paintCommands.containsAny()) {			
			runGUICommandOnCounterpart(
				
				//TODO: Add a suitable construction mechanic to ChainedNode. 
				ChainedNode.fromString(Protocol.SET_PAINT_COMMANDS_HEADER + "(" + paintCommands + ")")
			);
		}
	}
}
