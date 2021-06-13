//package declaration
package ch.nolix.element.gui.image;

//Java imports
import java.awt.image.BufferedImage;

//own imports
import ch.nolix.element.gui.color.Color;

//interface
public interface IImage {
	
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
	IImage getSection(int xPosition, int yPosition, int width, int height);
	
	//method declaration
	Color getTopLeftPixel();
	
	//method declaration
	Color getTopRightPixel();
	
	//method declaration
	int getWidth();
			
	//method declaration
	BufferedImage toBufferedImage();
	
	//method declaration
	IImage toImmutableImage();
	
	//method declaration
	byte[] toJPG();
	
	//method declaration
	IImage toLeftRotatedImage();
	
	//method declaration
	byte[] toPNG();
	
	//method declaration
	IImage toRepeatedImage(int width, int height);
	
	//method declaration
	IImage toRightRotatedImage();
	
	//method declaration
	IImage toScaledImage(double factor);
	
	//method declaration
	IImage toScaledImage(double widthFactor, double heightFactor);
	
	//method declaration
	IImage toStretchedImage(int width, int height);
}
