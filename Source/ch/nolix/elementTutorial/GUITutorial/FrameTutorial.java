//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link FrameTutorial} provides a tutorial for a {@link Frame}.
 * Of the {@link FrameTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 30
 */
public final class FrameTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with an alice blue background.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new Frame()
		.setTitle("Frame Tutorial")
		.setBackgroundColor(Color.ALICE_BLUE);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link FrameTutorial} can be created.
	 */
	private FrameTutorial() {}
}
