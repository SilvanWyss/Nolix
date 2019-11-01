//package declaration
package ch.nolix.system.baseGUIClient;

import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.localComputer.PopupWindowProvider;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 270
 * @param <FGC> The type of a {@link BaseFrontGUIClient}.
 */
public abstract class BaseFrontGUIClient<FGC extends BaseFrontGUIClient<FGC>> extends Client<FGC> {
	
	//attribute
	private final IFrontGUIClientGUIHandler mGUIHandler;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontGUIClient} that will have the given GUIType.
	 * 
	 * @param GUIType
	 */
	public BaseFrontGUIClient(final BaseFrontGUIClientGUIType GUIType) {		
		
		//Enumerates the given GUIType.
		switch (GUIType) {
			case LayerGUI:
				mGUIHandler = new BaseFrontGUIClientLayerGUIHandler(this);
				break;
			case CanvasGUI:
				mGUIHandler = new BaseFrontGUIClientCanvasGUIHandler(this);
				break;
			default:
				throw new InvalidArgumentException(GUIType);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void close() {
		
		//Calls method of the base class.
		super.close();
		
		mGUIHandler.noteClose();
	}
	
	//method
	/**
	 * @return the type of the GUI of the current {@link BaseFrontGUIClient}.
	 */
	public final BaseFrontGUIClientGUIType getGUIType() {
		return mGUIHandler.getGUIType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void noteKeyPressOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_PRESS_HEADER, key.getSpecification().toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteKeyReleaseOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_RELEASE_HEADER, key.getSpecification().toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteKeyTypingOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_TYPING_HEADER, key.getSpecification().toString());		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteLeftMouseButtonClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_CLICK_HEADER);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteLeftMouseButtonPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteLeftMouseButtonReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteMouseMoveOnCounterpart(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteCommandOnCounterpart(
			Protocol.NOTE_MOUSE_MOVE_HEADER,
			String.valueOf(cursorXPositionOnViewArea),
			String.valueOf(cursorYPositionOnViewArea)
		);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteMouseWheelClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_CLICK_HEADER);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteMouseWheelPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_PRESS_HEADER);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteMouseWheelReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_RELEASE_HEADER);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteMouseWheelRotationStepOnCounterpart(final DirectionOfRotation directionOfRotation) {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_ROTATION_STEP_HEADER, directionOfRotation.toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteRightMouseButtonClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_CLICK_HEADER);	
	}
	
	//method
	public final void noteRightMouseButtonPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteRightMouseButtonReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER);
	}
	
	//method
	public final void noteResizeOnCounterpart() {
		noteResizeOnCounterpart(getRefGUI().getViewAreaWidth(), getRefGUI().getViewAreaHeight());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void noteResizeOnCounterpart(final int viewAreaWidth, final int viewAreaHeight) {
		noteCommandOnCounterpart(
			Protocol.NOTE_RESIZE_HEADER,
			String.valueOf(viewAreaWidth),
			String.valueOf(viewAreaHeight)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Node internal_getData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.GUI_TYPE_HEADER:
				return new Node(getGUIType());
			case Protocol.GET_FILE_HEADER:
				return new Node(new String(readFileToBytes(), StandardCharsets.UTF_8));
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void internal_run(final ChainedNode command) {
		
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
				super.internal_run(command);
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
	/**
	 * Lets the counterpart of the current {@link FrontGUIClient}
	 * note a command with the given commandHeader and commandAttributes.
	 * 
	 * @param commandHeader
	 * @param commandAttributes
	 */
	private void noteCommandOnCounterpart(final String commandHeader, final String... commandAttributes) {
		
		/*
		 * This is important because a GUI could fire an event before the current FrontGUIClientoid is connected.
		 *
		 * The GUI of a FrontGUIClientoid must be ready before the FrontGUIClientoid is connected,
		 * because everything of a a FrontGUIClientoid must be ready before connecting.
		 */
		internal_waitUntilIsConnected();
		
		//TODO
		//internal_runOnCounterpart(commandHeader + "(" + new ReadContainer<>(commandAttributes) + ")");
		
		internal_runOnCounterpart(
			new ChainedNode(
				commandHeader,
				new ReadContainer<>(commandAttributes).to(ca -> Node.fromString(ca)))
			);
	}
	
	//method
	private byte[] readFileToBytes() {
		return getRefGUI().onFrontEnd().readFile();
	}
	
	//method
	private void saveFile(final byte[] content) {
		getRefGUI().onFrontEnd().saveFile(content);
	}
}
