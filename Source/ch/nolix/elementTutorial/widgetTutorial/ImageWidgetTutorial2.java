package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.ImageWidget;

/**
 * The {@link ImageWidgetTutorial2} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetTutorial2} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 50
 */
public class ImageWidgetTutorial2 {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("ImageWidget Tutorial");
		
		//Creates an Image.
		final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Creates an ImageWidget with the Image.
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
		
		//Adds the ImageWidget to the Frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	
	/**
	 * Avoids that an instance of the {@link ImageWidgetTutorial2} can be created.
	 */
	private ImageWidgetTutorial2() {}
}
