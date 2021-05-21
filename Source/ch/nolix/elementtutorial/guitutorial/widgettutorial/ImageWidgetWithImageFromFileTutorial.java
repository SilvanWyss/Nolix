package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.Image;
import ch.nolix.element.gui.widget.ImageWidget;

/**
 * The {@link ImageWidgetWithImageFromFileTutorial} is a tutorial for {@link ImageWidget}s.
 * Of the {@link ImageWidgetWithImageFromFileTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-09-01
 * @lines 50
 */
public final class ImageWidgetWithImageFromFileTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("ImageWidget Tutorial");
		
		//Creates an Image.
		final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Creates an ImageWidget with the Image.
		final var imageWidget = new ImageWidget().setImage(image);
		
		//Configures the look of the ImageWidget.
		imageWidget
		.getRefLook()	
		.setBorderThicknessForState(WidgetLookState.BASE, 5)
		.setBorderColorForState(WidgetLookState.BASE, Color.DARK_BLUE)
		.setBackgroundColorForState(WidgetLookState.BASE, Color.WHITE_SMOKE)
		.setPaddingForState(WidgetLookState.BASE, 5);
		
		//Adds the ImageWidget to the Frame.
		frame.addLayerOnTop(imageWidget);
	}
	
	private ImageWidgetWithImageFromFileTutorial() {}
}
