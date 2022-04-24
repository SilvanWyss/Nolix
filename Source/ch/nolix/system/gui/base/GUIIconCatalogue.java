//package declaration
package ch.nolix.system.gui.base;

import ch.nolix.system.gui.image.Image;

//class
public final class GUIIconCatalogue {
	
	//constant
	private static final String NOLIX_ICON_RESOURCE_PATH = "ch/nolix/element/gui/base/resource/Nolix_Icon.png";
	
	//constant
	public static final Image NOLIX_ICON = Image.fromResource(NOLIX_ICON_RESOURCE_PATH);
	
	//constructor
	private GUIIconCatalogue() {}
}
