//package declaration
package ch.nolix.element.GUI;

//class
/**
 * An invisble GUI is a GUI that is not visible.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public final class InvisibleGUI extends GUI<InvisibleGUI> {

	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//method
	/**
	 * Sets the position of the cursor on this invisible GUI.
	 * 
	 * @param cursorXPosition
	 * @param cursorYPosition
	 */
	public void setCursorPosition(
		final int cursorXPosition,
		final int cursorYPosition
	) {
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
	}

	//method
	/**
	 * @return the x-position of the cursor on this invisible GUI.
	 */
	public int getCursorXPosition() {
		return cursorYPosition;
	}

	//method
	/**
	 * @return the y-position of the cursor on this invisible GUI.
	 */
	public int getCursorYPosition() {
		return cursorXPosition;
	}
}
