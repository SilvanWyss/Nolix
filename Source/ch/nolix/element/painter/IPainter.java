//package declaration
package ch.nolix.element.painter;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.textFormat.TextFormat;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 200
 */
public interface IPainter {
	
	//default method
	/**
	 * @return a new {@link IPainter} from the current {@link IPainter}.
	 */
	public default IPainter createPainter() {
		return createPainter(0, 0);
	}

	//method declaration
	/**
	 * @param xTranslation
	 * @param yTranslation
	 * @return a new {@link IPainter} from the current {@link IPainter}
	 * with the given translation.
	 */
	public abstract IPainter createPainter(
		int xTranslation,
		int yTranslation
	);
	
	/**
	 * 
	 * @param xTranslation
	 * @param yTranslation
	 * @param paintAreaWidth
	 * @param paintAreaHeight
	 * @return a new {@link IPainter} from the current {@link IPainter}
	 * with the given translation and paint area.
	 */
	public abstract IPainter createPainter(
		int xTranslation,
		int yTranslation,
		int paintAreaWidth,
		int paintAreaHeight
	);
	
	//method declaration
	/**
	 * @param text
	 * @param textFormat
	 * @return the width of the given text
	 * from the current {@link IPainter} using the given font.
	 */
	public abstract int getTextWith(
		String text,
		TextFormat textFormat
	);
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint a polygon
	 * with the vertices with the given x- and y-positions.
	 * 
	 * @param vertices
	 */
	public abstract void paintFilledPolygon(final int[] xs, final int[] ys);
	
	//default method
	/**
	 * Lets the current {@link IPainter} paint a filled rectangle
	 * at its origin position with the given width and height.
	 * 
	 * @param xPosition
	 * @param yPosition
	 * @param width
	 * @param height
	 */
	public default void paintFilledRectangle(
		final int width,
		final int height
	) {
		paintFilledRectangle(0, 0, width, height);
	}
	
	//method declaration
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
	
	//default method
	/**
	 * Lets the current {@link Painter} paint the given image.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 */
	public default void paintImage(final Image image) {
		
		//Calls other method
		paintImage(image, image.getWidth(), image.getHeight());
	}
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint the given image with the given width and height.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 */
	public abstract void paintImage(
		Image image,
		int width,
		int height
	);
	
	//default method
	/**
	 * Lets the current {@link IPainter} paint the given using a default {@link TextFormat}.
	 * 
	 * @param text
	 */
	public default void paintText(final String text) {
		paintText(text, new TextFormat());
	}
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given text using the given text format.
	 * 
	 * @param text
	 * @param textFormat
	 */
	public abstract void paintText(
		String text,
		TextFormat textFormat
	);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given text using the given text format.
	 * 
	 * Only the first part of the given text
	 * that is not longer than the given max width will be painted.
	 * 
	 * @param text
	 * @param textFormat
	 * @param maxTextWidth
	 */
	public abstract void paintText(
		String text,
		TextFormat textFormat,
		int maxTextWidth
	);
	
	//method declaration
	/**
	 * Sets the color of the current {@link IPainter}.
	 * 
	 * @param color
	 */
	public abstract void setColor(Color color);
	
	//method declaration
	/**
	 * Sets the color gradient of the current {@link IPainter}.
	 * 
	 * @param colorGradient
	 */
	public abstract void setColorGradient(ColorGradient colorGradient);
	
	//method declaration
	/**
	 * Translates the current {@link IPainter}.
	 * 
	 * @param xTranslation
	 * @param yTranslation
	 */
	public abstract void translate(
		int xTranslation,
		int yTranslation
	);
}
