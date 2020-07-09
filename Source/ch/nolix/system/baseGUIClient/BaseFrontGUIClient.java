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
		
		//Sets the pre-close action of the current BaseFrontGUIClient.
		internalSetPreCloseAction(this::preClose);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {
						
		//Calls method of the base class.
		super.close();
		
		preClose();
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
		internalRunOnCounterpart(new ChainedNode(Protocol.NOTE_INPUT, input.getSpecification()));;
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
			case Protocol.GUI_TYPE_HEADER:
				return new Node(getGUIType());
			case Protocol.GET_TEXT_FROM_CLIPBOARD:
				return new Node(getRefGUI().fromFrontEnd().getTextFromClipboard());
			case Protocol.GET_FILE_HEADER:
				
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
			case Protocol.GUI_HEADER:
				mGUIHandler.runGUICommand(command.getNextNode());
				break;
			case Protocol.SAVE_FILE_HEADER:
				saveFile(command.getOneAttributeAsString().getBytes(StandardCharsets.UTF_8));
				break;
			case Protocol.SHOW_ERROR_MESSAGE_HEADER:
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
	private void preClose() {
		getRefGUI().close();
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
