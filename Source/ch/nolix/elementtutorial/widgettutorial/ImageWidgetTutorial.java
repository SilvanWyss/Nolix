package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.widget.ImageWidget;

/**
 * The {@link ImageWidgetTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 50
 */
public final class ImageWidgetTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Image Widget Tutorial");
		
		//Creates an Image.
		final var image = new Image(800, 500);
		
		for (var y = 1; y <= 500; y++) {
			for (var x = 1; x <= 800; x++) {
				image.setPixel(x, y, new Color((x * y) % 255, 0, (x + y) % 255));
			}
		}
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Configures the look of the ImageWidget.
		imageWidget.onLook(
			l ->
			l
			.setBorderThicknessesForState(WidgetLookState.NORMAL, 5)
			.setBackgroundColorForState(WidgetLookState.NORMAL, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.NORMAL, 5)
		);
		
		//Adds the imageWidget to the frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	/**
	 * Avoids that an instance of the {@link ImageWidgetTutorial} can be created.
	 */
	private ImageWidgetTutorial() {}
}
