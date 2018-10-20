//package declaration
package ch.nolix.element.GUI;

//class
/**
 * A {@link InvisibleGUI} is a {@link GUI} that is not visible.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
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
		reset();
		approveProperties();
	}
	
	//method
	/**
	 * Closes the current {@link InvisibleGUI}.
	 */
	public void close() {}
	
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
	public int getHeight() {
		//TODO
		return 0;
	}
	
	//method
	public int getWidth() {
		//TODO
		return 0;
	}
	
	//method
	public int getContentHeight() {
		//TODO
		return 0;
	}
	
	//method
	public int getContentWidth() {
		//TODO
		return 0;
	}
	
	//method
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final void paint() {}
	
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
}
