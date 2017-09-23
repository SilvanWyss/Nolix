//package declaration
package ch.nolix.element.data;

//own imports
import ch.nolix.core.enums.UniDirection;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 70
 */
public final class BackgroundColorGradient extends ColorGradient {

	//type name
	public static final String TYPE_NAME = "BackgroundColorGradient";
	
	//constructor
	/**
	 * Creates new background color gradient.
	 */
	public BackgroundColorGradient() {}
	
	//constructor
	/**
	 * Creates new background color gradient with the given color 1 and color 2.
	 * 
	 * @param direction
	 * @param color1
	 * @param color2
	 * @throws NullArgumentException if the given direction is null.
	 * @throws NullArgumentException if the given color 1 is null.
	 * @throws NullArgumentException if the given color 2 is null.
	 */
	public BackgroundColorGradient(
		final Color color1,
		final Color color2
	) {
		
		//Calls constructor of the base class.
		super(color1, color2);
	}
	
	//constructor
	/**
	 * Creates new background color gradient with the given direction.
	 * 
	 * @param direction
	 * @throws NullArgumentException if the given direction is null.
	 */
	public BackgroundColorGradient(final UniDirection direction) {
		
		//Calls constructor of the base class.
		super(direction);
	}
	
	//constructor
	/**
	 * Creates new background color gradient with the given direction, color 1 and color 2.
	 * 
	 * @param direction
	 * @param color1
	 * @param color2
	 * @throws NullArgumentException if the given direction is null.
	 * @throws NullArgumentException if the given color 1 is null.
	 * @throws NullArgumentException if the given color 2 is null.
	 */
	public BackgroundColorGradient(
		final UniDirection direction,
		final Color color1,
		final Color color2
	) {
		
		//Calls constructor of the base class.
		super(direction, color1, color2);
	}
}
