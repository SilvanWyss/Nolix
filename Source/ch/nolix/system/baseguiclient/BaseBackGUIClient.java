//package declaration
package ch.nolix.system.baseguiclient;

//Java import
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.gui.CanvasGUI;
import ch.nolix.element.gui.GUI;
import ch.nolix.element.gui.IWidgetGUI;
import ch.nolix.element.gui.InvisibleGUI;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.input.IInput;
import ch.nolix.element.input.InputFactory;
import ch.nolix.element.input.MouseInput;
import ch.nolix.system.client.Client;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-01
 * @lines 410
 * @param <BBGUIC> The type of a {@link BaseBackGUIClient}.
 */
public abstract class BaseBackGUIClient<BBGUIC extends BaseBackGUIClient<BBGUIC>> extends Client<BBGUIC> {
	
	//constant
	private static final int MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS = 60;
	
	//attributes
	private BaseFrontGUIClientGUIType counterpartGUIType;
	private boolean isNotingMouseInput;
	private boolean isWaitingForFileFromCounterpart;
	
	//optional attribute
	private Node latestFileDataFromCounterpart;
	
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
	 * @throws ArgumentIsNullException if the given error message is null.
	 */
	public final void showErrorMessageOnCounterpart(final String errorMessage) {
		internalRunOnCounterpart(
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SHOW_ERROR_MESSAGE, Node.withHeader(errorMessage))
		);
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
				noteInput(InputFactory.INSTANCE.createElementFrom(command.getOneAttributeAsNode()));				
				break;
			case CommandProtocol.RECEIVE_FILE:
				receiveFileDataFromCounterpart(command.getOneAttributeAsNode());
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
			ChainedNode.withHeaderAndNextNode(
				ObjectProtocol.GUI,
				ChainedNode.withHeader(ObjectProtocol.VIEW_AREA_SIZE)
			)
		)
		.toIntPair();
		
		pGUI.noteResize(viewAreaSize.getValue1(), viewAreaSize.getValue2());
	}
	
	//method
	final SingleContainer<byte[]> getFileFromCounterpart() {
		
		final var fileData = isWebClient() ? getFileDataFromWebCounterpart() : getFileDataFromNonWebCounterpart();
		
		if (!fileData.containsOneAttribute()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(fileData.getRefOneAttribute().toString().getBytes(StandardCharsets.UTF_8));
	}
	
	//method
	final String getTextFromClipboardFromCounterpart() {
		return
		internalGetDataFromCounterpart(ChainedNode.withHeader(CommandProtocol.GET_TEXT_FROM_CLIPBOARD)).getHeader();
	}
	
	//method
	final void saveFileOnCounterpart(final byte[] content) {
		internalRunOnCounterpart(
			ChainedNode.withHeaderAndAttributesFromNodes(
				CommandProtocol.SAVE_FILE,
				Node.withHeader(new String(content, StandardCharsets.UTF_8))
			)
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
			case WIDGET_GUI:
				updateWidgetGUIOnCounterpart();
				break;
			case CANVAS_GUI:
				updateCanvasGUIOnCounterpart();
				break;
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link BaseBackGUIClient}
	 * is already waiting for a file from the counterpart of the current {@link BaseBackGUIClient}.
	 */
	private void assertIsNotWaitingForFileFromCounterpart() {
		if (isWaitingForFileFromCounterpart()) {
			throw new InvalidArgumentException(this, "is already waiting for a file from the counterpart");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link BaseBackGUIClient}
	 * is not waiting for a file from the counterpart of the current {@link BaseBackGUIClient}.
	 */
	private void assertIsWaitingForFileFromCounterpart() {
		if (!isWaitingForFileFromCounterpart()) {
			throw new InvalidArgumentException(this, "is not waiting for a file from counterpart");
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} fetch the GUI type from the counterpart of the current {@link BaseBackGUIClient}
	 * if the current {@link BaseBackGUIClient} does not know it.
	 */
	private void fetchCounterpartGUITypeIfNeeded() {
		if (!knowsCounterpartGUIType()) {
			counterpartGUIType =
			BaseFrontGUIClientGUIType.fromSpecification(
				Node.withAttribute(internalGetDataFromCounterpart(ChainedNode.withHeader(ObjectProtocol.GUI_TYPE)))
			);
		}
	}
	
	//method
	private BaseNode getFileDataFromNonWebCounterpart() {
		return internalGetDataFromCounterpart(ChainedNode.withHeader(CommandProtocol.GET_FILE));
	}
	
	//method
	private Node getFileDataFromWebCounterpart() {
		
		assertIsNotWaitingForFileFromCounterpart();
		
		isWaitingForFileFromCounterpart = true;
		
		internalRunOnCounterpart(ChainedNode.withHeader(CommandProtocol.SEND_FILE));
		
		Sequencer
		.forMaxSeconds(MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS)
		.waitAsLongAs(this::isWaitingForFileFromCounterpart);
		
		isWaitingForFileFromCounterpart = false;
		
		return latestFileDataFromCounterpart;
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link BaseBackGUIClientSession} of the current {@link BaseBackGUIClient}.
	 */
	private IWidgetGUI<?> getRefGUI() {
		
		@SuppressWarnings("rawtypes")
		final var session = (BaseBackGUIClientSession)internalGetRefCurrentSession();
		
		return session.getRefGUI();
	}
	
	//method
	/**
	 * @return true if the current {@link BaseBackGUIClient} is noting a mouse move.
	 */
	private boolean isNotingMouseInput() {
		return isNotingMouseInput;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseBackGUIClient}
	 * is waiting for a file from the counterpart of the current {@link BaseBackGUIClient}.
	 */
	private boolean isWaitingForFileFromCounterpart() {
		return isWaitingForFileFromCounterpart;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseBackGUIClient}
	 * knows the {@link GUI} type of the current {@link BaseBackGUIClient}.
	 */
	private boolean knowsCounterpartGUIType() {
		return (counterpartGUIType != null);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} note the given input.
	 * 
	 * @param input
	 */
	private void noteInput(final IInput<?> input) {
		if (!(isNotingMouseInput() && (input instanceof MouseInput))) {
			noteInputAndLockWhenIsFreeForInput(input);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} note the given input and lock inputs when it is free for inputs.
	 * 
	 * @param input
	 */
	private void noteInputAndLockWhenIsFreeForInput(final IInput<?> input) {
		try {
			
			//TODO: Fix that isNotingMouseInput can be set true.
			/*
			 * ATTENTION: Now, when isNotingMouseInput is set true, a process of a mouse input can remain.
			 * And then, isNotingMouseInput will never be set false and all further inputs will not be processed.
			 * Then the GUI client does not respond to any input anymore.
			 */
			//isNotingMouseInput = true
			
			noteInputWhenIsFreeForInput(input);
		}
		finally {
			isNotingMouseInput = false;
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient} note the given input when is free for inputs.
	 * 
	 * @param input
	 */
	private void noteInputWhenIsFreeForInput(final IInput<?> input) {
		
		getRefGUI().noteInput(input);
		
		if (!isClosed()) {
			updateGUIOnCounterpart();
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackGUIClient}
	 * receive a file from the counterpart of the current {@link BaseBackGUIClient}.
	 * 
	 * @param fileData
	 * @throws ArgumentIsNullException if the given fileData is null.
	 * @throws InvalidArgumentException if the current {@link BaseBackGUIClient}
	 * is not waiting for a file from the counterpart of the current {@link BaseBackGUIClient}.
	 */
	private void receiveFileDataFromCounterpart(final Node fileData) {
		
		Validator.assertThat(fileData).thatIsNamed("file data").isNotNull();
		assertIsWaitingForFileFromCounterpart();
		
		isWaitingForFileFromCounterpart = false;
		latestFileDataFromCounterpart = fileData;
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BaseBackGUIClient} with the given attributes.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <BN extends BaseNode> void resetGUI(final IContainer<BN> attributes) {
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
	
	//TODO: Create several sub methods for this method.
	//method
	/**
	 * Update the {@link GUI} of the counterpart of the current {@link BaseBackGUIClient}
	 * for the case when it is a {@link CanvasGUI}.
	 */
	private void updateCanvasGUIOnCounterpart() {
		
		final var canvasGUIUpdateCommands = new LinkedList<ChainedNode>();
		
		canvasGUIUpdateCommands.addAtEnd(
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SET_TITLE, Node.withHeader(getRefGUI().getTitle()))
		);
		
		canvasGUIUpdateCommands.addAtEnd(
			ChainedNode.withHeaderAndAttributesFromNodes(
				CommandProtocol.SET_CURSOR_ICON,
				getRefGUI().getCursorIcon().getSpecification()
			)
		);
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
	 * for the case when it is a {@link WidgetGUI}.
	 */
	private void updateWidgetGUIOnCounterpart() {
		runGUICommandOnCounterpart(new ChainedNode(CommandProtocol.RESET, getRefGUI().getAttributes()));
	}
}
