package ch.nolix.systemtutorial.guitutorial.maintutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;

/**
 * The {@link TransparentLayerTutorial} is a tutorial for the {@link Layer#setOpacityPercentage} method.
 * Of the {@link TransparentLayerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2022-05-10
 */
public final class TransparentLayerTutorial {
	
	/**
	 * Creates a {@link Frame} with a background image and a transparent {@link Layer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Transparent Layer Tutorial");
		
		//Loads an Image and sets it as background to the Frame.
		frame.setBackgroundImage(
			MutableImage.fromResource("ch/nolix/systemTutorial/guiTutorial/maintutorial/resource/Pilatus.jpg"),
			ImageApplication.SCALE_TO_FRAME
		);
		
		//Creates a transparent Layer with a bar, that is basically black, and adds it to the Frame.
		frame.pushLayer(
			new Layer()
			.setOpacityPercentage(0.8)
			.setContentPosition(ExtendedContentPosition.BOTTOM)
			.setRootWidget(
				new Label()
				.setText("Nolix")
				.setMinWidthInPercentOfGUIViewAreaWidth(1.0)
				.onLook(
					l ->
					l
					.setBackgroundColorForState(WidgetLookState.BASE, Color.BLACK)
					.setPaddingForState(WidgetLookState.BASE, 50)
					.setTextSizeForState(WidgetLookState.BASE, 100)
					.setTextColorForState(WidgetLookState.BASE, Color.WHITE)
				)
			)
		);
	}
	
	/**
	 * Prevents that an instance of the {@link TransparentLayerTutorial} can be created.
	 */
	private TransparentLayerTutorial() {}
}
