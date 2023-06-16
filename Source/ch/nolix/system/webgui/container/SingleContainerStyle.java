//package declaration
package ch.nolix.system.webgui.container;

import ch.nolix.system.graphic.color.Color;
//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.containerapi.ISingleContainerStyle;

//class
public final class SingleContainerStyle
extends ExtendedControlStyle<SingleContainerStyle>
implements ISingleContainerStyle<SingleContainerStyle> {
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constructor
	public SingleContainerStyle() {
		initialize();
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
}
