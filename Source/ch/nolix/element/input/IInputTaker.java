//package declaration
package ch.nolix.element.input;

import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementenum.RotationDirection;

//interface
/**
 * @author Silvan
 * @month 2019-08
 * @lines 330
 */
public interface IInputTaker {
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note the given input.
	 * 
	 * @param input
	 * @throws InvalidArgumentException if the given input is not valid.
	 */
	default void noteInput(final IInput<?> input) {
		
		if (input instanceof MouseInput) {
			noteMouseInput(input.as(MouseInput.class));
		}
		
		else if (input instanceof KeyInput) {
			noteKeyInput(input.as(KeyInput.class));
		}
		
		else {
			throw new InvalidArgumentException(input);
		}
	}
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note the given keyInput.
	 * 
	 * @param keyInput
	 */
	default void noteKeyInput(final KeyInput keyInput) {
		switch (keyInput.getInputType()) {
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
	 * Lets the current {@link IInputTaker} note a key press.
	 * 
	 * @param key
	 */
	void noteKeyPress(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a key release.
	 * 
	 * @param key
	 */
	void noteKeyRelease(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a key typing.
	 * 
	 * @param key
	 */
	void noteKeyTyping(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button click.
	 */
	void noteLeftMouseButtonClick();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button click at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button press.
	 */
	void noteLeftMouseButtonPress();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button press at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button release.
	 */
	void noteLeftMouseButtonRelease();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button release at the given position.
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
	 * Lets the current {@link IInputTaker} note the given mouseInput.
	 * 
	 * @param mouseInput
	 */
	default void noteMouseInput(final MouseInput mouseInput) {
		switch (mouseInput.getInputType()) {
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
	 * Lets the current {@link IInputTaker} note a mouse move.
	 * The given cursorXPositionOnViewArea and cursorYPositionOnViewArea are the new position of the cursor.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel clcik.
	 */
	void noteMouseWheelClick();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel click at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel press.
	 */
	void noteMouseWheelPress();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel press at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel release.
	 */
	void noteMouseWheelRelease();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel release at the given position.
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
	 * Lets the current {@link IInputTaker} note a mouse wheel rotation step.
	 * 
	 * @param rotationDirection
	 */
	void noteMouseWheelRotationStep(final RotationDirection rotationDirection);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button click.
	 */
	void noteRightMouseButtonClick();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button click at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button press.
	 */
	void noteRightMouseButtonPress();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button press at the given position.
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
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button release.
	 */
	void noteRightMouseButtonRelease();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button release at the given position.
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
