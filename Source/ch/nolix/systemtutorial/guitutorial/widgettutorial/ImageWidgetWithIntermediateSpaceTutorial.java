package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

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
		
		//Configures the look of the ImageWidget.
		imageWidget
		.setMinWidth(500)
		.setMinHeight(500)
		.setContentPosition(ContentPosition.LEFT)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 2)
			.setBorderColorForState(WidgetLookState.BASE, Color.BLUE)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LIGHT_PINK)
		);
		
		//Adds the ImageWidget to the Frame.
		frame.pushLayerWithRootWidget(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetWithIntermediateSpaceTutorial} can be created.
	 */
	private ImageWidgetWithIntermediateSpaceTutorial() {}
}
