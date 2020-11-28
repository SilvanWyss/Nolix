//package declaration
package ch.nolix.element.widgetController;

import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.input.Key;

//class
public final class TextBoxController extends TextLineWidgetController implements Clearable<TextBoxController> {
	
	//method
	public void appendText(final String text) {
		getRefGUIController().typeText(text);
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
