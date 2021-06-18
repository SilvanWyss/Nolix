package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.ImageWidget;

/**
 * The {@link ImageWidgetTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-08-23
 * @lines 40
 */
public final class ImageWidgetTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("ImageWidget tutorial");
		
		//Creates an Image.
		final var image = MutableImage.withWidthAndHeight(800, 500);
		for (var y = 1; y <= 500; y++) {
			for (var x = 1; x <= 800; x++) {
				image.setPixel(x, y, Color.withRedValueAndGreenValueAndBlueValue((x * y) % 255, 0, (x + y) % 255));
			}
		}
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Adds the ImageWidget to the Frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetTutorial} can be created.
	 */
	private ImageWidgetTutorial() {}
}
