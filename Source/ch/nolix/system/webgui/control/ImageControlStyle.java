//package declaration
package ch.nolix.system.webgui.control;

//own imports
import ch.nolix.system.webgui.controlstyle.ExtendedControlStyle;
import ch.nolix.systemapi.webguiapi.controlapi.IImageControlStyle;

//class
public final class ImageControlStyle
extends ExtendedControlStyle<ImageControlStyle>
implements IImageControlStyle<ImageControlStyle> {
	
	//constructor
	public ImageControlStyle() {
		initialize();
	}
}
