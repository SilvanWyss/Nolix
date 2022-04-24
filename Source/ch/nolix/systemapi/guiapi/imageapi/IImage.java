//package declaration
package ch.nolix.systemapi.guiapi.imageapi;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.elementapi.IElement;

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
