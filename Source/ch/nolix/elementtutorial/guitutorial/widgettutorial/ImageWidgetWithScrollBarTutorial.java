package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.WidgetLookState;

/**
 * The {@link ImageWidgetWithScrollBarTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithScrollBarTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-25
 * @lines 50
 */
public final class ImageWidgetWithScrollBarTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link ImageWidget}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("ImageWidget with scroll bar tutorial");
		
		//Creates an enlarged image from resource.
		final var image =
		MutableImage.fromResource("ch/nolix/elementTutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg")
		.asWithWidthAndHeight(1000, 1000);
		
		//Creates an ImageWidget with the image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Configures the look of the ImageWidget.
		imageWidget
		.setMaxWidth(500)
		.setMaxHeight(500)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 2)
			.setBorderColorForState(WidgetLookState.BASE, Color.BLUE)
		);
		
		//Adds the ImageWidget to the Frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	/**
	 * Prevents that an instance of the {@link ImageWidgetWithScrollBarTutorial} can be created.
	 */
	private ImageWidgetWithScrollBarTutorial() {}
}
