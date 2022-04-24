//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.system.gui.image.Image;

//class
public final class GUIIconCatalogue {
	
	//constant
	private static final String NOLIX_ICON_RESOURCE_PATH = "ch/nolix/system/gui/base/resource/Nolix_Icon.png";
	
	//constant
	public static final Image NOLIX_ICON = Image.fromResource(NOLIX_ICON_RESOURCE_PATH);
	
	//constructor
	private GUIIconCatalogue() {}
}
