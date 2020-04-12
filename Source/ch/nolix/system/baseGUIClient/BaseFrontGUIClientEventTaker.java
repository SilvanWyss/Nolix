//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.Key;

//class
/**
 * @author Silvan Wyss
 * @month 2019-08
 * @lines 170
 */
final class BaseFrontGUIClientEventTaker implements IInputTaker {
	
	//attribute
	/**
	 * The {@link BaseFrontGUIClient} the current {@link BaseFrontGUIClientEventTaker} belongs to.
	 */
	private final BaseFrontGUIClient<?> parentFrontGuiClientoid;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontGUIClientEventTaker} that will belong to the given parentFrontGuiClientoid.
	 * 
	 * @param parentFrontGuiClientoid
	 * @throws ArgumentIsNullException if the given parentFrontGuiClientoid is null.
	 */
	public BaseFrontGUIClientEventTaker(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		
		Validator.assertThat(parentFrontGuiClientoid).thatIsNamed("parent FrontGUIClientoid").isNotNull();
		
		this.parentFrontGuiClientoid = parentFrontGuiClientoid;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyPress(final Key key) {
		parentFrontGuiClientoid.noteKeyPressOnCounterpart(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyRelease(final Key key) {
		parentFrontGuiClientoid.noteKeyReleaseOnCounterpart(key);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyTyping(Key key) {
		parentFrontGuiClientoid.noteKeyTypingOnCounterpart(key);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonClick() {
		parentFrontGuiClientoid.noteLeftMouseButtonClickOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPress() {
		parentFrontGuiClientoid.noteLeftMouseButtonPressOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonRelease() {
		parentFrontGuiClientoid.noteLeftMouseButtonReleaseOnCounterpart();		
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
		parentFrontGuiClientoid.noteMouseWheelClickOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelPress() {
		parentFrontGuiClientoid.noteMouseWheelPressOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRelease() {
		parentFrontGuiClientoid.noteMouseWheelReleaseOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		parentFrontGuiClientoid.noteMouseWheelRotationStepOnCounterpart(directionOfRotation);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonClick() {
		parentFrontGuiClientoid.noteRightMouseButtonClickOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonPress() {
		parentFrontGuiClientoid.noteRightMouseButtonPressOnCounterpart();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonRelease() {
		parentFrontGuiClientoid.noteRightMouseButtonReleaseOnCounterpart();		
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
