//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;

//interface
public interface IEventTaker {
	
	//method declaration
	/**
	 * Lets the current {@link IBaseGUI} note a key press.
	 * 
	 * @param key
	 */
	public abstract void noteKeyPress(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a key release.
	 * 
	 * @param key
	 */
	public abstract void noteKeyRelease(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a key typing.
	 * 
	 * @param key
	 */
	public abstract void noteKeyTyping(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button click.
	 */
	public abstract void noteLeftMouseButtonClick();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button press.
	 */
	public abstract void noteLeftMouseButtonPress();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button release.
	 */
	public abstract void noteLeftMouseButtonRelease();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a mouse move.
	 * The given cursorXPositionOnViewArea and cursorYPositionOnViewArea are the new position of the cursor.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public abstract void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea);
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel clcik.
	 */
	public abstract void noteMouseWheelClick();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel press.
	 */
	public abstract void noteMouseWheelPress();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel release.
	 */
	public abstract void noteMouseWheelRelease();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel rotation step.
	 * 
	 * @param directionOfRotation
	 */
	public abstract void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation);
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a resize.
	 * The given viewAreaWidth and viewAreaHeight are the new size of the view area.
	 * 
	 * @param viewAreaWidth
	 * @param viewAreaHeight
	 */
	public abstract void noteResize(int viewAreaWidth, int viewAreaHeight);

	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button click.
	 */
	public abstract void noteRightMouseButtonClick();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button press.
	 */
	public abstract void noteRightMouseButtonPress();
	
	//method declaration
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button release.
	 */
	public abstract void noteRightMouseButtonRelease();
}
