//package declaration
package ch.nolix.element.widgetController;

//own import
import ch.nolix.element.color.Color;

//class
abstract class TextLineWidgetController extends BorderWidgetController {
	
	//method
	public final Color getBaseTextColor() {
		return Color.fromSpecification(getAttribute("BaseTextColor"));
	}
	
	//method
	public final Color getFocusTextColor() {
		return Color.fromSpecification(getAttribute("FocusTextColor"));
	}
	
	//method
	public final Color getHoverTextColor() {
		return Color.fromSpecification(getAttribute("HoverTextColor"));
	}
	
	//method
	public final String getText() {
		return getAttribute("Text").getOneAttributeAsString();
	}
	
	//method
	public final int getTextLength() {
		return getText().length();
	}
	
	//method
	public final boolean isBlank() {
		return getText().isBlank();
	}
	
	//method
	public final boolean isEmpty() {
		return getText().isEmpty();
	}
}
