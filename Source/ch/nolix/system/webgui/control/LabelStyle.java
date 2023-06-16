//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.controlapi.ILabelStyle;

//class
public final class LabelStyle extends ExtendedControlStyle<LabelStyle> implements ILabelStyle<LabelStyle> {
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constructor
	public LabelStyle() {
		initialize();
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
}
