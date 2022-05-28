//package declaration
package ch.nolix.systemapi.guiapi.painterapi;

//own imports
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.baseapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.baseapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//interface
/**
 * @author Silvan Wyss
 * @date 2018-03-13
 */
public interface IPainter {
	
	//method
	/**
	 * @return a new {@link IPainter} from the current {@link IPainter}.
	 */
	default IPainter createPainter() {
		return createPainter(0, 0);
	}
	
	//method declaration
	/**
	 * @param xTranslation
	 * @param yTranslation
	 * @return a new {@link IPainter} from the current {@link IPainter} with the given translation.
	 */
	IPainter createPainter(int xTranslation, int yTranslation);
	
	/**
	 * 
	 * @param xTranslation
	 * @param yTranslation
	 * @param paintAreaWidth
	 * @param paintAreaHeight
	 * @return a new {@link IPainter} from the current {@link IPainter} with the given translation and paint area.
	 */
	IPainter createPainter(
		int xTranslation,
		int yTranslation,
		int paintAreaWidth,
		int paintAreaHeight
	);
	
	//method declaration
	/**
	 * @return true if the current {@link IPainter} descents from another {@link IPainter}.
	 */
	boolean descendsFromOtherPainter();
	
	//method declaration
	/**
	 * @return the default {@link TextFormat} of the current {@link IPainter}.
	 */
	TextFormat getDefaultTextFormat();
		
	//method declaration
	/**
	 * The effective opacity of a {@link IPainter} is
	 * the opacity of the {@link IPainter} multiplied with the opacity of its parent {@link IPainter}.
	 * 
	 * @return the effective opacity of the current {@link IPainter}.
	 */
	double getEffectiveOpacity();
	
	//method declaration
	/**
	 * @param id
	 * @return the {@link MutableImage} with the given id from the current {@link IPainter}.
	 */
	IImage<?> getImageById(String id);
	
	//method declaration
	/**
	 * @return the opacity of the current {@link IPainter}.
	 */
	double getOpacity();
	
	//method declaration
	/**
	 * @return the {@linker IPainter} the current {@link IPainter} descends from.
	 * @throws Exception if the current {link IPainter} does not descend from another {@link IPainter}.
	 */
	IPainter getParentPainter();
	
	//method declaration
	/**
	 * @param text
	 * @param textFormat
	 * @return the width of the given text from the current {@link IPainter} using the given textFormat.
	 */
	int getTextWidth(String text, TextFormat textFormat);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint a polygon with the vertices with the given x- and y-positions.
	 * 
	 * @param x
	 * @param y
	 */
	void paintFilledPolygon(int[] x, int[] y);
	
	//method
	/**
	 * Lets the current {@link IPainter} paint a filled rectangle with the given width and height.
	 * 
	 * @param width
	 * @param height
	 */
	default void paintFilledRectangle(final int width, final int height) {
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
	void paintFilledRectangle(int xPosition, int yPosition,	int width, int height);
	
	//method
	/**
	 * Lets the current {@link IPainter} paint the given image.
	 * 
	 * @param mutableImage
	 */
	void paintImage(IImage<?> mutableImage);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given image with the given width and height.
	 * 
	 * @param mutableImage
	 * @param width
	 * @param height
	 */
	void paintImage(IImage<?> mutableImage, int width, int height);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the image that has the given id.
	 * 
	 * @param id
	 */
	void paintImageById(String id);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given image, that has the given id, with the given width and height.
	 * 
	 * @param id
	 * @param width
	 * @param height
	 */
	void paintImageById(String id, int width, int height);
	
	//method
	/**
	 * Lets the current {@link IPainter} paint the given text.
	 * 
	 * @param text
	 */
	default void paintText(final String text) {
		
		//Calls other method.
		paintText(text, getDefaultTextFormat());
	}
	
	//method
	/**
	 * Lets the current {@link IPainter} paint the given text.
	 * Only the first part of the given text, that is not longer than the given maxWidth, will be painted.
	 * 
	 * @param text
	 * @param maxWidth
	 */
	default void paintText(final String text, final int maxWidth) {
		
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
	void paintText(String text, TextFormat textFormat);
	
	//method declaration
	/**
	 * Lets the current {@link IPainter} paint the given text using the given textFormat.
	 * Only the first part of the given text, that is not longer than the given maxWidth, will be painted.
	 * 
	 * @param text
	 * @param textFormat
	 * @param maxWidth
	 */
	void paintText(String text, TextFormat textFormat, int maxWidth);
	
	//method declaration
	/**
	 * Sets the color of the current {@link IPainter}.
	 * 
	 * @param color
	 */
	void setColor(IColor color);
	
	//method declaration
	/**
	 * Sets the color gradient of the current {@link IPainter}.
	 * 
	 * @param colorGradient
	 */
	void setColorGradient(IColorGradient colorGradient);
	
	//method declaration
	/**
	 * Sets the opacity of the current {@link IPainter}.
	 * 
	 * @param opacity
	 */
	void setOpacity(double opacity);
	
	//method declaration
	/**
	 * Translates the current {@link IPainter} by the given translation.
	 * 
	 * @param xTranslation
	 * @param yTranslation
	 */
	void translate(int xTranslation, int yTranslation);
	
	//method
	/**
	 * Translates the current {@link IPainter} horizontally by the given xTranslation.
	 * 
	 * @param xTranslation
	 */
	default void translateHorizontally(final int xTranslation) {
		translate(xTranslation, 0);
	}
	
	//method
	/**
	 * Translates the current {@link IPainter} vertically by the given yTranslation.
	 * 
	 * @param yTranslation
	 */
	default void translateVertically(final int yTranslation) {
		translate(0, yTranslation);
	}
}
