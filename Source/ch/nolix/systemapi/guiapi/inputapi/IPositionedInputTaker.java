//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;

//interface
/**
 * @author Silvan
 * @date 2019-08-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IPositionedInputTaker extends IInputTaker {
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note the given input.
	 * 
	 * @param input
	 * @throws RuntimeException if the given input is not valid.
	 */
	default void noteInput(final IInput<?> input) {
		if (input instanceof IMouseInput) {
			noteMouseInput((IMouseInput<?>)input);
		} else if (input instanceof IKeyInput) {
			noteKeyInput((IKeyInput<?>)input);
		} else {
			throw new IllegalArgumentException("The given input is neither a IMouseInput nor a IKeyInput.");
		}
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note the given keyInput.
	 * 
	 * @param keyInput
	 */
	default void noteKeyInput(final IKeyInput<?> keyInput) {
		switch (keyInput.getKeyInputType()) {
			case PRESS:
				noteKeyPress(keyInput.getKey());
				break;
			case RELEASE:
				noteKeyRelease(keyInput.getKey());
				break;
			case TYPING:
				noteKeyTyping(keyInput.getKey());
				break;
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link IPositionedInputTaker} note a left mouse button click.
	 */
	void noteLeftMouseButtonClick();
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a left mouse button click at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteLeftMouseButtonClick(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteLeftMouseButtonClick();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a left mouse button press at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteLeftMouseButtonPress(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteLeftMouseButtonPress();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a left mouse button release at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteLeftMouseButtonRelease(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteLeftMouseButtonRelease();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note the given mouseInput.
	 * 
	 * @param mouseInput
	 */
	default void noteMouseInput(final IMouseInput<?> mouseInput) {
		switch (mouseInput.getMouseInputType()) {
			case MOUSE_MOVE:
				noteMouseMove(mouseInput.getCursorXPosition(), mouseInput.getCursorYPosition());
				break;
			case LEFT_MOUSE_BUTTON_PRESS:
				noteLeftMouseButtonPress();
				break;
			case LEFT_MOUSE_BUTTON_RELEASE:
				noteLeftMouseButtonRelease();
				break;
			case LEFT_MOUSE_BUTTON_CLICK:
				noteLeftMouseButtonClick();
				break;
			case RIGHT_MOUSE_BUTTON_PRESS:
				noteRightMouseButtonPress();
				break;
			case RIGHT_MOUSE_BUTTON_RELEASE:
				noteRightMouseButtonRelease();
				break;
			case RIGHT_MOUSE_BUTTON_CLICK:
				noteRightMouseButtonClick();
				break;
			case MOUSE_WHEEL_PRESS:
				noteMouseWheelPress();
				break;
			case MOUSE_WHEEL_RELEASE:
				noteMouseWheelRelease();
				break;
			case MOUSE_WHEEL_CLICK:
				noteMouseWheelClick();
				break;
			case FORWARD_MOUSE_WHEEL_ROTATION_STEP:
				noteMouseWheelRotationStep(RotationDirection.FORWARD);
				break;
			case BACKWARD_MOUSE_WHEEL_ROTATION_STEP:
				noteMouseWheelRotationStep(RotationDirection.BACKWARD);
				break;
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse move.
	 * The given cursorXPositionOnViewArea and cursorYPositionOnViewArea are the new position of the cursor.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea);
	
	//method declaration
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse wheel clcik.
	 */
	void noteMouseWheelClick();
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse wheel click at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteMouseWheelClick(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteMouseWheelClick();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse wheel press at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteMouseWheelPress(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteMouseWheelPress();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse wheel release at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteMouseWheelRelease(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteMouseWheelRelease();
	}
	
	//method declaration
	/**
	 * Lets the current {@link IPositionedInputTaker} note a mouse wheel rotation step.
	 * 
	 * @param rotationDirection
	 */
	void noteMouseWheelRotationStep(final RotationDirection rotationDirection);
	
	//method declaration
	/**
	 * Lets the current {@link IPositionedInputTaker} note a right mouse button click.
	 */
	void noteRightMouseButtonClick();
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a right mouse button click at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteRightMouseButtonClick(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteRightMouseButtonClick();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a right mouse button press at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteRightMouseButtonPress(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteRightMouseButtonPress();
	}
	
	//method
	/**
	 * Lets the current {@link IPositionedInputTaker} note a right mouse button release at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	default void noteRightMouseButtonRelease(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteRightMouseButtonRelease();
	}
}
