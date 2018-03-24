//package declaration
package ch.nolix.element.painter;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.font.Font;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 70
 */
public interface IPainter {

	//abstract method
	/**
	 * @param xTranslation
	 * @param yTranslation
	 * @return a new {@link IPainter} from the current {@link IPainter} with the given translation.
	 */
	public abstract IPainter createTranslatedPainter(
		int xTranslation,
		int yTranslation
	);
	
	//abstract method
	/**
	 * @param text
	 * @param font
	 * @return the width of the given text from the current {@link IPainter} using the given font.
	 */
	public abstract int getTextWith(
		String text,
		Font font
	);
	
	//abstract method
	/**
	 * Lets the current {@link IPainter} paint a filled rectangle
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
	 * Lets the current {@link IPainter} paint the given text using the given font.
	 * 
	 * @param text
	 * @param font
	 */
	public abstract void paintText(
		String text,
		Font font
	);
	
	//abstract method
	/**
	 * Sets the color of the current {@link IPainter}.
	 * 
	 * @param color
	 */
	public abstract void setColor(Color color);
}
