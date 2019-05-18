package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
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
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Checkbox Tutorial");
		
		//Creates a Checkbox.
		final var checkBox = new Checkbox();
		
		//Configures the look of the checkbox.
		checkBox.applyOnBaseLook(
			bl -> 
			bl
			.setSize(50)
			.setLineThickness(5)
			.setLineColor(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
		);
		
		//Adds the checkbox to the frame.
		frame.addLayerOnTop(checkBox);
	}
	
	/**
	 * Avoids that an instance of the {@link CheckboxTutorial} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private CheckboxTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
