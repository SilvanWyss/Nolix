//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link ScrollTutorial} provides a tutorial for the scroll feature of the {@link Label}.
 * Of the {@link ScrollTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 50
 */
public final class ScrollTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a scrollable {@link Label}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a label.
		final var label = 
		new Label()
		.setText("PLATON")
		.setMaxWidth(800)
		.setMaxHeight(400);
		
		//Configures the look of the label.
		label
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setTextSize(400);
		
		//Creates a frame with the label.
		new Frame()
		.setTitle("Scroll Tutorial")
		.setRootWidget(label);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ScrollTutorial} can be created.
	 */
	private ScrollTutorial() {}
}
