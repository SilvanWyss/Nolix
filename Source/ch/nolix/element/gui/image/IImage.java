//package declaration
package ch.nolix.element.gui.image;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.common.container.matrix.Matrix;
import ch.nolix.element.elementapi.IElement;
import ch.nolix.element.gui.color.Color;

//interface
public interface IImage<I extends IImage<I>> extends IElement<I> {
	
	//method declaration
	IMutableImage<?> asWithAlphaValue(final double alphaValue);
	
	//method declaration
	IMutableImage<?> asWithWidthAndHeight(int width, int height);
	
	//method declaration
	Color getBottomLeftPixel();
	
	//method declaration
	Color getBottomRightPixel();
	
	//method declaration
	int getHeight();
	
	//method declaration
	Color getPixel(int xPosition, int yPosition);
	
	//method declaration
	int getPixelCount();
	
	//method declaration
	Matrix<Color> getPixles();
	
	//method declaration
	IMutableImage<?> getSection(int xPosition, int yPosition, int width, int height);
	
	//method declaration
	Color getTopLeftPixel();
	
	//method declaration
	Color getTopRightPixel();
	
	//method declaration
	int getWidth();
			
	//method declaration
	BufferedImage toBufferedImage();
	
	//method declaration
	IImage<?> toImmutableImage();
	
	//method declaration
	byte[] toJPG();
	
	//method declaration
	IMutableImage<?> toLeftRotatedImage();
	
	//method declaration
	byte[] toPNG();
	
	//method declaration
	IMutableImage<?> toRepeatedImage(int width, int height);
	
	//method declaration
	IMutableImage<?> toRightRotatedImage();
	
	//method declaration
	IMutableImage<?> toScaledImage(double factor);
	
	//method declaration
	IMutableImage<?> toScaledImage(double widthFactor, double heightFactor);
}
