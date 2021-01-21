package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.gui.CursorIcon;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Button;

/**
 * The {@link ButtonTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-19
 * @lines 60
 */
public final class ButtonTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Button Tutorial");
		
		//Creates a Button.
		final var button = new Button().setText("Change background color");
		
		//Defines a left mouse button release action for the Button.
		button.setLeftMouseButtonReleaseAction(
			() -> {
				if (frame.getBackgroundColor().equals(Color.WHITE)) {
					frame.setBackgroundColor(Color.YELLOW);
				} else {
					frame.setBackgroundColor(Color.WHITE);
				}
			}
		);
		
		//Configures the look of the Button.
		button
		.setCustomCursorIcon(CursorIcon.HAND)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(5)
			.setTextSize(50)
		)
		.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.SKY_BLUE));
		
		//Adds the Button to the Frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
