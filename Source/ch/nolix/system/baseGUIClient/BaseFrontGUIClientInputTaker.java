//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementEnum.DirectionOfRotation;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.input.KeyInput;
import ch.nolix.element.input.KeyInputType;
import ch.nolix.element.input.MouseInput;

//class
/**
 * @author Silvan Wyss
 * @month 2019-08
 * @lines 170
 */
final class BaseFrontGUIClientInputTaker implements IInputTaker {
	
	//attribute
	/**
	 * The {@link BaseFrontGUIClient} the current {@link BaseFrontGUIClientInputTaker} belongs to.
	 */
	private final BaseFrontGUIClient<?> parentFrontGuiClientoid;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontGUIClientInputTaker} that will belong to the given parentFrontGuiClientoid.
	 * 
	 * @param parentFrontGuiClientoid
	 * @throws ArgumentIsNullException if the given parentFrontGuiClientoid is null.
	 */
	public BaseFrontGUIClientInputTaker(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		
		Validator.assertThat(parentFrontGuiClientoid).thatIsNamed("parent FrontGUIClientoid").isNotNull();
		
		this.parentFrontGuiClientoid = parentFrontGuiClientoid;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyPress(final Key key) {
		parentFrontGuiClientoid.noteInputOnCounterpart(new KeyInput(key, KeyInputType.Press));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyRelease(final Key key) {
		parentFrontGuiClientoid.noteInputOnCounterpart(new KeyInput(key, KeyInputType.Release));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyTyping(Key key) {
		parentFrontGuiClientoid.noteInputOnCounterpart(new KeyInput(key, KeyInputType.Typing));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonClick() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.LeftMouseButtonClick);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPress() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.LeftMouseButtonPress);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonRelease() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.LeftMouseButtonRelease);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {
		parentFrontGuiClientoid.noteMouseMoveOnCounterpart(cursorXPositionOnViewArea, cursorYPositionOnViewArea);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelClick() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.MouseWheelClick);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelPress() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.MouseWheelPress);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRelease() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.MouseWheelRelease);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		parentFrontGuiClientoid.noteInputOnCounterpart(
			directionOfRotation == DirectionOfRotation.Backward ?
			MouseInput.BackwardMouseWheelRotationStep : MouseInput.ForwardMouseWheelRotationStep
		);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonClick() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.RightMouseButtonClick);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonPress() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.RightMouseButtonPress);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonRelease() {
		parentFrontGuiClientoid.noteInputOnCounterpart(MouseInput.RightMouseButtonRelease);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		parentFrontGuiClientoid.noteResizeOnCounterpart(viewAreaWidth, viewAreaHeight);		
	}
}
