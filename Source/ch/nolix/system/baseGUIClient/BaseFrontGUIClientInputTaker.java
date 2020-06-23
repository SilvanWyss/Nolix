//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IIntGetter;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.elementEnum.DirectionOfRotation;
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
 * @month 2019-08
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
		inputTaker.run(new KeyInput(key, KeyInputType.Press));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyRelease(final Key key) {
		inputTaker.run(new KeyInput(key, KeyInputType.Release));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyTyping(Key key) {
		inputTaker.run(new KeyInput(key, KeyInputType.Typing));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteLeftMouseButtonClick() {
		inputTaker.run(
			new MouseInput(
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.LeftMouseButtonClick
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.LeftMouseButtonPress
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.LeftMouseButtonRelease
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
			new MouseInput(cursorXPositionOnViewArea, cursorYPositionOnViewArea, MouseInputType.MouseMove)
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.MouseWheelClick
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.MouseWheelPress
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.MouseWheelRelease
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		inputTaker.run(
			new MouseInput(
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				directionOfRotation == DirectionOfRotation.Backward ?
				MouseInputType.BackwardMouseWheelRotationStep : MouseInputType.ForwardMouseWheelRotationStep
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.RightMouseButtonClick
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.RightMouseButtonPress
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
				cursorXPositionOnViewAreaGetter.getOutput(),
				cursorYPositionOnViewAreaGetter.getOutput(),
				MouseInputType.RightMouseButtonRelease
			)
		);
	}
}
