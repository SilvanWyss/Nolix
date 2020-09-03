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
import ch.nolix.element.GUI.InvisibleGUI;
import ch.nolix.element.input.InputFactory;
import ch.nolix.system.client.Client;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 250
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
			
		internalRunOnCounterpart(new ChainedNode(CommandProtocol.SHOW_ERROR_MESSAGE, new Node(errorMessage)));
		
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
	protected void internalRun(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ObjectProtocol.GUI:
				runGUICommand(command.getNextNode());
				break;
			case CommandProtocol.NOTE_INPUT:
				
				getRefGUI().noteInput(InputFactory.INSTANCE.createElementFrom(command.getOneAttributeAsNode()));
				
				if (!isClosed()) {
					updateGUIOnCounterpart();
				}
				
				break;
			default:
				
				//Calls method of the base class.
				super.internalRun(command);
		}
	}
	
	//method
	final void configureGUI(final InvisibleGUI pGUI) {
		
		final var viewAreaSize =
		internalGetDataFromCounterpart(
			ChainedNode.withHeaderAndNextNode(ObjectProtocol.GUI, new ChainedNode(ObjectProtocol.VIEW_AREA_SIZE))
		)
		.toIntPair();
		
		pGUI.noteResize(viewAreaSize.getValue1(), viewAreaSize.getValue2());
	}
	
	//method
	final SingleContainer<byte[]> getFileFromCounterpart() {
		
		final var data = internalGetDataFromCounterpart(new ChainedNode(CommandProtocol.GET_FILE));
		
		if (!data.containsOneAttribute()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(data.getRefOneAttribute().toString().getBytes(StandardCharsets.UTF_8));
	}
	
	//method
	final String getTextFromClipboardFromCounterpart() {
		return internalGetDataFromCounterpart(new ChainedNode(CommandProtocol.GET_TEXT_FROM_CLIPBOARD)).getHeader();
	}
	
	//method
	final void saveFileOnCounterpart(final byte[] content) {
		internalRunOnCounterpart(
			new ChainedNode(CommandProtocol.SAVE_FILE, new Node(new String(content, StandardCharsets.UTF_8)))
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
				updateLayerGUIOnCounterpart();
				break;
			case CanvasGUI:
				updateCanvasGUIOnCounterpart();
				break;
		}
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
				internalGetDataFromCounterpart(new ChainedNode(ObjectProtocol.GUI_TYPE)).getHeader()
			);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link Session} of the current {@link BaseBackGUIClient}.
	 */
	private InvisibleGUI getRefGUI() {
		
		@SuppressWarnings("rawtypes")
		final var session = (BaseBackGUIClientSession)internalGetRefCurrentSession();
		
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
	private void runGUICommandOnCounterpart(final ChainedNode pGUICommand) {
		internalRunOnCounterpart(ChainedNode.withHeaderAndNextNode(ObjectProtocol.GUI, pGUICommand));
	}
	
	//method
	private void runGUICommandsOnCounterpart(final IContainer<ChainedNode> pGUICommands) {
		internalRunOnCounterpart(pGUICommands.to(c -> ChainedNode.withHeaderAndNextNode(ObjectProtocol.GUI, c)));
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
			case CommandProtocol.RESET:
				resetGUI(lGUICommand.getAttributesAsNodes());
				break;
			default:
				throw new InvalidArgumentException("GUI command", lGUICommand, "is not valid");
		}
	}
	
	//method
	/**
	 * Update the {@link GUI} of the counterpart of the current {@link BaseBackGUIClient}
	 * for the case when it is a {@link CanvasGUI}.
	 */
	private void updateCanvasGUIOnCounterpart() {
		
		final var canvasGUIUpdateCommands = new LinkedList<ChainedNode>();
		
		canvasGUIUpdateCommands.addAtEnd(new ChainedNode(CommandProtocol.SET_TITLE, new Node(getRefGUI().getTitle())));
		canvasGUIUpdateCommands.addAtEnd(new ChainedNode(CommandProtocol.SET_CURSOR_ICON, getRefGUI().getCursorIcon().getSpecification()));
		canvasGUIUpdateCommands.addAtEnd();
		
		final var paintCommands = getRefGUI().getPaintCommands();
		if (paintCommands.containsAny()) {
			canvasGUIUpdateCommands.addAtEnd(
				ChainedNode.withHeaderAndAttributes(CommandProtocol.SET_PAINT_COMMANDS, paintCommands)
			);
		}
		
		runGUICommandsOnCounterpart(canvasGUIUpdateCommands);
	}
	
	//method
	/**
	 * Update the {@link GUI} of the counterpart of the current {@link BaseBackGUIClient}
	 * for the case when it is a {@link LayerGUI}.
	 */
	private void updateLayerGUIOnCounterpart() {
		runGUICommandOnCounterpart(new ChainedNode(CommandProtocol.RESET, getRefGUI().getAttributes()));
	}
}
