package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link ImageWidgetWithBorderTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithBorderTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 */
public final class ImageWidgetWithBorderTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("ImageWidget with border tutorial");
		
		//Creates an image from resource.
		final var image =
		MutableImage.fromResource("ch/nolix/elementTutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Configures the look of the ImageWidget.
		imageWidget.onStyle(
			l ->
			l
			.setBorderThicknessForState(ControlState.BASE, 2)
			.setBorderColorForState(ControlState.BASE, Color.BLUE)
		);
		
		//Adds the ImageWidget to the Frame.
		frame.pushLayerWithRootWidget(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetWithBorderTutorial} can be created.
	 */
	private ImageWidgetWithBorderTutorial() {}
}
