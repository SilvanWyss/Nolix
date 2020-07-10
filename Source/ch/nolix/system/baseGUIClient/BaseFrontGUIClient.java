//package declaration
package ch.nolix.system.baseGUIClient;

//Java import
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.localComputer.PopupWindowProvider;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.input.IInput;
import ch.nolix.element.input.ResizeInput;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Client;

//class
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 180
 * @param <FGC> The type of a {@link BaseFrontGUIClient}.
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
			case LayerGUI:
				mGUIHandler = new BaseFrontGUIClientLayerGUIHandler(this);
				break;
			case CanvasGUI:
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
		internalRunOnCounterpart(new ChainedNode(CommandProtocol.NOTE_INPUT, input.getSpecification()));;
	}
	
	//method
	public final void noteResizeOnCounterpart() {
		noteInputOnCounterpart(new ResizeInput(getRefGUI().getViewAreaWidth(), getRefGUI().getViewAreaHeight()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node internalGetData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case ObjectProtocol.GUI_TYPE:
				return new Node(getGUIType());
			case CommandProtocol.GET_TEXT_FROM_CLIPBOARD:
				return new Node(getRefGUI().fromFrontEnd().getTextFromClipboard());
			case CommandProtocol.GET_FILE:
				
				final var data = readFileToBytes();
				
				if (data.isEmpty()) {
					return new Node();
				}
				
				return Node.withAttribute(new Node(new String(data.getRefElement(), StandardCharsets.UTF_8)));
			default:
				
				//Calls method of the base class.
				return super.internalGetData(request);
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
			case CommandProtocol.SHOW_ERROR_MESSAGE:
				PopupWindowProvider.showErrorWindow(command.getOneAttributeAsString());
				break;
			default:
				
				//Calls method of the base class.
				super.internalRun(command);
		}
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link FrontGUIClient}.
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
}
