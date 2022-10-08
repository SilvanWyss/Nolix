//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.ITextboxStyle;

//class
public final class TextboxStyle extends ExtendedControlStyle<TextboxStyle> implements ITextboxStyle<TextboxStyle> {
	
	//constructor
	public TextboxStyle() {
		initialize();
	}
}
