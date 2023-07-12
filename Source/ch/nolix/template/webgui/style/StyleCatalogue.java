//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.Style;

//class
public final class StyleCatalogue {
	
	//constant
	public static final Style DARK_MODE_STYLE = new DarkModeStyleCreator().createDarkModeStyle();
	
	//constructor
	private StyleCatalogue() {}
}
