package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Console;

/**
 * The {@link ConsoleTutorial} provides a tutorial for {@link Console}s.
 * Of the {@link ConsoleTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-06
 */
public final class ConsoleTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Console Tutorial");
		
		//Creates a Console
		final var console =	new Console();
		console.writeLine("Hello!");
		
		//Configures the look of the console.
		console
		.setProposalWidth(500)
		.setProposalHeight(200)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
			.setTextColor(Color.DARK_BLUE)
		);
		
		//Adds the console to the frame.
		frame.addLayerOnTop(console);
	}
	
	/**
	 * Avoids that an instance of the {@link ConsoleTutorial} can be created.
	 */
	private ConsoleTutorial() {}
}
