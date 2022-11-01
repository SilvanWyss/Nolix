//package declaration
package ch.nolix.system.gui.icon;

import ch.nolix.system.graphic.image.Image;

//class
public final class IconCatalogue {
	
	//constant
	private static final String NOLIX_ICON_RESOURCE_PATH = "ch/nolix/system/gui/icon/resource/Nolix_Icon.png";
	
	//constant
	public static final Image NOLIX_ICON = Image.fromResource(NOLIX_ICON_RESOURCE_PATH);
	
	//constructor
	private IconCatalogue() {}
}
