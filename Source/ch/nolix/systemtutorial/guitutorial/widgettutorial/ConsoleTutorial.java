package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widgetgui.Frame;

/**
 * The {@link ConsoleTutorial} is a tutorial for {@link Console}s.
 * Of the {@link ConsoleTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-06-29
 */
public final class ConsoleTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Console tutorial");
		
		//Creates a Console.
		final var console =	new Console();
		console.writeLine("Hello!");
							
		//Adds the Console to the Frame.
		frame.pushLayerWithRootWidget(console);
	}
	
	/**
	 * Prevents that an instance of the {@link ConsoleTutorial} can be created.
	 */
	private ConsoleTutorial() {}
}
