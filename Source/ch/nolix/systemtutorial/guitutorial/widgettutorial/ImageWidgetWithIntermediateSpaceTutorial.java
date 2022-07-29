package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link ImageWidgetWithIntermediateSpaceTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithIntermediateSpaceTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-25
 */
public final class ImageWidgetWithIntermediateSpaceTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("ImageWidget with intermediate space tutorial");
		
		//Creates an enlarged image from resource.
		final var image =
		MutableImage.fromResource("ch/nolix/elementTutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg")
		.withWidthAndHeight(400, 400);
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Configures the style of the ImageWidget.
		imageWidget
		.setMinWidth(500)
		.setMinHeight(500)
		.setContentPosition(ContentPosition.LEFT)
		.onStyle(
			l ->
			l
			.setBorderThicknessForState(ControlState.BASE, 2)
			.setBorderColorForState(ControlState.BASE, Color.BLUE)
			.setBackgroundColorForState(ControlState.BASE, Color.LIGHT_PINK)
		);
		
		//Adds the ImageWidget to the Frame.
		frame.pushLayerWithRootWidget(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetWithIntermediateSpaceTutorial} can be created.
	 */
	private ImageWidgetWithIntermediateSpaceTutorial() {}
}
