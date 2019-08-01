package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.image.Image;
import ch.nolix.element.widgets.ImageWidget;

/**
 * The {@link ImageWidgetTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 */
public final class ImageWidgetTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new LayerFrame("Image Widget Tutorial");
		
		//Creates an Image.
		final var image = new Image(800, 500);
		
		for (var y = 1; y <= 500; y++) {
			for (var x = 1; x <= 800; x++) {
				image.setPixel(x, y, new Color((x * y) % 255, 0, (x + y) % 255));
			}
		}
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget(image);
		
		//Configures the look of the ImageWidget.
		imageWidget.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
		);
		
		//Adds the imageWidget to the frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	/**
	 * Avoids that an instance of the {@link ImageWidgetTutorial} can be created.
	 */
	private ImageWidgetTutorial() {}
}
