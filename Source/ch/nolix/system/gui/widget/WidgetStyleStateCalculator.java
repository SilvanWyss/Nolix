//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

//class
final class WidgetStyleStateCalculator {
	
	//method
	public ControlState calculateLookStateFor(final Widget<?, ?> widget) {
		
		if (!widget.isFocused()) {
			return calculateLookStateForUnfocusedWidget(widget);
		}
		
		return calculateLookStateForFocusedWidget(widget);
	}
	
	//method
	private ControlState calculateLookStateForFocusedWidget(final Widget<?, ?> widget) {
		
		if (!widget.isHovered()) {
			return ControlState.FOCUS;
		}
		
		return ControlState.HOVER;
	}
	
	//method
	private ControlState calculateLookStateForUnfocusedWidget(final Widget<?, ?> widget) {
		
		if (!widget.isHovered()) {
			return ControlState.BASE;
		}
		
		return ControlState.HOVER;
	}
}
