package ch.nolix.elementTutorial.widgetsTutorial;

//own imports
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.Button;

/**
 * The {@link ButtonTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 60
 */
public final class ButtonTutorial {
	
	private static int counter = 1;
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Button Tutorial");
		
		//Creates a Button.
		final var button = new Button("Change background color");
		
		//Sets left mouse button release command to the Button.
		button.setLeftMouseButtonReleaseCommand(() -> {
			
			frame.setBackgroundColor(counter % 2 == 0 ? Color.WHITE : Color.LIGHT_GREEN);
			
			counter++;
		});
		
		//Configures the look of the Button.
		button
		.setCustomCursorIcon(CursorIcon.Hand)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
			.setTextSize(50)
			.setTextColor(Color.DARK_BLUE)
		)
		.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.LIGHT_GREY))
		.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.LIGHT_GREY));
		
		//Adds the Button to the Frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
