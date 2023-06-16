//package declaration
package ch.nolix.system.webgui.control;

import ch.nolix.system.graphic.color.Color;
//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.controlapi.IToggleButtonStyle;

//class
public final class ToggleButtonStyle extends ExtendedControlStyle<IToggleButtonStyle> implements IToggleButtonStyle {
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constructor
	public ToggleButtonStyle() {
		initialize();
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
}
