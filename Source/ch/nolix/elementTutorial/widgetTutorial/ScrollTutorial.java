package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Label;

/**
 * The {@link ScrollTutorial} is a tutorial for the scroll feature of {@link BorderWidget}s.
 * Of the {@link ScrollTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class ScrollTutorial {
	
	/**
	 * Creates a {@link Frame} with a scrollable {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Scroll Tutorial");
		
		//Creates a Label.
		final var label = new Label("PLATON");
		
		//Configures the look of the label.
		label
		.setMaxWidth(1000)
		.setMaxHeight(500)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setTextSize(500)
			.remainTextWhenLimited()
		);
		
		//Adds the label to the frame.
		frame.addLayerOnTop(label);
	}
	
	/**
	 * Avoids that an instance of the {@link ScrollTutorial} can be created.
	 */
	private ScrollTutorial() {}
}
