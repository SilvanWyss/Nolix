//package declaration
package ch.nolix.system.application.baseguiclient;

//Java imports
import java.nio.charset.StandardCharsets;
import java.util.Base64;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.input.IInput;
import ch.nolix.system.application.main.FrontendClient;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-16
 * @param <FGC> is the type of a {@link BaseFrontendGUIClient}.
 */
public abstract class BaseFrontendGUIClient<FGC extends BaseFrontendGUIClient<FGC>> extends FrontendClient<FGC> {
	
	//attribute
	private final BaseFrontendGUIClientGUIHandler mGUIHandler = new BaseFrontendGUIClientGUIHandler(this);
	
	//method
	public void noteInputOnCounterpart(final IInput<?> input) {
		if (isOpen() && isConnected()) {
			runOnCounterpart(
				ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.NOTE_INPUT, input.getSpecification())
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node getDataFromHere(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case ObjectProtocol.GUI:
				return getDataFromGUI(request.getNextNode());
			case CommandProtocol.GET_TEXT_FROM_CLIPBOARD:
				return Node.withHeader(getRefGUI().fromFrontEnd().getTextFromClipboard());
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.REQUEST, request, "is not valid");
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void runHere(final ChainedNode command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ObjectProtocol.GUI:
				mGUIHandler.runGUICommand(command.getNextNode());
				break;
			case CommandProtocol.SAVE_FILE:
				saveFile(command.getOneAttributeAsString().getBytes(StandardCharsets.UTF_8));
				break;
			case CommandProtocol.SEND_OPTIONAL_FILE:
				sendOptionalFile();
				break;
			case CommandProtocol.SHOW_ERROR_MESSAGE:
				PopupWindowProvider.showErrorWindow(command.getOneAttributeAsString());
				break;
			default:
				throw new InvalidArgumentException(LowerCaseCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	/**
	 * @param pGUIDataRequest
	 * @return the data the given pGUIDataRequest requests
	 * from the {@link GUI} of the current {@link BaseFrontendGUIClient}.
	 * @throws InvalidArgumentException if the given pGUIDataRequest is not valid.
	 */
	private Node getDataFromGUI(final ChainedNode pGUIDataRequest) {
		
		//Enumerates the header of the given pGUIDataRequest.
		switch (pGUIDataRequest.getHeader()) {
			case ObjectProtocol.VIEW_AREA_SIZE:
				return Node.fromIntPair(getRefGUI().getViewAreaSize());
			default:
				throw new InvalidArgumentException("GUI data request", pGUIDataRequest, "is not valid");
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link BaseFrontendGUIClient}.
	 */
	private GUI<?> getRefGUI() {
		return mGUIHandler.getRefGUI();
	}
		
	//method
	private SingleContainer<byte[]> readFileToBytes() {
		return getRefGUI().fromFrontEnd().readFileToBytes();
	}
	
	//method
	private void saveFile(final byte[] content) {
		getRefGUI().onFrontEnd().saveFile(content);
	}
	
	//method
	private void sendOptionalFile() {
		
		final var fileContainer = readFileToBytes();
		
		if (fileContainer.isEmpty()) {
			runOnCounterpart(ChainedNode.withHeader(CommandProtocol.RECEIVE_OPTIONAL_FILE));
		}
		
		final var fileBytes = fileContainer.getRefElement();
		final var fileString = Base64.getEncoder().encodeToString(fileBytes);
		runOnCounterpart(
			ChainedNode.withHeaderAndAttribute(
				CommandProtocol.RECEIVE_OPTIONAL_FILE,
				ChainedNode.withHeader(fileString)
			)
		);
	}
}
