//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;

//class
/**
 * The {@link ImageWidgetTutorial} provides a tutorial for a {@link ImageWidget}.
 * Of the {@link ImageWidgetTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 60
 */
public final class ImageWidgetTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Create an image
			final var image = new Image(800, 500);
			
			for (var y = 1; y <= 500; y++) {
				for (var x = 1; x <= 800; x++) {
					image.setPixel(x, y, new Color((x * y) % 255, 0, (x + y) % 255));
				}
			}
		
		//Creates an image widget with the image.
		final var imageWidget = new ImageWidget(image);
		
		//Configures the look of the image widget.
		imageWidget
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setPaddings(10);
		
		//Creates a frame with the image widget.
		new Frame()
		.setTitle("Image Widget Tutorial")
		.setRootWidget(imageWidget);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ImageWidgetTutorial} can be created.
	 */
	private ImageWidgetTutorial() {}
}
