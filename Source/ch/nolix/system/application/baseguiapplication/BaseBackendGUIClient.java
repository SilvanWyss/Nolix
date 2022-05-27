//package declaration
package ch.nolix.system.application.baseguiapplication;

//Java imports
import java.nio.charset.StandardCharsets;
import java.util.Base64;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.targetuniversalapi.IApplicationTarget;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.application.main.BackendClient;
import ch.nolix.system.gui.base.GUI;
import ch.nolix.system.gui.base.IWidgetGUI;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.input.InputFactory;
import ch.nolix.system.gui.input.MouseInput;
import ch.nolix.systemapi.guiapi.inputapi.IInput;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-01
 * @param <BBGUIC> is the type of a {@link BaseBackendGUIClient}.
 * @param <AC> is the type of the context of the parent {@link Application} of a {@link BaseBackendGUIClient}.
 */
public abstract class BaseBackendGUIClient<
	BBGUIC extends BaseBackendGUIClient<BBGUIC, AC>,
	AC
> extends BackendClient<BBGUIC, AC> {
	
	//constant
	private static final int MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS = 60;
	
	//attributes
	private BaseBackendGUIClientCounterpartUpdater counterpartUpdater = new BaseBackendGUIClientCounterpartUpdater(this);
	private boolean isNotingMouseInput;
	private boolean isWaitingForFileFromCounterpart;
	
	//optional attribute
	private SingleContainer<String> latestOptionalFileFromCounterpart;
		
	//method
	/**
	 * Shows the given errorMessage on the counterpart of the current {@link BaseBackendGUIClient}.
	 * 
	 * @param errorMessage
	 * @throws ArgumentIsNullException if the given error message is null.
	 */
	public final void showErrorMessageOnCounterpart(final String errorMessage) {
		runOnCounterpart(
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SHOW_ERROR_MESSAGE, Node.withHeader(errorMessage))
		);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	protected void runHere(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ObjectProtocol.GUI:
				runGUICommand(command.getNextNode());
				break;
			case CommandProtocol.NOTE_INPUT:
				noteInput(InputFactory.INSTANCE.createElementFrom(command.getOneAttributeAsNode()));				
				break;
			case CommandProtocol.RECEIVE_OPTIONAL_FILE:
				receiveOptionalFileFromCounterpart(command);
				break;
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	final void configureGUI(final InvisibleGUI pGUI) {
		
		final var viewAreaSize =
		getDataFromCounterpart(
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
		
		final var optionalFile = getOptionalFileFromCounterpart();
		
		if (optionalFile.isEmpty()) {
			return new SingleContainer<>();
		}
		
		//Important: The received fileString is a Base 64 encoded string.
		final var fileString = optionalFile.getRefElement();
		final var bytes = Base64.getDecoder().decode(fileString.substring(fileString.indexOf(',') + 1));
		return new SingleContainer<>(bytes);
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link BaseBackendGUIClientSession} of the current {@link BaseBackendGUIClient}.
	 */
	final IWidgetGUI<?> getRefGUI() {
		
		@SuppressWarnings("rawtypes")
		final var session = (BaseBackendGUIClientSession)getRefCurrentSession();
		
		return session.getRefGUI();
	}
	
	//method
	final String getTextFromClipboardFromCounterpart() {
		return getDataFromCounterpart(ChainedNode.withHeader(CommandProtocol.GET_TEXT_FROM_CLIPBOARD)).getHeader();
	}
	
	//method
	final void internalOpenNewTabOnCounterpartWithURL(final String pURL) {
		runOnCounterpart(
			ChainedNode.withHeaderAndAttribute(
				CommandProtocol.OPEN_NEW_TAB,
				ChainedNode.withHeaderAndAttribute(
					ObjectProtocol.URL,
					ChainedNode.withHeader(pURL)
				)
			)
		);
	}
	
	//method
	final void internalRedirectTo(final IApplicationTarget applicationTarget) {
		runOnCounterpart(
			ChainedNode.withHeaderAndAttribute(
				CommandProtocol.REDIRECT,
				ChainedNode.withHeader(applicationTarget.toURL())
			)
		);
	}
	
	//method
	final void internalRunOnCounterpart(final ChainedNode command) {
		runOnCounterpart(command);
	}
	
	//method
	final void internalRunOnCounterpart(final Iterable<ChainedNode> commands) {
		runOnCounterpart(commands);
	}
	
	//method
	final void saveFileOnCounterpart(final byte[] content) {
		runOnCounterpart(
			ChainedNode.withHeaderAndAttributesFromNodes(
				CommandProtocol.SAVE_FILE,
				Node.withHeader(new String(content, StandardCharsets.UTF_8))
			)
		);
	}
	
	//method
	/**
	 * Updates the counterpart of the current {@link BaseBackendGUIClient}.
	 */
	final void updateCounterpart() {	
		
		/*
		 * TODO: Fix this.
		 * A refresh resp. a recalculation of the GUI is required twice instead of 1 time.
		 * If there is done 1 refresh only, the GUI will show up empty on the frontend client as long as
		 * the user does not make a device input.
		 */
		getRefGUI().refresh();
		getRefGUI().refresh();
		
		counterpartUpdater.updateCounterpart();
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link BaseBackendGUIClient}
	 * is already waiting for a file from the counterpart of the current {@link BaseBackendGUIClient}.
	 */
	private void assertIsNotWaitingForFileFromCounterpart() {
		if (isWaitingForFileFromCounterpart()) {
			throw new InvalidArgumentException(this, "is already waiting for a file from the counterpart");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link BaseBackendGUIClient}
	 * is not waiting for a file from the counterpart of the current {@link BaseBackendGUIClient}.
	 */
	private void assertIsWaitingForFileFromCounterpart() {
		if (!isWaitingForFileFromCounterpart()) {
			throw new InvalidArgumentException(this, "is not waiting for a file from counterpart");
		}
	}
	
	//method
	private SingleContainer<String> getOptionalFileFromCounterpart() {
				
		assertIsNotWaitingForFileFromCounterpart();
		
		isWaitingForFileFromCounterpart = true;
		
		runOnCounterpart(ChainedNode.withHeader(CommandProtocol.SEND_OPTIONAL_FILE));
		
		Sequencer
		.forMaxSeconds(MAX_WAITING_TIME_FOR_FILE_FROM_COUNTERPART_IN_SECONDS)
		.waitAsLongAs(this::isWaitingForFileFromCounterpart);
		
		isWaitingForFileFromCounterpart = false;
		
		return latestOptionalFileFromCounterpart;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseBackendGUIClient} is noting a mouse move.
	 */
	private boolean isNotingMouseInput() {
		return isNotingMouseInput;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseBackendGUIClient}
	 * is waiting for a file from the counterpart of the current {@link BaseBackendGUIClient}.
	 */
	private boolean isWaitingForFileFromCounterpart() {
		return isWaitingForFileFromCounterpart;
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} note the given input.
	 * 
	 * @param input
	 */
	private void noteAnyInput(final IInput<?> input) {
		
		getRefGUI().noteInput(input);
		
		//There can happen that the action before made that the current BaseBackGUIClient was closed.
		if (!isClosed()) {
			updateCounterpart();
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} note the given input.
	 * 
	 * @param input
	 */
	private void noteInput(final IInput<?> input) {
		if (input instanceof MouseInput) {
			noteMouseInput((MouseInput)input);
		} else {
			noteAnyInput(input);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} the given mouseInput.
	 * 
	 * @param mouseInput
	 */
	private void noteMouseInput(final MouseInput mouseInput) {
		if (!isNotingMouseInput()) {
			noteMouseInputWhenIsNotNotingMouseInput(mouseInput);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} the given mouseInput for the case
	 * that the current {@link BaseBackendGUIClient} is not already noting a {@link MouseInput}.
	 * 
	 * @param mouseInput
	 */
	private void noteMouseInputWhenIsNotNotingMouseInput(final MouseInput mouseInput) {
		try {
			
			isNotingMouseInput = true;
			
			noteAnyInput(mouseInput);
		} finally {
			isNotingMouseInput = false;
		}
	}
	
	//method
	private void receiveOptionalFileFromCounterpart(final ChainedNode receiveOptionalFileCommand) {
		switch (receiveOptionalFileCommand.getAttributeCount()) {
			case 0:
				receiveOptionalFileFromCounterpart(new SingleContainer<>());
				break;
			case 1:
				receiveOptionalFileFromCounterpart(
					new SingleContainer<>(receiveOptionalFileCommand.getOneAttribute().getHeader())
				);
				break;
			default:
				throw
				new InvalidArgumentException(
					"receive optional file commoand",
					receiveOptionalFileCommand,
					"is not valid"
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} receive
	 * a file optionally from the counterpart of the current {@link BaseBackendGUIClient}.
	 * 
	 * @param optionalFile
	 * @throws ArgumentIsNullException if the given optionalFile is null.
	 * @throws InvalidArgumentException if the current {@link BaseBackendGUIClient}
	 * is not waiting for a file from the counterpart of the current {@link BaseBackendGUIClient}.
	 */
	private void receiveOptionalFileFromCounterpart(final SingleContainer<String> optionalFile) {
		
		GlobalValidator.assertThat(optionalFile).thatIsNamed("optional file").isNotNull();
		assertIsWaitingForFileFromCounterpart();
		
		isWaitingForFileFromCounterpart = false;
		latestOptionalFileFromCounterpart = optionalFile;
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BaseBackendGUIClient} with the given attributes.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <BN extends BaseNode> void resetGUI(final IContainer<BN> attributes) {
		getRefGUI().resetFrom(attributes);
	}
	
	//method
	/**
	 * Lets the current {@link BaseBackendGUIClient} run the given GUICommand.
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
}
