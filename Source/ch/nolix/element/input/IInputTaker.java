//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.keyBoard.KeyInput;

//interface
/**
 * @author Silvan
 * @month 2019-08
 * @lines 180
 */
public interface IInputTaker {
	
	//method
	public default void noteKeyInput(final KeyInput keyInput) {
		switch (keyInput.getInputType()) {
			case Press:
				noteKeyPress(keyInput.getKey());
				break;
			case Release:
				noteKeyRelease(keyInput.getKey());
				break;
			case Typing:
				noteKeyTyping(keyInput.getKey());
				break;
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link IBaseGUI} note a key press.
	 * 
	 * @param key
	 */
	public abstract void noteKeyPress(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a key release.
	 * 
	 * @param key
	 */
	public abstract void noteKeyRelease(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a key typing.
	 * 
	 * @param key
	 */
	public abstract void noteKeyTyping(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button click.
	 */
	public abstract void noteLeftMouseButtonClick();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button click at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public default void noteLeftMouseButtonClick(
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
	public abstract void noteLeftMouseButtonPress();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button press at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public default void noteLeftMouseButtonPress(
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
	public abstract void noteLeftMouseButtonRelease();
	
	//method
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button release at the given position.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public default void noteLeftMouseButtonRelease(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {
		noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		noteLeftMouseButtonRelease();
	}
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse move.
	 * The given cursorXPositionOnViewArea and cursorYPositionOnViewArea are the new position of the cursor.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public abstract void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel clcik.
	 */
	public abstract void noteMouseWheelClick();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel press.
	 */
	public abstract void noteMouseWheelPress();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel release.
	 */
	public abstract void noteMouseWheelRelease();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel rotation step.
	 * 
	 * @param directionOfRotation
	 */
	public abstract void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation);
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a resize.
	 * The given viewAreaWidth and viewAreaHeight are the new size of the view area.
	 * 
	 * @param viewAreaWidth
	 * @param viewAreaHeight
	 */
	public abstract void noteResize(int viewAreaWidth, int viewAreaHeight);

	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button click.
	 */
	public abstract void noteRightMouseButtonClick();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button press.
	 */
	public abstract void noteRightMouseButtonPress();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button release.
	 */
	public abstract void noteRightMouseButtonRelease();
}
