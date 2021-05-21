package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.BorderWidget;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link ScrollingLabelTutorial} is a tutorial for the scroll feature of {@link BorderWidget}s.
 * Of the {@link ScrollingLabelTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-04-02
 * @lines 50
 */
public final class ScrollingLabelTutorial {
	
	/**
	 * Creates a {@link Frame} with a scrollable {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Scrolling Label Tutorial");
		
		//Creates a Label.
		final var label = new Label().setText("PLATON");
		
		//Configures the look of the Label.
		label
		.setMaxWidth(1000)
		.setMaxHeight(500)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setTextSizeForState(WidgetLookState.BASE, 500)
		);
		
		//Adds the Label to the frame.
		frame.addLayerOnTop(label);
	}
	
	/**
	 * Prevents that an instance of the {@link ScrollingLabelTutorial} can be created.
	 */
	private ScrollingLabelTutorial() {}
}
