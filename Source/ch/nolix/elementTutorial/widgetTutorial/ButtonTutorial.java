package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Button;

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
		button.setLeftMouseButtonReleaseAction(() -> {
			
			frame.setBackgroundColor(counter % 2 == 0 ? Color.WHITE : Color.BLUE);
			
			counter++;
		});
		
		//Configures the look of the Button.
		button
		.setCustomCursorIcon(CursorIcon.Hand)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(5)
			.setTextSize(50)
		)
		.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.LAVENDER))
		.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.LAVENDER));
		
		//Adds the Button to the Frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
