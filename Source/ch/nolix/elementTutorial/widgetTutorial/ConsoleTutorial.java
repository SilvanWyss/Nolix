//package declaration
package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Console;

//class
/**
 * The {@link ConsoleTutorial} provides a tutorial for a {@link Console}.
 * Of the {@link ConsoleTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-06
 * @lines 50
 */
public final class ConsoleTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a console
		final var console =
		new Console()
		.writeLine("Hello!");
		
		//Configures the look of the console.
			console
			.setProposalWidth(500)
			.setProposalHeight(200);
			
			console
			.getRefBaseLook()
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.ALICE_BLUE)
			.setPaddings(5);
		
		//Creates a frame with the console.
		new Frame()
		.setTitle("Console Tutorial")
		.setRootWidget(console);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ConsoleTutorial} can be created.
	 */
	private ConsoleTutorial() {}
}
