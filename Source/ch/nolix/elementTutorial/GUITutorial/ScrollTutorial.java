//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.enums.UniDirection;
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;

//class
/**
 * The {@link ScrollTutorial} provides a tutorial for the scroll feature of a {@link Label}.
 * Of the {@link ScrollTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-4
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
		
		//Creates label.
		final var label = 
		new Label()
		.setText("HNO3")
		.setMaxWidth(600)
		.setMaxHeight(400);
		
		label.getRefNormalStructure()
		.setBorderSizes(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColorGradient(new ColorGradient(UniDirection.Vertical, Color.CORAL, Color.WHITE))
		.setTextSize(500);
		
		//Creates frame.
		new Frame()
		.setTitle("Scroll Tutorial")
		.setContentPosition(ContentPosition.Center)
		.setRootWidget(label);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ScrollTutorial() {}
}
