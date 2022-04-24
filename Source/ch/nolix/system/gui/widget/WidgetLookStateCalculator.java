//package declaration
package ch.nolix.system.gui.widget;

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
