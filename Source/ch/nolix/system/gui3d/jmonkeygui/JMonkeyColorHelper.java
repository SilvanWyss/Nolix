//package declaration
package ch.nolix.system.gui3d.jmonkeygui;

//JMonkey imports
import com.jme3.math.ColorRGBA;

import ch.nolix.system.graphic.color.Color;

//class
/**
 * The JMonkey color helper provides method to handle colors.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 */
public final class JMonkeyColorHelper {

	//static method
	/**
	 * @param color
	 * @return a new ColorRGBA that represents the given color.
	 */
	public static ColorRGBA createColorRGBA(final Color color) {
		return new ColorRGBA(
			(float)color.getNormalizedRedValue(),			
			(float)color.getNormalizedGreenValue(),
			(float)color.getNormalizedBlueValue(),
			1.0F
		);
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link JMonkeyColorHelper} can be created.
	 */
	private JMonkeyColorHelper() {}
}
