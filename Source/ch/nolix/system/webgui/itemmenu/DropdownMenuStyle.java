//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenuStyle;

//class
public final class DropdownMenuStyle
extends ItemMenuStyle<DropdownMenuStyle>
implements IDropdownMenuStyle<DropdownMenuStyle> {
	
	//constant
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constructor
	public DropdownMenuStyle() {
		initialize();
	}
	
	//method
	@Override
	public IColor getDefaultTextColor() {
		return DEFAULT_TEXT_COLOR;
	}
}
