package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Checkbox;

/**
 * The {@link CheckboxTutorial} provides a tutorial for {@link Checkbox}s.
 * Of the {@link CheckboxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class CheckboxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Checkbox}.
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
