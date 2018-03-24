//package declaration
package ch.nolix.element.GUI;

//class
/**
 * A {@link InvisibleGUI} is a {@link GUI} that is not visible.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public final class InvisibleGUI extends GUI<InvisibleGUI> {

	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//constructor
	/**
	 * Creates a new {@link InvisibleGUI}.
	 */
	public InvisibleGUI() {
		approveProperties();
	}
	
	//method
	/**
	 * Sets the position of the cursor on the current {@link InvisibleGUI}.
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
	 * @return the x-position of the cursor on the current {@link InvisibleGUI}.
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

	//method
	/**
	 * Closes the current {@link InvisibleGUI}.
	 */
	public void close() {}

	//method
	/**
	 * Refreshes the current {@link InvisibleGUI}.
	 */
	public final void refresh() {}
}
