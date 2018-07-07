//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Checkbox;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link CheckboxTutorial} provides a tutorial for a {@link Checkbox}.
 * Of the {@link CheckboxTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 50
 */
public final class CheckboxTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link Checkbox}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a checkbox
		final var checkBox = new Checkbox();
		
		//Configures the look of the checkbox.
		checkBox
		.getRefBaseLook()
		.setSize(50)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setLineThickness(5)
		.setLineColor(Color.DARK_BLUE);
		
		//Creates a frame with the checkbox.
		new Frame()
		.setTitle("Checkbox Tutorial")
		.setRootWidget(checkBox);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link CheckboxTutorial} can be created.
	 */
	private CheckboxTutorial() {}
}
