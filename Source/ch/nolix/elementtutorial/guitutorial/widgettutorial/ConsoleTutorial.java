package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.Console;

/**
 * The {@link ConsoleTutorial} provides a tutorial for {@link Console}s.
 * Of the {@link ConsoleTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-06-29
 * @lines 30
 */
public final class ConsoleTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Console Tutorial");
		
		//Creates a Console.
		final var console =	new Console();
		console.writeLine("Hello!");
							
		//Adds the Console to the Frame.
		frame.addLayerOnTop(console);
	}
	
	/**
	 * Prevents that an instance of the {@link ConsoleTutorial} can be created.
	 */
	private ConsoleTutorial() {}
}
