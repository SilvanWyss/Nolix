//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.validator2.Validator;

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
	private CursorIcon cursorIcon;
	private int mouseXPosition;
	private int mouseYPosition;
	
	//method
	public void applyCursorIcon(final CursorIcon cursorIcon) {
		
		//Checks if the given cursor icon is not null.
		Validator.supposeThat(cursorIcon).thatIsInstanceOf(CursorIcon.class).isNotNull();
		
		this.cursorIcon = cursorIcon;
	}
	
	//method
	public CursorIcon getCursorIcon() {
		return cursorIcon;
	}
	
	//method
	public void setMousePosition(final int mouseXPosition, final int mouseYPosition) {
		this.mouseXPosition = mouseXPosition;
		this.mouseYPosition = mouseYPosition;
	}

	//method
	protected int getMouseXPosition() {
		return mouseYPosition;
	}

	//method
	protected int getMouseYPosition() {
		return mouseXPosition;
	}
}
