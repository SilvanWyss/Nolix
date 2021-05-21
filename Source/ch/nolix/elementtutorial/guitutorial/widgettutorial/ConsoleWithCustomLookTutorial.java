package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Console;

/**
 * The {@link ConsoleWithCustomLookTutorial} is a tutorial for {@link Console}s.
 * Of the {@link ConsoleWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 50
 */
public final class ConsoleWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Console with custom look Tutorial");
		
		//Creates a Console.
		final var console =	new Console();
		console.writeLine("Hello!");
		
		//Configures the look of the Console.
		console
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 10)
		);
		
		//Adds the Console to the Frame.
		frame.addLayerOnTop(console);
	}
	
	/**
	 * Prevents that an instance of the {@link ConsoleWithCustomLookTutorial} can be created.
	 */
	private ConsoleWithCustomLookTutorial() {}
}
