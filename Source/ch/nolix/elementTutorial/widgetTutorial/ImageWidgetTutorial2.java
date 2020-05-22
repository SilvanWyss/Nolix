package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.ImageWidget;

public class ImageWidgetTutorial2 {
	
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
}
