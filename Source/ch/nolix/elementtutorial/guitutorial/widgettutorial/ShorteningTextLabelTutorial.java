package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link ShorteningTextLabelTutorial} is a tutorial for {@link Label}s.
 * Of the {@link TimeLabelTutorial} an instance cannot be created.
 *
 * @author Silvan Wyss
 * @date 2019-05-19
 * @lines 40
 */
public final class ShorteningTextLabelTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Shortening text Label Tutorial");
		
		//Creates a Label with a long text.
		final var label = new Label().setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
		
		//Configures the look of the Label.
		label
		.setMaxWidth(500)
		.onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 50));	
			
		//Adds the Label to the Frame.
		frame.addLayerOnTop(label);
	}
	
	/**
	 * Prevents that an instance of the {@link ShorteningTextLabelTutorial} can be created.
	 */
	private ShorteningTextLabelTutorial() {}
}
