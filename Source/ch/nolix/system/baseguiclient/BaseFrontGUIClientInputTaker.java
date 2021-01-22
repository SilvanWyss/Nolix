//package declaration
package ch.nolix.system.baseguiclient;

//own imports
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.functionapi.IIntGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.input.IInput;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.input.KeyInput;
import ch.nolix.element.input.KeyInputType;
import ch.nolix.element.input.MouseInput;
import ch.nolix.element.input.MouseInputType;
import ch.nolix.element.input.ResizeInput;

//class
/**
 * @author Silvan Wyss
 * @date 2019-08-02
 * @lines 260
 */
final class BaseFrontGUIClientInputTaker implements IResizableInputTaker {
	
	//attributes
	private final IElementTaker<IInput<?>> inputTaker;
	private final IIntGetter cursorXPositionOnViewAreaGetter;
	private final IIntGetter cursorYPositionOnViewAreaGetter;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontGUIClientInputTaker}
	 * with the given inputTaker, cursorXPositionOnViewAreaGetter and cursorYPositionOnViewAreaGetter.
	 * 
	 * @param inputTaker
	 * @param cursorXPositionOnViewAreaGetter
	 * @param cursorYPositionOnViewAreaGetter
	 * @throws ArgumentIsNullException if the given inputTaker is null.
	 * @throws ArgumentIsNullException if the given cursorXPositionOnViewAreaGetter is null.
	 * @throws ArgumentIsNullException if the given cursorYPositionOnViewAreaGetter is null.
	 */
	public BaseFrontGUIClientInputTaker(
		final IElementTaker<IInput<?>> inputTaker,
		final IIntGetter cursorXPositionOnViewAreaGetter,
		final IIntGetter cursorYPositionOnViewAreaGetter
	) {
		
		Validator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		Validator
		.assertThat(cursorXPositionOnViewAreaGetter)
		.thatIsNamed("cursor x-position on view area getter")
		.isNotNull();
		
		Validator
		.assertThat(cursorYPositionOnViewAreaGetter)
		.thatIsNamed("cursor y-position on view area getter")
		.isNotNull();
		
		this.inputTaker = inputTaker;
		this.cursorXPositionOnViewAreaGetter = cursorXPositionOnViewAreaGetter;
		this.cursorYPositionOnViewAreaGetter = cursorYPositionOnViewAreaGetter;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyPress(final Key key) {
		inputTaker.run(new KeyInput(key, KeyInputType.PRESS));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyRelease(final Key key) {
		inputTaker.run(new KeyInput(key, KeyInputType.RELEASE));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyTyping(Key key) {
		inputTaker.run(new KeyInput(key, KeyInputType.TYPING));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonClick() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.LEFT_MOUSE_BUTTON_CLICK,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonPress() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.LEFT_MOUSE_BUTTON_PRESS,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonRelease() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.LEFT_MOUSE_BUTTON_RELEASE,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {
		inputTaker.run(
			new MouseInput(MouseInputType.MOUSE_MOVE, cursorXPositionOnViewArea, cursorYPositionOnViewArea)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelClick() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.MOUSE_WHEEL_CLICK,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelPress() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.MOUSE_WHEEL_PRESS,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRelease() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.MOUSE_WHEEL_RELEASE,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		inputTaker.run(
			new MouseInput(
				MouseInputType.createMouseWheelRotationStepFrom(rotationDirection),
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		inputTaker.run(new ResizeInput(viewAreaWidth, viewAreaHeight));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonClick() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.RIGHT_MOUSE_BUTTON_CLICK,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonPress() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.RIGHT_MOUSE_BUTTON_PRESS,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);	
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteRightMouseButtonRelease() {
		inputTaker.run(
			new MouseInput(
					MouseInputType.RIGHT_MOUSE_BUTTON_RELEASE,
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput()
			)
		);
	}
}
