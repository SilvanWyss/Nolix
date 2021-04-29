//package declaration
package ch.nolix.element.gui.base;

//class
final class WidgetLookStateCalculator {
	
	//method
	public WidgetLookState calculateLookStateFor(final Widget<?, ?> widget) {
		
		if (!widget.isFocused()) {
			return calculateLookStateForUnfocusedWidget(widget);
		}
		
		return calculateLookStateForFocusedWidget(widget);
	}
	
	//method
	private WidgetLookState calculateLookStateForFocusedWidget(final Widget<?, ?> widget) {
		
		if (!widget.isHovered()) {
			return WidgetLookState.FOCUS;
		}
		
		return WidgetLookState.HOVER;
	}
	
	//method
	private WidgetLookState calculateLookStateForUnfocusedWidget(final Widget<?, ?> widget) {
		
		if (!widget.isHovered()) {
			return WidgetLookState.BASE;
		}
		
		return WidgetLookState.HOVER;
	}
}
