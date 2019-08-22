//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.input.Key;

//interface
public interface IEventTaker {
	
	//abstract method
	/**
	 * Lets the current {@link IBaseGUI} note a key press.
	 * 
	 * @param key
	 */
	public abstract void noteKeyPress(Key key);
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a key release.
	 * 
	 * @param key
	 */
	public abstract void noteKeyRelease(Key key);
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a key typing.
	 * 
	 * @param key
	 */
	public abstract void noteKeyTyping(Key key);
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button click.
	 */
	public abstract void noteLeftMouseButtonClick();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button press.
	 */
	public abstract void noteLeftMouseButtonPress();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a left mouse button release.
	 */
	public abstract void noteLeftMouseButtonRelease();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a mouse move.
	 * The given cursorXPositionOnViewArea and cursorYPositionOnViewArea are the new position of the cursor.
	 * 
	 * @param cursorXPositionOnViewArea
	 * @param cursorYPositionOnViewArea
	 */
	public abstract void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea);
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel clcik.
	 */
	public abstract void noteMouseWheelClick();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel press.
	 */
	public abstract void noteMouseWheelPress();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel release.
	 */
	public abstract void noteMouseWheelRelease();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a mouse wheel rotation step.
	 * 
	 * @param directionOfRotation
	 */
	public abstract void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation);
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a resize.
	 * The given viewAreaWidth and viewAreaHeight are the new size of the view area.
	 * 
	 * @param viewAreaWidth
	 * @param viewAreaHeight
	 */
	public abstract void noteResize(int viewAreaWidth, int viewAreaHeight);

	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button click.
	 */
	public abstract void noteRightMouseButtonClick();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button press.
	 */
	public abstract void noteRightMouseButtonPress();
	
	//abstract method
	/**
	 * Lets the current {@link IEventTaker} note a right mouse button release.
	 */
	public abstract void noteRightMouseButtonRelease();
}
