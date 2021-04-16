package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link LabelShorteningTextTutorial} is a tutorial for {@link Label}s.
 * Of the {@link LabelTutorial} an instance cannot be created.
 *
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 40
 */
public final class LabelShorteningTextTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Label Shortening Text Tutorial");
		
		//Creates a Label with a long text.
		final var label = new Label().setText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
		
		//Configures the look of the label.
		label
		.setMaxWidth(500)
		.onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 50));	
			
		//Adds the label to the frame.
		frame.addLayerOnTop(label);
	}
	
	/**
	 * Avoids that an instance of the {@link LabelShorteningTextTutorial} can be created.
	 */
	private LabelShorteningTextTutorial() {}
}
