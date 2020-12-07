package ch.nolix.elementtutorial.widgettutorial;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Console;

/**
 * The {@link ConsoleTutorial} provides a tutorial for {@link Console}s.
 * Of the {@link ConsoleTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-06
 * @lines 50
 */
public final class ConsoleTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Console Tutorial");
		
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
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(5)
		);
		
		//Adds the console to the frame.
		frame.addLayerOnTop(console);
	}
	
	/**
	 * Avoids that an instance of the {@link ConsoleTutorial} can be created.
	 */
	private ConsoleTutorial() {}
}
