//package declaration
package ch.nolix.element.widgetcontroller;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.testablegui.GUIElementController;

//class
abstract class BorderWidgetController extends GUIElementController {
	
	//method
	public final Color getBaseBackgroundColor() {
		return Color.fromSpecification(getAttribute("BaseBackgroundColor"));
	}
	
	//method
	public final Color getFocusBackgroundColor() {
		return Color.fromSpecification(getAttribute("FocusBackgroundColor"));
	}
	
	//method
	public final Color getHoverBackgroundColor() {
		return Color.fromSpecification(getAttribute("HoverBackgroundColor"));
	}
}
