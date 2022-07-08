package ch.nolix.systemtutorial.guitutorial.maintutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;

/**
 * The {@link LayerTutorial2} is a tutorial for {@link Layer}s.
 * Of the {@link LayerTutorial2} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2022-03-11
 */
public final class LayerTutorial2 {
	
	/**
	 * Creates a {@link Frame} with 2 {@link Layer}s.
	 * The first {@link Layer} will show the letter 'A', the second 'Layer' will show the letter 'B'.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Layer tutorial").setBackgroundColor(Color.LAVENDER);
		
		//Creates labelA and labelB.
		final var labelA = new Label().setText("A");
		final var labelB = new Label().setText("B");
		
		//Configures the look of labelA.
		labelA.getRefActiveLook()
		.setTextSizeForState(WidgetLookState.BASE, 500)
		.setTextColorForState(WidgetLookState.BASE, Color.GREY);
		
		//Configures the look of labelB.
		labelB.getRefActiveLook()
		.setTextSizeForState(WidgetLookState.BASE, 400)
		.setTextColorForState(WidgetLookState.BASE, Color.BLACK);
		
		//Adds a new layer with labelA to the Frame.
		frame.pushLayer(ExtendedContentPosition.CENTER, labelA);
		
		//Adds a new layer with labelB to the Frame.
		frame.pushLayer(ExtendedContentPosition.CENTER, labelB);
	}
	
	/**
	 * Prevents that an instance of the {@link LayerTutorial2} can be created.
	 */
	private LayerTutorial2() {}
}
