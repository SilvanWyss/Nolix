package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.ImageWidget;

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
		MutableImage.fromResource("ch/nolix/systemtutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Adds the ImageWidget to the Frame.
		frame.pushLayerWithRootWidget(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetTutorial} can be created.
	 */
	private ImageWidgetTutorial() {}
}
