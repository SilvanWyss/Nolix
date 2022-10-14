//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.ITextStyle;

//class
public final class TextStyle extends ExtendedControlStyle<TextStyle> implements ITextStyle<TextStyle> {
	
	//constructor
	public TextStyle() {
		initialize();
	}
}
