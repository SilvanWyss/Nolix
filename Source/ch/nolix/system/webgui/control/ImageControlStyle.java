//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.IImageControlStyle;

//class
public final class ImageControlStyle extends ControlStyle<IImageControlStyle> implements IImageControlStyle {
	
	//constructor
	public ImageControlStyle() {
		initialize();
	}
}
