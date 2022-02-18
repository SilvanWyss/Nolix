package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.ImageWidget;

/**
 * The {@link ImageWidgetTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-08-23
 */
public final class ImageWidgetTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("ImageWidget tutorial");
		
		//Creates an image from resource.
		final var image =
		MutableImage.fromResource("ch/nolix/elementTutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg");
		
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
