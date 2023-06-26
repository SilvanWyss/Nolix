//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.ILabelStyle;

//class
public final class LabelStyle extends ExtendedControlStyle<LabelStyle> implements ILabelStyle<LabelStyle> {
	
	//constructor
	public LabelStyle() {
		initialize();
	}
}
