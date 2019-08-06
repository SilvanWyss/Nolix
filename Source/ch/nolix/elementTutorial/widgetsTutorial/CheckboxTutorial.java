package ch.nolix.elementTutorial.widgetsTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Checkbox;

/**
 * The {@link CheckboxTutorial} provides a tutorial for {@link Checkbox}s.
 * Of the {@link CheckboxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class CheckboxTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link Checkbox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Checkbox Tutorial");
		
		//Creates a Checkbox.
		final var checkBox = new Checkbox();
		
		//Configures the look of the checkbox.
		checkBox.applyOnBaseLook(
			bl -> 
			bl
			.setTextSize(50)
			.setLineThickness(5)
			.setTextColor(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
		);
		
		//Adds the checkbox to the frame.
		frame.addLayerOnTop(checkBox);
	}
	
	/**
	 * Avoids that an instance of the {@link CheckboxTutorial} can be created.
	 */
	private CheckboxTutorial() {}
}
