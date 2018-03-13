//package declaration
package ch.nolix.element.painter;

//own import
import ch.nolix.element.color.Color;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 50
 */
public interface IPainter {

	//abstract method
	/**
	 * @param xTranslation
	 * @param yTranslation
	 * @return a new painter from this painter with the given translation.
	 */
	public abstract IPainter createTranslatedPainter(
		int xTranslation,
		int yTranslation
	);
	
	//abstract method
	/**
	 * Lets this painter paint a filled rectangle
	 * at the given x-position and y-Position with the given width and height.
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 */
	public abstract void paintFilledRectangle(
		int xPosition,
		int yPosition,
		int width, 
		int height
	);
	
	//abstract method
	/**
	 * Sets the color of this painter.
	 * 
	 * @param color
	 */
	public abstract void setColor(Color color);
}
