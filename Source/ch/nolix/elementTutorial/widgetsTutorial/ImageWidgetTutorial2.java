package ch.nolix.elementTutorial.widgetsTutorial;

//own imports
import ch.nolix.common.fileSystem.FileAccessor;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.element.widgets.ImageWidget;

public class ImageWidgetTutorial2 {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Image Widget Tutorial");
		
		//Creates an Image.
		final var image = Image.fromBytes(new FileAccessor("C:/Temp/temp2.jpg").readFileToBytes());
		
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
