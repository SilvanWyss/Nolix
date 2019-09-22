//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
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
 * @lines 260
 * @param <FGC> The type of a {@link BaseFrontGUIClient}.
 */
public abstract class BaseFrontGUIClient<FGC extends BaseFrontGUIClient<FGC>> extends Client<FGC> {
	
	//attribute
	private final BaseFrontGUIClientGUIHandler mGUIHandler;
	
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
	public final void noteLeftMOuseButtonPressOnCounterpart() {
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
		
		//Handles the case that the GUI handler of the current FrontGUIClientoid can run the given command.
		if (mGUIHandler.canRunCommand(command)) {
			mGUIHandler.run(command);
		}
		
		//Handles the case that the GUI handler of the current FrontGUIClientoid cannot run the given command.
		else {
			
			//Calls method of the base class.
			super.internal_run(command);
		}
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
		
		internal_runOnCounterpart(commandHeader + "(" + new ReadContainer<>(commandAttributes) + ")");
	}
	
	//method
	/**
	 * @return the {@link GUI} of the current {@link FrontGUIClient}.
	 */
	private GUI<?> getRefGUI() {
		return mGUIHandler.getRefGUI();
	}
}
