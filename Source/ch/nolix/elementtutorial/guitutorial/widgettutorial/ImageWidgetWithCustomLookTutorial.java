package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.widget.ImageWidget;

/**
 * The {@link ImageWidgetWithCustomLookTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 50
 */
public final class ImageWidgetWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("ImageWidget tutorial");
		
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
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 10)
		);
		
		//Adds the ImageWidget to the Frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetWithCustomLookTutorial} can be created.
	 */
	private ImageWidgetWithCustomLookTutorial() {}
}
