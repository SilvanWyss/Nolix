//package declaration
package ch.nolix.element.painter;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.textFormat.TextFormat;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 220
 */
public interface IPainter {
		
	//method
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
	public abstract IPainter createPainter(int xTranslation, int yTranslation);
	
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
	 * @return the default {@link TextFormat} of the current {@link IPainter}.
	 */
	public abstract TextFormat getDefaultTextFormat();
	
	//method declaration
	/**
	 * @param id
	 * @return the {@link Image} with the given id from the current {@link IPainter}.
	 */
	public abstract Image getImageById(String id);
	
	//method declaration
	/**
	 * @param text
	 * @param textFormat
	 * @return the width of the given text from the current {@link IPainter} using the given textFormat.
	 */
	public abstract int getTextWidth(String text, TextFormat textFormat);
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint a polygon with the vertices with the given x- and y-positions.
	 * 
	 * @param x
	 * @param y
	 */
	public abstract void paintFilledPolygon(int[] x, int[] y);
	
	//method
	/**
	 * Lets the current {@link IPainter} paint a filled rectangle with the given width and height.
	 * 
	 * @param width
	 * @param height
	 */
	public default void paintFilledRectangle(final int width, final int height) {
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
	public abstract void paintFilledRectangle(int xPosition, int yPosition,	int width, int height);
	
	//method
	/**
	 * Lets the current {@link Painter} paint the given image.
	 * 
	 * @param image
	 */
	public abstract void paintImage(Image image);
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint the given image with the given width and height.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 */
	public abstract void paintImage(Image image, int width, int height);
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint the image that has the given id.
	 * 
	 * @param id
	 */
	public abstract void paintImageById(String id);
	
	//method declaration
	/**
	 * Lets the current {@link Painter} paint the given image, that has the given id, with the given width and height.
	 * 
	 * @param image
	 * @param width
	 * @param height
	 */
	public abstract void paintImageById(String id, int width, int height);
	
	//method
	/**
	 * Lets the current {@link IPainter} paint the given text.
	 * 
	 * @param text
	 */
	public default void paintText(final String text) {
		
		//Calls other method.
		paintText(text, getDefaultTextFormat());
	}
	
	//method
	/**
	 * Lets the current {@link IPainter} paint the given text.
	 * 
	 * Only the first part of the given text, that is not longer than the given maxWidth, will be painted.
	 * 
	 * @param text
	 * @param maxWidth
	 */
	public default void paintText(final String text, final int maxWidth) {
		
		//Calls other method.
		paintText(text, getDefaultTextFormat(), maxWidth);
	}
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given text using the given text format.
	 * 
	 * @param text
	 * @param textFormat
	 */
	public abstract void paintText(String text, TextFormat textFormat);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given text using the given textFormat.
	 * 
	 * Only the first part of the given text, that is not longer than the given maxWidth, will be painted.
	 * 
	 * @param text
	 * @param textFormat
	 * @param maxWidth
	 */
	public abstract void paintText(String text, TextFormat textFormat, int maxWidth);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} register the given image at the given id.
	 * 
	 * @param id
	 * @param image
	 */
	public abstract void registerImageAtId(String id, Image image);
	
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
	public abstract void translate(int xTranslation, int yTranslation);
}
