package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link ConsoleWithCustomLookTutorial} is a tutorial for {@link Console}s.
 * Of the {@link ConsoleWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 */
public final class ConsoleWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Console}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Console with custom look tutorial");
		
		//Creates a Console.
		final var console =	new Console();
		console.writeLine("Hello!");
		
		//Configures the look of the Console.
		console
		.onStyle(
			l ->
			l
			.setBorderThicknessForState(ControlState.BASE, 1)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setPaddingForState(ControlState.BASE, 10)
		);
		
		//Adds the Console to the Frame.
		frame.pushLayerWithRootWidget(console);
	}
	
	/**
	 * Prevents that an instance of the {@link ConsoleWithCustomLookTutorial} can be created.
	 */
	private ConsoleWithCustomLookTutorial() {}
}
