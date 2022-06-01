//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.functionuniversalapi.IIntGetter;
import ch.nolix.system.gui.input.KeyInput;
import ch.nolix.system.gui.input.MouseInput;
import ch.nolix.system.gui.input.ResizeInput;
import ch.nolix.systemapi.guiapi.inputapi.IInput;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.processproperty.KeyInputType;
import ch.nolix.systemapi.guiapi.processproperty.MouseInputType;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;

//class
/**
 * @author Silvan Wyss
 * @date 2019-08-02
 */
final class BaseFrontendGUIClientGUIInputTaker implements IResizableInputTaker {
	
	//attributes
	private final IElementTaker<IInput<?>> inputTaker;
	private final IIntGetter cursorXPositionOnViewAreaGetter;
	private final IIntGetter cursorYPositionOnViewAreaGetter;
	
	//constructor
	/**
	 * Creates a new {@link BaseFrontendGUIClientGUIInputTaker}
	 * with the given inputTaker, cursorXPositionOnViewAreaGetter and cursorYPositionOnViewAreaGetter.
	 * 
	 * @param inputTaker
	 * @param cursorXPositionOnViewAreaGetter
	 * @param cursorYPositionOnViewAreaGetter
	 * @throws ArgumentIsNullException if the given inputTaker is null.
	 * @throws ArgumentIsNullException if the given cursorXPositionOnViewAreaGetter is null.
	 * @throws ArgumentIsNullException if the given cursorYPositionOnViewAreaGetter is null.
	 */
	public BaseFrontendGUIClientGUIInputTaker(
		final IElementTaker<IInput<?>> inputTaker,
		final IIntGetter cursorXPositionOnViewAreaGetter,
		final IIntGetter cursorYPositionOnViewAreaGetter
	) {
		
		GlobalValidator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		GlobalValidator
		.assertThat(cursorXPositionOnViewAreaGetter)
		.thatIsNamed("cursor x-position on view area getter")
		.isNotNull();
		
		GlobalValidator
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
	public void noteKeyDown(final Key key) {
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
