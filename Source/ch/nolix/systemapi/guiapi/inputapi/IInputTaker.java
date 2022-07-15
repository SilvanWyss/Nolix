//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//interface
/**
 * @author Silvan Wyss
 * @date 2022-07-15
 */
public interface IInputTaker {
	
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
	 * Lets the current {@link IInputTaker} note a left mouse button press.
	 */
	void noteLeftMouseButtonPress();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button release.
	 */
	void noteLeftMouseButtonRelease();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel press.
	 */
	void noteMouseWheelPress();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel release.
	 */
	void noteMouseWheelRelease();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button press.
	 */
	void noteRightMouseButtonPress();
	
	//method declaration
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button release.
	 */
	void noteRightMouseButtonRelease();
}
