//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 260
 * @param <FGC> The type of a {@link FrontGUIClientoid}.
 */
public abstract class FrontGUIClientoid<FGC extends FrontGUIClientoid<FGC>> extends Client<FGC> {
	
	//attribute
	private final FrontGUIClientoidGUIHandler mGUIHandler;
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClientoid} that will have the given GUIType.
	 * 
	 * @param GUIType
	 */
	public FrontGUIClientoid(final FrontGUIClientoidGUIType GUIType) {		
		
		//Enumerates the given GUIType.
		switch (GUIType) {
			case LayerGUI:
				mGUIHandler = new FrontGUIClientoidLayerGUIHandler(this);
				break;
			case CanvasGUI:
				mGUIHandler = new FrontGUIClientoidCanvasGUIHandler(this);
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
	 * @return the type of the GUI of the current {@link FrontGUIClientoid}.
	 */
	public final FrontGUIClientoidGUIType getGUIType() {
		return mGUIHandler.getGUIType();
	}
	
	//method
	public void noteKeyPressOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_PRESS_HEADER, key.toString());
	}
	
	//method
	public final void noteKeyReleaseOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_RELEASE_HEADER, key.toString());
	}
	
	//method
	public final void noteKeyTypingOnCounterpart(final Key key) {
		noteCommandOnCounterpart(Protocol.NOTE_KEY_TYPING_HEADER, key.toString());		
	}
	
	//method
	public final void noteLeftMouseButtonClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_CLICK_HEADER);
	}
	
	//method
	public final void noteLeftMOuseButtonPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER);
	}
	
	//method
	public final void noteLeftMouseButtonReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER);
	}
	
	//method
	public final void noteMouseMoveOnCounterpart(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {
		noteCommandOnCounterpart(
			Protocol.NOTE_MOUSE_MOVE_HEADER,
			String.valueOf(cursorXPositionOnViewArea),
			String.valueOf(cursorYPositionOnViewArea)
		);		
	}
	
	//method
	public final void noteMouseWheelClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_CLICK_HEADER);
	}
	
	//method
	public final void noteMouseWheelPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_PRESS_HEADER);		
	}
	
	//method
	public final void noteMouseWheelReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_RELEASE_HEADER);
	}
	
	//method
	public final void noteMouseWheelRotationStepOnCounterpart(final DirectionOfRotation directionOfRotation) {
		noteCommandOnCounterpart(Protocol.NOTE_MOUSE_WHEEL_ROTATION_STEP_HEADER, directionOfRotation.toString());
	}
	
	//method
	public final void noteRightMouseButtonClickOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_CLICK_HEADER);	
	}
	
	//method
	public final void noteRightMouseButtonPressOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER);	
	}
	
	//method
	public final void noteRightMouseButtonReleaseOnCounterpart() {
		noteCommandOnCounterpart(Protocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER);
	}
	
	//method
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
	protected void internal_finishSessionInitialization() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DocumentNode internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.GUI_TYPE_HEADER:
				return DocumentNode.createWithHeader(getGUIType());
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
	protected final void internal_run(final Statement command) {
		
		//Handles the case that the GUI handler of the current FrontGUIClientoid can run the given command.
		if (mGUIHandler.canRunCommand(command)) {
			mGUIHandler.run(command);
		}
		
		//Handles the case that the GUI handler of the current FrontGUIClientoid cannot run the given command.
		else {
					
			//Enumerates the header of the given command.
			switch (command.getHeader()) {
				case Protocol.COUNTERPART_HEADER:
					runCounterpartCommand(command.getRefNextStatement());
					break;
				default:
				
					//Calls method of the base class.
					super.internal_run(command);
			}
		}
	}

	//method
	/**
	 * Lets the counterpart of the current {@link FrontGUIClient}
	 * note the run of the command identified by the given command header on the given widget.
	 * 
	 * @param widget
	 * @param commandHeader
	 */
	private void noteCommandOnCounterpart(final String commandHeader, final String... commandAttributes) {
		
		final var command = commandHeader + "(" + commandAttributes + ")";
		
		if (!mGUIHandler.providesUpdateCommandForCounterpart()) {
			internal_runOnCounterpart(command);
		}
		else {
			internal_runOnCounterpart(mGUIHandler.getUpdateCommandForCounterpart(), command);
		}
	}

	//method
	/**
	 * Resets the GUI of the counterpart of the current {@link FrontGUIClient}
	 * with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetCounterpartGUI(final Iterable<? extends DocumentNodeoid> attributes) {
		internal_runOnCounterpart(
			Protocol.GUI_HEADER
			+ '.'
			+ Protocol.RESET_HEADER
			+ '('
			+ attributes
			+ ')'
		);
	}

	//method
	/**
	 * Lets the current {@link FrontGUIClient} run the given counterpart command.
	 * 
	 * @param counterpartCommand
	 * @throws InvalidArgumentException if the given counterpart command is not valid.
	 */
	private void runCounterpartCommand(final Statement counterpartCommand) {
		
		//Enumerates the header of the given counterpart command.
		switch (counterpartCommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetCounterpartGUI(counterpartCommand.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("counterpart command",	counterpartCommand,	"is not valid");
		}
	}
}
