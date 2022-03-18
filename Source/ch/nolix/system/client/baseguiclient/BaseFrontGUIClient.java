//package declaration
package ch.nolix.system.client.baseguiclient;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.localcomputer.PopupWindowProvider;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.input.IInput;
import ch.nolix.system.client.base.Client;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-16
 * @param <FGC> is the type of a {@link BaseFrontGUIClient}.
 */
public abstract class BaseFrontGUIClient<FGC extends BaseFrontGUIClient<FGC>> extends Client<FGC> {
	
	//attribute
	private final IFrontGUIClientGUIHandler mGUIHandler;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontGUIClient} that will have the given GUIType.
	 * 
	 * @param pGUIType
	 */
	public BaseFrontGUIClient(final BaseFrontGUIClientGUIType pGUIType) {		
		
		//Enumerates the given pGUIType.
		switch (pGUIType) {
			case CANVAS_GUI:
				mGUIHandler = new BaseFrontGUIClientCanvasGUIHandler(this);
				break;
			default:
				throw new InvalidArgumentException(pGUIType);
		}
	}
	
	//method
	/**
	 * @return the type of the GUI of the current {@link BaseFrontGUIClient}.
	 */
	public final BaseFrontGUIClientGUIType getGUIType() {
		return mGUIHandler.getGUIType();
	}
	
	//method
	public void noteInputOnCounterpart(final IInput<?> input) {
		if (isOpen()) {
			internalRunOnCounterpart(
				ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.NOTE_INPUT, input.getSpecification())
			);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node internalGetData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case ObjectProtocol.GUI:
				return getDataFromGUI(request.getNextNode());
			case ObjectProtocol.GUI_TYPE:
				return Node.withHeader(getGUIType().toString());
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
	protected final void internalRun(final ChainedNode command) {
		
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
	 * from the {@link GUI} of the current {@link BaseFrontGUIClient}.
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
	 * @return the {@link GUI} of the current {@link BaseFrontGUIClient}.
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
	
	private void sendOptionalFile() {
		
		final var fileContainer = readFileToBytes();
		
		if (fileContainer.isEmpty()) {
			internalRunOnCounterpart(ChainedNode.withHeader(CommandProtocol.RECEIVE_OPTIONAL_FILE));
		}
		
		final var file = fileContainer.getRefElement();
		final var fileString = new String(file, StandardCharsets.UTF_8);
		internalRunOnCounterpart(
			ChainedNode.withHeaderAndAttribute(
				CommandProtocol.RECEIVE_OPTIONAL_FILE,
				ChainedNode.withHeader(fileString)
			)
		);
	}
}
