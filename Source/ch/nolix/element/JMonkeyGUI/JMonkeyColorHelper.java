//package declaration
package ch.nolix.element.JMonkeyGUI;

//JMonkey import
import com.jme3.math.ColorRGBA;

//own import
import ch.nolix.element.color.Color;

//class
/**
 * The JMonkey color helper provides method to handle colors.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 40
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
			1.0f
		);
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private JMonkeyColorHelper() {}
}
