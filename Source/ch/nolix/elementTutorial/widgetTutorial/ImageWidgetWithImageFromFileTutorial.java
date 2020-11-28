package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;

/**
 * The {@link ImageWidgetWithImageFromFileTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithImageFromFileTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 50
 */
public final class ImageWidgetWithImageFromFileTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("ImageWidget Tutorial");
		
		//Creates an Image.
		final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Creates an ImageWidget with the Image.
		final var imageWidget = new ImageWidget().setImage(image);
		
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
	 * Avoids that an instance of the {@link ImageWidgetWithImageFromFileTutorial} can be created.
	 */
	private ImageWidgetWithImageFromFileTutorial() {}
}
