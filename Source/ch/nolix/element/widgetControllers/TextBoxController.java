//package declaration
package ch.nolix.element.widgetControllers;

//own imports
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.element.input.Key;

//class
public final class TextBoxController extends TextLineWidgetController implements Clearable<TextBoxController> {
	
	//method
	public void appendText(final String text) {
		getParentGUI().typeText(text);
	}
	
	//method
	@Override
	public TextBoxController clear() {
		
		clickLeftMouseButtonOnTopLeft();
		
		while (!isEmpty()) {
			getParentGUI().noteKeyPress(Key.BACKSPACE);
		}
		
		return this;
	}
	
	//method
	public void setText(final String text) {
		clear();
		appendText(text);
	}
}
